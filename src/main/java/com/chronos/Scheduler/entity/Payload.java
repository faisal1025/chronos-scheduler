package com.chronos.Scheduler.entity;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Getter
@Setter
@NoArgsConstructor
@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.PROPERTY,
        property = "type"
)
@JsonSubTypes({
        @JsonSubTypes.Type(
                value = EmailPayload.class,
                name = "EMAIL"
        ),
        @JsonSubTypes.Type(
                value = HttpPayload.class,
                name = "HTTP"
        ),
        @JsonSubTypes.Type(
                value = ScriptPayload.class,
                name = "SCRIPT"
        )
})
public abstract class Payload {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO.IDENTITY)
    private Long id;
}
