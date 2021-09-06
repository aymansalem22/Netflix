package com.everis.d4i.tutorial.mappers;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;

import com.everis.d4i.tutorial.entities.Actor;
import com.everis.d4i.tutorial.json.ActorParticipant;
import com.everis.d4i.tutorial.json.ActorRest;
import com.everis.d4i.tutorial.json.ChapterRest;

public class ActorParticpateMapper {

	private ModelMapper modelMapper = new ModelMapper();

	public ActorParticipant mapActorToActorParticipant(Actor actor) {
		ActorParticipant actorParticipant = new ActorParticipant();

		actorParticipant.setActor(modelMapper.map(actor, ActorRest.class));

		// must change from chapterrest to chapterlistrest
		List<ChapterRest> chapterRestList = actor.getChapters().stream()// return list from chapter
				.map(chapter -> modelMapper.map(chapter, ChapterRest.class)).collect(Collectors.toList());
		// must change to restchaperlist for that abovve we do list
		actorParticipant.setChapters(chapterRestList);

		return actorParticipant;

//		List<TvShowRest> tvShowsRestList =
//				actor.getChapters()
//			        .stream()
//			        .map(chapter -> modelMapper.map(chapter.getSeason().getTvShow(), TvShowRest.class))//retrieve seassons and tvshow to change them to tvshowlist
//			        .distinct()
//			        .collect(Collectors.toList());

		// actorParticipant.setTvShows(tvShowsRestList);

	}
}
