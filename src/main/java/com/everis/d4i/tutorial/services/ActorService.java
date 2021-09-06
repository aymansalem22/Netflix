package com.everis.d4i.tutorial.services;

import java.util.List;

import com.everis.d4i.tutorial.exceptions.NetflixException;
import com.everis.d4i.tutorial.json.ActorParticipant;
import com.everis.d4i.tutorial.json.ActorRest;

public interface ActorService {

	List<ActorRest> getActors() throws NetflixException;

	ActorRest getActorById(Long id) throws NetflixException;

	ActorRest createActor(ActorRest actorRest) throws NetflixException;

	ActorRest updateActorById(Long id, ActorRest actorRest) throws NetflixException;

	ActorParticipant AddActorToChapter(Long idActor, Long idChapter) throws NetflixException;

	ActorRest AddActorToChapters(Long idActor, Long idChapter) throws NetflixException;

	void deleteActorById(Long id) throws NetflixException;

}
