package com.everis.d4i.tutorial.controllers;

import java.util.List;

import com.everis.d4i.tutorial.exceptions.NetflixException;
import com.everis.d4i.tutorial.json.ActorParticipant;
import com.everis.d4i.tutorial.json.ActorRest;
import com.everis.d4i.tutorial.responses.NetflixResponse;

public interface ActorController {

	NetflixResponse<List<ActorRest>> getActors() throws NetflixException;

	NetflixResponse<ActorRest> getActorById(Long id) throws NetflixException;

	NetflixResponse<ActorRest> CreateActor(ActorRest actorRest) throws NetflixException;

	NetflixResponse<ActorRest> UpdateActorById(Long id, ActorRest actorRest) throws NetflixException;

	NetflixResponse<ActorParticipant> AddActorToChapter(Long idActor, Long idChapters) throws NetflixException;

	// the way explain in actorrest must discomment
	NetflixResponse<ActorRest> AddActorToChapterAnoterOption(Long idActor, Long idChapter) throws NetflixException;

	NetflixResponse<ActorRest> deleteActorById(Long id) throws NetflixException;

}
