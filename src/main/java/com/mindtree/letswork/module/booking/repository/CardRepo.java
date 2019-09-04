package com.mindtree.letswork.module.booking.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mindtree.letswork.module.booking.entity.Card;

@Repository
public interface CardRepo extends JpaRepository<Card, Integer> {

}
