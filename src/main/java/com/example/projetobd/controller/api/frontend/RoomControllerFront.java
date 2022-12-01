package com.example.projetobd.controller.api.frontend;

import com.example.projetobd.request.RoomRequest;
import com.example.projetobd.request.SessionCreateRequest;
import com.example.projetobd.service.MovieService;
import com.example.projetobd.service.RoomService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/rooms")
public class RoomControllerFront {

    private final RoomService roomService;
    private final MovieService movieService;

    public RoomControllerFront(RoomService roomService, MovieService movieService) {
        this.roomService = roomService;
        this.movieService = movieService;
    }

    @GetMapping
    public String getRooms(Model model){
        model.addAttribute("rooms", roomService.getRooms());
        model.addAttribute("roomService", roomService);
        return "rooms";
    }

    @GetMapping("/{roomId}/new-session")
    public String addSessionToRoomForm(@PathVariable Long roomId, Model model) {
        model.addAttribute("movies", movieService.getMovies());
        model.addAttribute("sessionCreateRequest", new SessionCreateRequest(roomId));
        System.out.println("linha 38");
        return "add-session-to-room";
    }

    @PostMapping("/{roomId}/new-session")
    public String addSessionToRoom(SessionCreateRequest sessionCreateRequest) {
        roomService.addSessionToRoom(sessionCreateRequest);
        return "redirect:/rooms";
    }

    @GetMapping("/new")
    public String addRoomForm(Model model) {
        model.addAttribute("roomRequest", new RoomRequest());
        return "add-room";
    }

    @PostMapping
    public String addRoom(RoomRequest roomRequest, BindingResult bindingResult) {
        if(bindingResult.hasErrors()) {
            return "add-room";
        }
        roomService.createRoom(roomRequest.getCapacity());
        return "redirect:/rooms";
    }

    @GetMapping("/{roomId}/delete")
    public String deleteRoom(@PathVariable Long roomId) {
        roomService.deleteRoom(roomId);
        return "redirect:/rooms";
    }
}
