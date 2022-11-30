package com.example.projetobd.service;

import com.example.projetobd.entity.Session;
import com.example.projetobd.repository.SessionRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SessionService {
    private final SessionRepository sessionRepository;
    private final ModelMapper modelMapper;

    public SessionService(SessionRepository sessionRepository, ModelMapper modelMapper) {
        this.sessionRepository = sessionRepository;
        this.modelMapper = modelMapper;
    }

    public List<Session> getSessions() {
        return sessionRepository.findAll();
    }

    public Session getSessionById(Long sessionId) {
        return sessionRepository.findById(sessionId).get();
    }

    public void deleteAllSessions() {
        //before deleting all sessions we must set all foreign keys to null
        for (Session session : sessionRepository.findAll()) {
            session.setRoom(null);
            session.setMovie(null);
            sessionRepository.save(session);
        }
    }


}
