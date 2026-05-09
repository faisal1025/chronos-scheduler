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
public class ScriptPayload extends Payload {

    @Column(length = 10000)
    private String command;
}
