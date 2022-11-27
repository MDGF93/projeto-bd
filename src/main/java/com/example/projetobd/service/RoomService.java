package com.example.projetobd.service;

import com.example.projetobd.entity.Room;
import com.example.projetobd.entity.Session;
import com.example.projetobd.repository.MovieRepository;
import com.example.projetobd.repository.RoomRepository;
import com.example.projetobd.request.SessionCreateRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoomService {

    private final RoomRepository roomRepository;
    private final MovieRepository movieRepository;

    public RoomService(RoomRepository roomRepository, MovieRepository movieRepository) {
        this.roomRepository = roomRepository;
        this.movieRepository = movieRepository;
    }


    public void createRoom(Integer capacity) {
        Room room = new Room(capacity);
        roomRepository.save(room);
    }

    public void addSessionToRoom(SessionCreateRequest sessionCreateRequest) {
        Session session = new Session();
        session.setMovie(movieRepository.findById(sessionCreateRequest.getMovieId()).get());
        session.setStartTime(sessionCreateRequest.getStartTime());
        Room room = roomRepository.findById(sessionCreateRequest.getRoomId()).get();
        session.setRoom(room);
        session.setSessionMovieTitle(session.getMovie().getTitleBr());
        session.setEndTime(session.getStartTime().plusMinutes(session.getMovie().getLength()));
        //Check if the sessions that is being passed won't be in the same time interval as another session
        if (room.getSessions().stream().anyMatch(s -> s.getStartTime().isBefore(session.getEndTime()) && s.getEndTime().isAfter(session.getStartTime()))) {
            throw new RuntimeException("The session that is being passed is in the same time interval as another session");
        }
        else {
            //add this session to the movie's sessions list
            session.getMovie().getSessions().add(session);
            room.getSessions().add(session);
            roomRepository.save(room);
        }
    }

    public void removeSessionFromRoom(Long roomId, Long sessionId) {
        Room room = roomRepository.findById(roomId).get();
        room.getSessions().removeIf(s -> s.getId().equals(sessionId));
        roomRepository.save(room);
    }

    public String generateRandomRoom() {
        //generate a room with an even number of seats between 50 and 150
        int capacity = (int) (Math.random() * 100 + 50);
        if (capacity % 2 != 0) {
            capacity++;
        }
        Room room = new Room(capacity);
        roomRepository.save(room);
        return "Room with capacity " + capacity + " created";
    }

    public List<Room> getRooms() {
        return roomRepository.findAll();
    }

    public Room getRoomById(Long roomId) {
        return roomRepository.findById(roomId).get();
    }

    public void updateRoom(Long roomId, Integer capacity) {
        Room room = roomRepository.findById(roomId).get();
        room.setCapacity(capacity);
        roomRepository.save(room);
    }

    public void deleteRoom(Long roomId) {
        roomRepository.deleteById(roomId);
    }
}



