package com.armiabotow.firstbot.model.impl;

import com.armiabotow.firstbot.model.Basic;
import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.Entity;
import javax.persistence.Lob;
import java.time.LocalDateTime;

@Entity
@SuperBuilder
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Meeting extends Basic {
    private String name;
    private LocalDateTime dateTime;
    @Lob
    private String description;
}
