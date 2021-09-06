package com.everis.d4i.tutorial.entities;

import java.io.Serializable;
import java.time.Year;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "tv_shows")
public class TvShow implements Serializable {

	private static final long serialVersionUID = 4916713904971425156L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "NAME")
	private String name;

	@Column(name = "SHORT_DESC", nullable = true)
	private String shortDescription;

	@Column(name = "LONG_DESC", nullable = true)
	private String longDescription;

	@Column(name = "YEAR")
	private Year year;

	@Column(name = "RECOMMENDED_AGE")
	private byte recommendedAge;

	@Column(name = "ADVERTISING", nullable = true)
	private String advertising;

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "tvShow")
	private List<Season> seasons;

	@ManyToMany(fetch = FetchType.LAZY)

	@JoinTable(name = "categories_tv_shows", joinColumns = @JoinColumn(name = "tv_shows_id"), inverseJoinColumns = @JoinColumn(name = "categories_id"))
	private List<Category> categories;

	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "tv_shows_rewards", joinColumns = @JoinColumn(name = "tv_shows_id"), inverseJoinColumns = @JoinColumn(name = "rewards_id"))
	private List<Reward> rewards;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getShortDescription() {
		return shortDescription;
	}

	public void setShortDescription(String shortDescription) {
		this.shortDescription = shortDescription;
	}

	public String getLongDescription() {
		return longDescription;
	}

	public void setLongDescription(String longDescription) {
		this.longDescription = longDescription;
	}

	public Year getYear() {
		return year;
	}

	public void setYear(Year year) {
		this.year = year;
	}

	public byte getRecommendedAge() {
		return recommendedAge;
	}

	public void setRecommendedAge(byte recommendedAge) {
		this.recommendedAge = recommendedAge;
	}

	public String getAdvertising() {
		return advertising;
	}

	public void setAdvertising(String advertising) {
		this.advertising = advertising;
	}

	public List<Season> getSeasons() {
		return seasons;
	}

	public void setSeasons(List<Season> seasons) {
		this.seasons = seasons;
	}

	public List<Category> getCategories() {
		return categories;
	}

	public void setCategories(List<Category> categories) {
		this.categories = categories;
	}

	public List<Reward> getRewards() {
		return rewards;
	}

	public void setRewards(List<Reward> rewards) {
		this.rewards = rewards;
	}

	@Override
	public String toString() {
		return "TvShow [id=" + id + ", name=" + name + ", shortDescription=" + shortDescription + ", longDescription="
				+ longDescription + ", year=" + year + ", recommendedAge=" + recommendedAge + ", advertising="
				+ advertising + ", seasons=" + seasons + ", categories=" + categories + "]";
	}

}

//FetchType.eager retrieve all parametros belong to table
//FetchType.LAZY retrieve one parametro to not be load on database
//jointable id of the table where i stand ..>joinColumns = @JoinColumn(name = "tv_shows_id"),
//other table that i want the relation with it ==>inverseJoinColumns = @JoinColumn(name = "categories_id"))

//to do FORIEGN key must do this comment in myysqlbranch :
/*
 * alter table categories_tv_shows add FORIEGN KEY (categories_id) REFERNCES
 * categories(id)
 * 
 * alter table categories_tv_shows add FORIEGN KEY (tv_shows_id) REFERNCES
 * tv_shows(id)
 */
