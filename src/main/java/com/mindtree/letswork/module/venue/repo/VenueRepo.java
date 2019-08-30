package com.mindtree.letswork.module.venue.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mindtree.letswork.module.venue.entity.Venue;

@Repository
public interface VenueRepo extends JpaRepository<Venue, Integer> {

}
