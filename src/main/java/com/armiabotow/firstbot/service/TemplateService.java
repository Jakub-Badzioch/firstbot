package com.armiabotow.firstbot.service;

import com.armiabotow.firstbot.model.impl.Template;
import com.armiabotow.firstbot.repository.TemplateRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;

@RequiredArgsConstructor
@Service
public class TemplateService {

    private final TemplateRepository templateRepository;

    public Template getByName(String name) {
       return templateRepository.findByName(name).orElseThrow(EntityNotFoundException::new);
    }
}
