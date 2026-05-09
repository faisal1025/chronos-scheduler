package com.chronos.Scheduler.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@NoArgsConstructor
@Getter
@Setter
public abstract class Payload {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO.IDENTITY)
    private Long id;
}
