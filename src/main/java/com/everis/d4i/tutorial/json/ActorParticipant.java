package com.everis.d4i.tutorial.json;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ActorParticipant implements Serializable {

	private static final long serialVersionUID = 7408254321988460318L;

	ActorRest actor;
	// List<TvShowRest> tvShows;
	List<ChapterRest> chapters;

	public ActorRest getActor() {
		return actor;
	}

	public void setActor(ActorRest actor) {
		this.actor = actor;
	}

//	public List<TvShowRest> getTvShows() {
//		return tvShows;
//	}
//
//	public void setTvShows(List<TvShowRest> tvShows) {
//		this.tvShows = tvShows;
//	}

	public List<ChapterRest> getChapters() {
		return chapters;
	}

	public void setChapters(List<ChapterRest> chapters) {
		this.chapters = chapters;
	}

}
