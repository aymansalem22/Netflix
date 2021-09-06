package com.everis.d4i.tutorial.repositories;

import java.time.Year;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.Predicate;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import com.everis.d4i.tutorial.entities.TvShow;
import com.everis.d4i.tutorial.entities.TvShowFilterRest;

@Component // to do autowired in other place
public class TvShowSpecification {

	public Specification<TvShow> getTvShowSpec(TvShowFilterRest request) {
		return (root, query, criteriaBuilder) -> {
			List<Predicate> predicates = new ArrayList<>();
			if (request.getName() != null) {
				// root is the expersion and the request is the value , mean name="name in
				// request"
				predicates.add(criteriaBuilder.equal(root.get("name"), request.getName()));
			}
			if (request.getStartYear() != null && request.getEndYear() != null) {
				predicates.add(criteriaBuilder.between(root.get("year"), Year.of(request.getStartYear()),
						Year.of(request.getEndYear())));
			}
			query.orderBy(criteriaBuilder.desc(root.get("id")));
			return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
		};
	}

}
