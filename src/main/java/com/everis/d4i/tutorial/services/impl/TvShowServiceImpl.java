package com.everis.d4i.tutorial.services.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.persistence.EntityNotFoundException;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.everis.d4i.tutorial.entities.Category;
import com.everis.d4i.tutorial.entities.TvShow;
import com.everis.d4i.tutorial.entities.TvShowFilterRest;
import com.everis.d4i.tutorial.exceptions.DuplicateException;
import com.everis.d4i.tutorial.exceptions.InternalServerErrorException;
import com.everis.d4i.tutorial.exceptions.NetflixException;
import com.everis.d4i.tutorial.exceptions.NotFoundException;
import com.everis.d4i.tutorial.json.TvShowRest;
import com.everis.d4i.tutorial.repositories.CategoryRepository;
import com.everis.d4i.tutorial.repositories.TvShowRepository;
import com.everis.d4i.tutorial.repositories.TvShowSpecification;
import com.everis.d4i.tutorial.services.TvShowService;
import com.everis.d4i.tutorial.utils.constants.ExceptionConstants;

@Service
public class TvShowServiceImpl implements TvShowService {
	private static final Logger LOGGER = LoggerFactory.getLogger(TvShowServiceImpl.class);
	@Autowired
	private TvShowRepository tvShowRepository;
	@Autowired
	private TvShowSpecification tvShowSpec;

	@Autowired
	private CategoryRepository categoryRepository;

	private ModelMapper modelMapper = new ModelMapper();

	@Override
	public List<TvShowRest> getTvShowsByCategory(Long categoryId) throws NetflixException {

		return tvShowRepository.findByCategoryId(categoryId).stream()
				.map(tvShow -> modelMapper.map(tvShow, TvShowRest.class)).collect(Collectors.toList());

	}

	@Override
	public TvShowRest getTvShowById(Long id) throws NetflixException {

		try {
			return modelMapper.map(tvShowRepository.getOne(id), TvShowRest.class);
		} catch (EntityNotFoundException entityNotFoundException) {
			throw new NotFoundException(entityNotFoundException.getMessage());
		}

	}

	@Override
	public TvShowRest updateTvShowNameById(Long tvShowId, String tvShowName) throws NetflixException {
		Optional<TvShow> tvShow = tvShowRepository.findById(tvShowId);
		if (tvShow.isPresent()) {
			TvShow tvShowExists = tvShow.get();
			tvShowExists.setName(tvShowName);
			tvShowRepository.save(tvShowExists);
			return modelMapper.map(tvShowExists, TvShowRest.class);

		}
		throw new NotFoundException("TV SHOW ID NOT FOUND " + tvShowId);
	}

	@Override
	public TvShowRest addCategoryToTvShow(Long tvShowId, Long categoryId) throws NetflixException {
		Optional<TvShow> tvShow = tvShowRepository.findById(tvShowId);
		if (tvShow.isPresent()) {
			Optional<Category> category = categoryRepository.findById(categoryId);
			if (category.isPresent()) {
				if (tvShowRepository.existsByIdAndCategories_Id(tvShow.get().getId(), categoryId)) {
					throw new DuplicateException("Category Exist on tvShow" + categoryId);
				}
				TvShow tvShowExists = tvShow.get();
				tvShowExists.getCategories().add(category.get());
				try {// must be this to catch any error after save the transaction and do all the
						// handle expects
					tvShowRepository.save(tvShowExists);
				} catch (final Exception e) {
					LOGGER.error(ExceptionConstants.INTERNAL_SERVER_ERROR, e);
					throw new InternalServerErrorException(ExceptionConstants.INTERNAL_SERVER_ERROR);
				}
				return modelMapper.map(tvShowExists, TvShowRest.class);
			}
			throw new NotFoundException("Category ID NOT FOUND " + categoryId);
		}
		throw new NotFoundException("TV SHOW ID NOT FOUND " + tvShowId);
	}

	@Override
	public List<TvShowRest> findAll(Sort sort) throws NetflixException {
//	tvshow in mapper mean:	for (TvShow tvshow : tvShowRepository.findAll(sort)) {
//			modelMapper.map(tvshow, TvShowRest.class);
//		}
		return tvShowRepository.findAll(sort).stream().map(tvshow -> modelMapper.map(tvshow, TvShowRest.class))
				.collect(Collectors.toList());
	}

	@Override
	public void deleteTvShowById(Long id) throws NetflixException {
		tvShowRepository.deleteById(id);

	}

	@Override
	public List<TvShowRest> findAllProgramtically(Sort sort) throws NetflixException {
		/*
		 * parallelStream:working in same object in same time
		 * 
		 */
//		final Sort sortProgramtically = Sort.by(Sort.Direction.ASC, "name").
//				and(Sort.by(Sort.Direction.DESC, "year"));
		return tvShowRepository.findAll(sort).parallelStream().map(tvShow -> modelMapper.map(tvShow, TvShowRest.class))
				.collect(Collectors.toList());
	}

	@Override
	public List<TvShowRest> getTvShowFiltered(TvShowFilterRest filter) throws NetflixException {
		Specification<TvShow> spec = tvShowSpec.getTvShowSpec(filter);
		return tvShowRepository.findAll(spec).stream().map(tvShow -> modelMapper.map(tvShow, TvShowRest.class))
				.collect(Collectors.toList());
	}

	@Override
	public List<TvShowRest> getTvShowFilteredByQueryNamedMethod(String name) throws NetflixException {
		// mean the null in query thats is value the word null mean name when equal
		// null!! so return nothing data
		List<TvShowRest> tvShowRests = tvShowRepository.findAllByName(name).stream()
				.map(tvShow -> modelMapper.map(tvShow, TvShowRest.class)).collect(Collectors.toList());
		return tvShowRests;
	}

	@Override
	public Page<TvShowRest> getTvShowWithPage(Pageable page) throws NetflixException {
		Page<TvShowRest> tvPage = tvShowRepository.findAll(page)
				.map(tvShow -> modelMapper.map(tvShow, TvShowRest.class));
		return tvPage;

		/*
		 * other way to do paginaton Page<TvShowRest> Page<TvShowRest>
		 * page=tvShowRespository.findAll(page) .map(tvShow ->
		 * modelMapper.map(tvShow,TvShowRest.class)); return page;
		 */
	}

	/*
	 * other way to check if exist category in tv show with function
	 * 1-if(checkCategoryExistInTvShow.get(),categoryId){ throw new
	 * DuplicateException(" category existe on tvshow "+categoryId); }
	 * 
	 * 2-private boolean cehckCategoryExistInTvShow(TvShow tvShow, Long categoryID)
	 * { Category c = tvShow.getCategories().stream().filter(carnet ->
	 * categoryID.equals(carnet.getId())).findFirst() .orElse(null);//if the array
	 * list have one return with "findfirst" else if no return null"orelse(null)"
	 * return c != null;//if category not null return true , if category null return
	 * false }
	 * 
	 * 3- do exception class " package com.everis.d4i.tutorial.exceptions;
	 * 
	 * import java.util.Arrays;
	 * 
	 * import org.springframework.http.HttpStatus;
	 * 
	 * import com.everis.d4i.tutorial.dto.ErrorDto;
	 * 
	 * public class DuplicateException extends NetflixException { private static
	 * final long serialVersionUID = -6870732210014274010L;
	 * 
	 * public DuplicateException(final String message) {
	 * super(HttpStatus.CONFLICT.value(), message); }
	 * 
	 * public DuplicateException(final String message, final ErrorDto data) {
	 * super(HttpStatus.CONFLICT.value(), message, Arrays.asList(data)); } }
	 * 
	 * "
	 * 
	 * 
	 * 
	 */
}
