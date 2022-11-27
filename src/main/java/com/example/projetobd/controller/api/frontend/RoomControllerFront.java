package com.example.projetobd.controller.api.frontend;

import com.example.projetobd.request.SessionCreateRequest;
import com.example.projetobd.service.MovieService;
import com.example.projetobd.service.RoomService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/rooms")
public class RoomControllerFront {

    private final RoomService roomService;
    private final MovieService movieService;
    private final ModelMapper modelMapper;

    public RoomControllerFront(RoomService roomService, MovieService movieService, ModelMapper modelMapper) {
        this.roomService = roomService;
        this.movieService = movieService;
        this.modelMapper = modelMapper;
    }

    @RequestMapping
    public String getRooms(Model model){
        model.addAttribute("rooms", roomService.getRooms());
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
        System.out.println("I'm here at line 44");
        System.out.println(sessionCreateRequest);
        roomService.addSessionToRoom(sessionCreateRequest);
        System.out.println(sessionCreateRequest);
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

    @GetMapping("/{roomId}/edit")
    public String editRoomForm(@PathVariable Long roomId, Model model) {
        model.addAttribute("room", roomService.getRoomById(roomId));
        return "edit-room";
    }

    @PutMapping("/{roomId}/edit")
    public String editRoom(@PathVariable Long roomId, RoomRequest roomRequest, BindingResult bindingResult) {
        if(bindingResult.hasErrors()) {
            return "edit-room";
        }
        roomService.updateRoom(roomId, roomRequest.getCapacity());
        return "redirect:/rooms";
    }

    @GetMapping("/{roomId}/delete")
    public String deleteRoom(@PathVariable Long roomId) {
        roomService.deleteRoom(roomId);
        return "redirect:/rooms";
    }
}
