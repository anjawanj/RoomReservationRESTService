package com.anjawanj.data.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.anjawanj.persistence.model.Guest;

@Repository
public interface GuestRepository extends PagingAndSortingRepository<Guest, Long> {
	
	Guest findByEmailAddress(String emailAddress);

}