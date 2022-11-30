package com.example.projetobd.service;

import com.example.projetobd.entity.Room;
import com.example.projetobd.entity.Session;
import com.example.projetobd.repository.MovieRepository;
import com.example.projetobd.repository.RoomRepository;
import com.example.projetobd.request.SessionCreateRequest;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
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
        session.setDate(sessionCreateRequest.getDate());
        session.setAvailableSeats(session.getRoom().getCapacity());
        session.setTickets(new ArrayList<>());
        //Check if the sessions that is being passed won't be in the same time interval as another session
        for (Session session_element: room.getSessions()){
            if (session_element.getLocalDateTime().equals(session.getLocalDateTime())){
                throw new RuntimeException("There is already a session in this room at this time");
            }
        }

        //add this session to the movie's sessions list
        session.getMovie().getSessions().add(session);
        room.getSessions().add(session);
        roomRepository.save(room);
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

   public List<Session> organizeSessionsByDate(List<Session> sessions_list){
       for (int i = 0; i < sessions_list.size(); i++){
            for (int j = 0; j < sessions_list.size(); j++){
                if (sessions_list.get(i).getDate().isBefore(sessions_list.get(j).getDate())){
                    Session aux = sessions_list.get(i);
                    sessions_list.set(i, sessions_list.get(j));
                    sessions_list.set(j, aux);
                }
            }
        }
        return sessions_list;
   }

   public List<Session> getSessionsThatAreNotFullAndStillRunning(Long roomId){
        Room room = roomRepository.findById(roomId).get();
        List<Session> sessions = room.getSessions();
        sessions.removeIf(s -> s.getTickets().size() == s.getRoom().getCapacity() || s.getLocalDateTime().isBefore(LocalDateTime.now()));
        return organizeSessionsByDate(sessions);
   }

   public List<Session> getSessionsThatAreNotFullAndStillRunning(){
        List<Room> rooms = roomRepository.findAll();
        List<Session> sessions = new ArrayList<>() ;
        for (Room room: rooms){
            sessions.addAll(room.getSessions());
        }
       sessions.removeIf(s -> s.getTickets().size() == s.getRoom().getCapacity() || s.getLocalDateTime().isBefore(LocalDateTime.now()));
        return organizeSessionsByDate(sessions);
   }
}



