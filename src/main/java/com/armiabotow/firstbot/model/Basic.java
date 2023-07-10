package com.armiabotow.firstbot.model;

import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;


@MappedSuperclass
@SuperBuilder
@EqualsAndHashCode
@ToString
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public abstract class Basic {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
}
