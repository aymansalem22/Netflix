package com.everis.d4i.tutorial.controllers;

import java.util.List;

import com.everis.d4i.tutorial.exceptions.NetflixException;
import com.everis.d4i.tutorial.json.CategoryRest;
import com.everis.d4i.tutorial.responses.NetflixResponse;

public interface CategoryController {

	NetflixResponse<List<CategoryRest>> getCategories() throws NetflixException;

	NetflixResponse<CategoryRest> createCategory(CategoryRest categoryRest) throws NetflixException;

	NetflixResponse<CategoryRest> updateCategory(CategoryRest categoryRest) throws NetflixException;

	NetflixResponse<CategoryRest> updateCategoryById(Long id, CategoryRest categoryRest) throws NetflixException;

	NetflixResponse<CategoryRest> deleteCategoryById(Long id) throws NetflixException;

}