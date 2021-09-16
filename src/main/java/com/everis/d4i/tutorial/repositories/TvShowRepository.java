package com.everis.d4i.tutorial.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.everis.d4i.tutorial.entities.TvShow;

@Repository
public interface TvShowRepository extends JpaRepository<TvShow, Long>, JpaSpecificationExecutor<TvShow> {

	@Query(value = "SELECT * FROM tv_shows t1 INNER JOIN categories_tv_shows t2 ON t1.id = t2.tv_shows_id WHERE t2.categories_id = ?1 ", nativeQuery = true)
	List<TvShow> findByCategoryId(Long categoryId);

//	example jpql :not work to get the tv show in every category
//	@Query(value = "SELECT t FROM TvShow t where t.categories.id = :categoryId")
//	List<TvShow> findByCategoryIdJPQL(@Param("categoryId")Long categoryId);

	boolean existsByIdAndCategories_Id(Long tvShowId, Long categoryId);

	// same the below
	@Query(value = "SELECT  t from TvShow t where t.year > :value")
	public List<TvShow> findAllByNames(@Param("value") String name);

	public List<TvShow> findAllByName(String name);

}
