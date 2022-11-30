package com.example.projetobd.controller.api;

import com.example.projetobd.entity.Session;
import com.example.projetobd.service.SessionService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/sessions")
public class SessionController {

    private final SessionService sessionService;

    public SessionController(SessionService sessionService) {
        this.sessionService = sessionService;
    }

    @GetMapping
    public List<Session> getSessions() {
        return sessionService.getSessions();
    }

    @GetMapping("/{sessionId}")
    public void getSessionById(@PathVariable Long sessionId) {
        sessionService.getSessionById(sessionId);
    }
    
    @DeleteMapping
    public void deleteAllSessions() {
        sessionService.deleteAllSessions();
    }

}
