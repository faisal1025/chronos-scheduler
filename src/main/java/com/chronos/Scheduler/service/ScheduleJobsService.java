package com.chronos.Scheduler.service;

import com.chronos.Scheduler.entity.Job;
import com.chronos.Scheduler.entity.Status;
import com.chronos.Scheduler.repository.JobRepository;
import jakarta.transaction.Transactional;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.w3c.dom.stylesheets.LinkStyle;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ScheduleJobsService {

    private final JobRepository jobRepository;
    private final KafkaTemplate<String, Job> kafkaTemplate;

    public ScheduleJobsService(JobRepository jobRepository, KafkaTemplate<String, Job> kafkaTemplate) {
        this.jobRepository = jobRepository;
        this.kafkaTemplate = kafkaTemplate;
    }

    @Scheduled(fixedDelay = 2000)
    @Transactional
    public void pollJobs() {

        List<Job> jobs =
                jobRepository.findByNextExecutionTimeLessThanEqualAndStatus(LocalDateTime.now(),
                        Status.SCHEDULED);

        for (Job job : jobs) {

            kafkaTemplate.send("job_to_execute", job);

            if (Boolean.TRUE.equals(job.isRecurring())) {

                job.setNextExecutionTime(
                        job.getNextExecutionTime().plusDays(1)
                );

            } else {

                job.setStatus(Status.COMPLETED);
            }

            jobRepository.save(job);
        }
    }
}
