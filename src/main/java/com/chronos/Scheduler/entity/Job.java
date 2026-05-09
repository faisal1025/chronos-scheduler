package com.chronos.Scheduler.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Entity
@EntityListeners(AuditingEntityListener.class)
@NoArgsConstructor
public class Job {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    private Long id;

    @Getter
    @Setter
    @Enumerated(EnumType.STRING)
    private JobType jobType;

    @Getter
    @Setter
    private boolean isRecurring;

    @Getter
    @Setter
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "payload_id")
    private Payload payload;

    @Getter
    @Setter
    private String cronExpression;

    @Getter
    @Setter
    private LocalDateTime nextExecutionTime;

    @Getter
    @Setter
    @Enumerated(EnumType.STRING)
    private Status status;

    @Getter
    @Setter
    private Long retryInterval; // in ms

    @Getter
    @Setter
    private Byte maxRetries;

    @Getter
    @Setter
    @CreatedDate
    private LocalDateTime createdAt;

    @Getter
    @Setter
    @LastModifiedDate
    private LocalDateTime updatedAt;

    @Getter
    @Setter
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "created_by_user_id", nullable = false)
    private UserDomain createdBy;

    public Job(JobType jobType, Payload payload, String cronExpression, LocalDateTime nextExecutionTime, Status status, Long retryInterval, Byte maxRetries, LocalDateTime createdAt, LocalDateTime updatedAt, UserDomain createdBy) {
        this.jobType = jobType;
        this.payload = payload;
        this.cronExpression = cronExpression;
        this.nextExecutionTime = nextExecutionTime;
        this.status = status;
        this.retryInterval = retryInterval;
        this.maxRetries = maxRetries;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.createdBy = createdBy;
    }
}
