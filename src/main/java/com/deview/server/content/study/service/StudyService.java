package com.deview.server.content.study.service;

import com.deview.server.content.study.repository.StudyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class StudyService {
    private final StudyRepository studyRepository;
}
