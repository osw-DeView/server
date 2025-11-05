package com.deview.server.content.study.controller;

import com.deview.server.domain.auth.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class StudyController {
    private final AuthService authService;

}
