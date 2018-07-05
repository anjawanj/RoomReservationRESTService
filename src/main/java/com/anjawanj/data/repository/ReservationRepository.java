package com.anjawanj.data.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.anjawanj.persistence.model.Guest;
import com.anjawanj.persistence.model.Reservation;

import java.sql.Date;
import java.util.List;

@Repository
public interface ReservationRepository extends PagingAndSortingRepository<Reservation,Long> {
    List<Reservation> findByStartDate(Date startDate);
    List<Reservation> findByEndDate(Date endDate);
    
    Page<Reservation> findAll(Pageable pageRequest);
    
    @Query("SELECT t.guests FROM Reservation t where t.startDate = ?1")
    List<List<Guest>> getGuestReservationInfo(Date startDate);
    
    Reservation findByRoomId(Long roomId);
    
}