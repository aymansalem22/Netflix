package com.everis.d4i.tutorial.services.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.everis.d4i.tutorial.entities.Category;
import com.everis.d4i.tutorial.exceptions.InternalServerErrorException;
import com.everis.d4i.tutorial.exceptions.NetflixException;
import com.everis.d4i.tutorial.json.CategoryRest;
import com.everis.d4i.tutorial.repositories.CategoryRepository;
import com.everis.d4i.tutorial.services.CategoryService;
import com.everis.d4i.tutorial.utils.constants.ExceptionConstants;

@Service
public class CategoryServiceImpl implements CategoryService {

	private static final Logger LOGGER = LoggerFactory.getLogger(CategoryServiceImpl.class);

	@Autowired
	private CategoryRepository categoryRepository;

	private ModelMapper modelMapper = new ModelMapper();

	public List<CategoryRest> getCategories() throws NetflixException {

		// first options solution with array list:
		/*
		 * List<CategoryRest> list=new ArrayList<CategoryRest>(); List<Category>
		 * categoryList=new ArrayList<Category>(); for(int i=0;i<categoryList.size;i++){
		 * categoryRest c=modelMapper.map(categoryList.get(i),CategoryRest.class(); }
		 *
		 */

		// stream loop like for each and arraylist but more eficence and perfomance and
		// must deal with
		// loop
		// stream have function map to connect so category or any name must be equal
		// with other model mapper and convert it to rest and will return to
		// the variable category ,the stream increase depend the length of array--stream
		// on conccurency working
		// collect(Collectors.toList()); every object return from strem will put it in
		// list collection
		return categoryRepository.findAll().stream().map(category -> modelMapper.map(category, CategoryRest.class))// recieve
																													// which
																													// convert
																													// to
																													// defintion
																													// class
																													// restcategory
				.collect(Collectors.toList());

	}

	public CategoryRest createCategories(final CategoryRest categoryRest) throws NetflixException {
		// another way
		/*
		 * convert from rest objecct java to java entity object (*both is java) Category
		 * category=modelMapper.map(categoryRest,Category.Class)
		 * 
		 * and here the try and catch to capth the any error in jpa reposiroty so the
		 * throw netflix cant see the expecetion from db
		 */
		Category category = new Category();
		category.setName(categoryRest.getName());// here i just want name bc id in newcategory generate auto
		try {
			category = categoryRepository.save(category);
		} catch (final Exception e) {
			LOGGER.error(ExceptionConstants.INTERNAL_SERVER_ERROR, e);
			throw new InternalServerErrorException(ExceptionConstants.INTERNAL_SERVER_ERROR);
		}
		return modelMapper.map(category, CategoryRest.class);
	}

	@Override
	public CategoryRest updateCategories(CategoryRest categoryRest) throws NetflixException {
		Category category = modelMapper.map(categoryRest, Category.class);// convert categoryRest to Category and with
																			// that i retrieve id and name
		// if(category.getId()==null) {
		// throw new OBJECT_NOT_EXIST(ExceptionConstants.INTERNAL_SERVER_ERROR);
		// }
		try {
			category = categoryRepository.save(category);// there are two save one for create(now that if id=null ) and
															// one for update(if exists id)
		} catch (final Exception e) {
			LOGGER.error(ExceptionConstants.INTERNAL_SERVER_ERROR, e);
			throw new InternalServerErrorException(ExceptionConstants.INTERNAL_SERVER_ERROR);
		}
		return modelMapper.map(category, CategoryRest.class);
	}

	@Override
	public CategoryRest updateCategoryById(Long id, CategoryRest categoryRest) throws NetflixException {
		categoryRest.setId(id);
		// in mapper: to change between two object must have common things
		Category category = modelMapper.map(categoryRest, Category.class);

		try {
			category = categoryRepository.save(category);
		} catch (final Exception e) {
			LOGGER.error(ExceptionConstants.INTERNAL_SERVER_ERROR, e);
			throw new InternalServerErrorException(ExceptionConstants.INTERNAL_SERVER_ERROR);
		}
		return modelMapper.map(category, CategoryRest.class);
	}

	@Override
	public void deleteCategoriesById(Long id) throws NetflixException {
		categoryRepository.deleteById(id);
	}

}
