package com.everis.d4i.tutorial.services.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.everis.d4i.tutorial.entities.Actor;
import com.everis.d4i.tutorial.entities.Chapter;
import com.everis.d4i.tutorial.exceptions.DuplicateException;
import com.everis.d4i.tutorial.exceptions.InternalServerErrorException;
import com.everis.d4i.tutorial.exceptions.NetflixException;
import com.everis.d4i.tutorial.exceptions.NotFoundException;
import com.everis.d4i.tutorial.json.ActorParticipant;
import com.everis.d4i.tutorial.json.ActorRest;
import com.everis.d4i.tutorial.mappers.ActorParticpateMapper;
import com.everis.d4i.tutorial.repositories.ActorRepositoy;
import com.everis.d4i.tutorial.services.ActorService;
import com.everis.d4i.tutorial.services.ChapterService;
import com.everis.d4i.tutorial.utils.constants.ExceptionConstants;

@Service
public class ActorServiceImp implements ActorService {

	private static final Logger LOGGER = LoggerFactory.getLogger(CategoryServiceImpl.class);
	@Autowired
	private ActorRepositoy actorRepository;

	@Autowired
	private ChapterService chapterService;

	private ModelMapper modelMapper = new ModelMapper();

	@Override
	public List<ActorRest> getActors() throws NetflixException {
		
     List <Actor>actors=actorRepository.findAll();
     //we do this check if null or not to testing when return null and bc the stream must pass not null
     //bc stream isnt safe with nulls
     if(actors!=null) {
		return actors.stream().map(actor -> modelMapper.map(actor, ActorRest.class))
				.collect(Collectors.toList());}
     else {
    	 return null;
     }
	}

	@Override
	public ActorRest getActorById(Long id) throws NetflixException {
		// option 1 exactly to do as the app better
		Actor actor = actorRepository.findById(id)
				.orElseThrow(() -> new NotFoundException("Actor id not found - " + id));
		return modelMapper.map(actor, ActorRest.class);

		// option 2
//		Optional<Actor> actor=actorRepository.findById(id);
//		if(actor.isPresent()) {
//			return modelMapper.map(actorRepository.findById(id).get(), ActorRest.class);
//		}
//		throw new NotFoundException("Actor id not found - "+id);

		// option 3we cant handle correctt the exception so above is more correctly
//		try {
//			//if use actorRepository.getone(id) when insert id not in db cant catch it and with findby id with 
//			//type optional we can handle the expetion
//			return modelMapper.map(actorRepository.findById(id).get(), ActorRest.class);
//		} catch (EntityNotFoundException entityNotFoundException) {
//			throw new NotFoundException(entityNotFoundException.getMessage());
//			//TASK EXCEPTION 
//		}
	}

	@Override
	public ActorRest createActor(ActorRest actorRest) throws NetflixException {
		Actor actor = modelMapper.map(actorRest, Actor.class);
		try {
			actor = actorRepository.save(actor);
		} catch (Exception e) {
			LOGGER.error(ExceptionConstants.INTERNAL_SERVER_ERROR, e);
			throw new InternalServerErrorException(ExceptionConstants.INTERNAL_SERVER_ERROR);
		}

		return modelMapper.map(actor, ActorRest.class);
	}

	@Override
	public ActorRest updateActorById(Long id, ActorRest actorRest) throws NetflixException {
		Optional<Actor> actor = actorRepository.findById(id);// actorRest.setId(id);
		if (actor.isPresent()) {
			actorRest.setId(id);
			Actor actorexists = modelMapper.map(actorRest, Actor.class);
			actorexists = actorRepository.save(actorexists);
			return modelMapper.map(actorexists, ActorRest.class);
		}
		throw new NotFoundException("Actor id not found - " + id);

	}

	@Override
	public ActorParticipant AddActorToChapter(Long idActor, Long idChapter) throws NetflixException {
		Actor actor = actorRepository.findById(idActor)
				.orElseThrow(() -> new NotFoundException("Actor id not found - " + idActor));
		if (actorRepository.existsByIdAndChapters_Id(idActor, idChapter)) {
			throw new DuplicateException("Actor id  found - " + idActor);
		}
		Chapter chapter = chapterService.findChapterById(idChapter);

		actor.getChapters().add(chapter);
		actorRepository.save(actor);
		ActorParticpateMapper mapper = new ActorParticpateMapper();
		return mapper.mapActorToActorParticipant(actor);

	}

	@Override
	public ActorRest AddActorToChapters(Long idActor, Long idChapter) throws NetflixException {
		Actor actor = actorRepository.findById(idActor)
				.orElseThrow(() -> new NotFoundException("actor id not found -" + idActor));
		if (actorRepository.existsByIdAndChapters_Id(idActor, idChapter)) {
			throw new DuplicateException("actor id found -" + idActor);
		}
		Chapter chapter = chapterService.findChapterById(idChapter);
		actor.getChapters().add(chapter);
		actorRepository.save(actor);
		return modelMapper.map(actor, ActorRest.class);
	}

	@Override
	public void deleteActorById(Long id) throws NetflixException {
		Actor actor = actorRepository.findById(id)
				.orElseThrow(() -> new NotFoundException("Actor id not found - " + id));
		actorRepository.delete(actor);

	}

}
