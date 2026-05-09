package com.chronos.Scheduler.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@Getter
@Setter
public class EmailPayload extends Payload {

    private String toEmail;

    private String subject;

    @Column(length = 5000)
    private String body;
}
