package com.armiabotow.firstbot.model.impl;

import com.armiabotow.firstbot.model.Basic;
import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.Entity;
import javax.persistence.Lob;


@Entity
@SuperBuilder
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Template extends Basic {
    private String name;
    // wiecej niz 255 znakow
    @Lob
    private String body;
}
