package com.example.projetobd.controller.api;

import com.example.projetobd.entity.Room;
import com.example.projetobd.request.SessionCreateRequest;
import com.example.projetobd.service.RoomService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/room")
public class RoomController {

    private final RoomService roomService;

    public RoomController(RoomService roomService) {
        this.roomService = roomService;
    }

    @PostMapping("/{capacity}")
    public void createRoom(@PathVariable Integer capacity) {
        roomService.createRoom(capacity);
    }

    @PostMapping("/session/{roomId}")
    public void addSessionToRoom(@PathVariable Long roomId, @RequestBody SessionCreateRequest sessionCreateRequest) {
        roomService.addSessionToRoom(roomId, sessionCreateRequest);
    }

    @DeleteMapping("/session/{roomId}/{sessionId}")
    public void removeSessionFromRoom(@PathVariable Long roomId, @PathVariable Long sessionId) {
        roomService.removeSessionFromRoom(roomId, sessionId);
    }

    @GetMapping("/generate-random-room")
    public String generateRandomRoom() {
        return roomService.generateRandomRoom();
    }

    @GetMapping
    public List<Room> getRooms() {
        return roomService.getRooms();
    }

    @GetMapping("/{roomId}")
    public Room getRoomById(@PathVariable Long roomId) {
        return roomService.getRoomById(roomId);
    }
}
