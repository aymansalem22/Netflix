package com.everis.d4i.tutorial.services;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import com.everis.d4i.tutorial.entities.TvShowFilterRest;
import com.everis.d4i.tutorial.exceptions.NetflixException;
import com.everis.d4i.tutorial.json.TvShowRest;

public interface TvShowService {

	List<TvShowRest> getTvShowsByCategory(Long categoryId) throws NetflixException;

	TvShowRest getTvShowById(Long id) throws NetflixException;

	// this already has sort so we can sort the pages
	Page<TvShowRest> getTvShowWithPage(Pageable page) throws NetflixException;

	List<TvShowRest> findAll(Sort sort) throws NetflixException;

	List<TvShowRest> getTvShowFiltered(TvShowFilterRest filter) throws NetflixException;

	List<TvShowRest> getTvShowFilteredByQueryNamedMethod(String name) throws NetflixException;

	List<TvShowRest> findAllProgramtically(Sort sort) throws NetflixException;

	TvShowRest addCategoryToTvShow(Long tvShowId, Long categoryId) throws NetflixException;

	TvShowRest updateTvShowNameById(Long tvShowId, String tvShowName) throws NetflixException;

	void deleteTvShowById(Long id) throws NetflixException;

}
