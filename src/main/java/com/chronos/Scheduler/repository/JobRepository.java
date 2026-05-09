package com.chronos.Scheduler.repository;

import com.chronos.Scheduler.entity.Job;
import com.chronos.Scheduler.entity.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface JobRepository extends JpaRepository<Job, Long> {
        List<Job> findByNextExecutionTimeLessThanEqualAndStatus(
                LocalDateTime now,
                Status status
        );
}
