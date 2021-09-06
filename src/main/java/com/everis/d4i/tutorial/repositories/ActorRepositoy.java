package com.everis.d4i.tutorial.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.everis.d4i.tutorial.entities.Actor;

@Repository
public interface ActorRepositoy extends JpaRepository<Actor, Long> {

	boolean existsByIdAndChapters_Id(Long actorId, Long chapterId);

}
