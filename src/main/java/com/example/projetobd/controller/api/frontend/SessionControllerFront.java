package com.example.projetobd.controller.api.frontend;

import com.example.projetobd.service.RoomService;
import com.example.projetobd.service.SessionService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/sessions")
public class SessionControllerFront {

    private final RoomService roomService;
    private final SessionService sessionService;
    private final ModelMapper modelMapper;

    public SessionControllerFront(RoomService roomService, SessionService sessionService, ModelMapper modelMapper) {
        this.roomService = roomService;
        this.sessionService = sessionService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/{sessionId}")
    public String getSession(@PathVariable Long sessionId, Model model) {
        model.addAttribute("sessao", sessionService.getSessionById(sessionId));
        model.addAttribute("sessionService", sessionService);
        return "session";
    }


}
