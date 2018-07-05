package com.anjawanj.data.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.anjawanj.persistence.model.Room;

@Repository
public interface RoomRepository extends CrudRepository<Room, Long>{
    Room findByNumber(String number);
    
    @Query(value="SELECT * FROM Room where IS_RESERVED = false limit 1",nativeQuery=true)
    Room getAvailableRoom(); 
}
