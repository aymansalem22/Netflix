package com.everis.d4i.tutorial.controllers;

import java.util.List;

import com.everis.d4i.tutorial.entities.TvShowFilterRest;
import com.everis.d4i.tutorial.exceptions.NetflixException;
import com.everis.d4i.tutorial.json.TvShowRest;
import com.everis.d4i.tutorial.responses.NetflixResponse;

public interface TvShowFilterController {

	NetflixResponse<List<TvShowRest>> getTvShowFiltered(TvShowFilterRest filter) throws NetflixException;

	NetflixResponse<List<TvShowRest>> getTvShowFilteredByQueryNamedMethod(String name) throws NetflixException;

}
