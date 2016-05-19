package com.deivydas.voroneckis.database;


import com.deivydas.voroneckis.domain.Room;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author vdeiv
 */
public class RoomMemoryDatabase implements IRoomDatabase {

    private static Map<Long, Room> roomMap = new HashMap<Long, Room>();
    long idNumber = 0;
    
    public RoomMemoryDatabase(){
        Room room = new Room();
        room.setStreet("Parkstraat");
        room.setArea(12);
        add(room);
        Room room2 = new Room();
        room2.setStreet("Brusselstraat");
        room2.setArea(20);
        add(room2);
    }
    
    @Override
    public List read() {
        return new ArrayList(roomMap.values());
    }

    @Override
    public void add(Room room) {
        roomMap.put(idNumber, room);
        room.setId(idNumber);
        idNumber++;
    }

    @Override
    public void delete(long id) {
        roomMap.remove(id);
    }

    @Override
    public void update(Room room) {
        roomMap.put(room.getId(), room);
    }

    @Override
    public Room getRoomById(long id){
        return roomMap.get(id);
    }

    @Override
    public void closeConnection() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
