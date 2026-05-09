package com.chronos.Scheduler.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class HttpPayload extends Payload {

    private String url;

    private String method;

    @Column(length = 10000)
    private String body;

    @Column(length = 5000)
    private String headersJson;
}
