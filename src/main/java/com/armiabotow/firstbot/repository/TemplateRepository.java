package com.armiabotow.firstbot.repository;

import com.armiabotow.firstbot.model.impl.Template;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.lang.NonNull;

import java.util.Optional;

public interface TemplateRepository extends JpaRepository<Template, Long> {
    Optional<Template> findByName(@NonNull String name);
}
