package com.everis.d4i.tutorial.controllers;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import com.everis.d4i.tutorial.exceptions.NetflixException;
import com.everis.d4i.tutorial.json.TvShowRest;
import com.everis.d4i.tutorial.responses.NetflixResponse;

public interface TvShowController {

	NetflixResponse<List<TvShowRest>> getTvShowsByCategory(Long categoryId) throws NetflixException;

	NetflixResponse<TvShowRest> getTvShowById(Long id) throws NetflixException;

	NetflixResponse<List<TvShowRest>> findAll(Sort sort) throws NetflixException;

	NetflixResponse<List<TvShowRest>> findAllProgramtically(Sort sort) throws NetflixException;

	NetflixResponse<Page<TvShowRest>> findAllWithPagination(Pageable page) throws NetflixException;

	NetflixResponse<TvShowRest> addCategoryToTvShow(Long tvShowId, Long categoryId) throws NetflixException;

	NetflixResponse<TvShowRest> updateTvShowNameById(Long tvShowID, String tvShowName) throws NetflixException;

	NetflixResponse<TvShowRest> deleteTvShowById(Long tvShowID) throws NetflixException;

}
