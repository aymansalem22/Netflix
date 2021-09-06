package com.everis.d4i.tutorial.json;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ActorRest implements Serializable {

	private static final long serialVersionUID = 2562292635410148858L;

	private Long id;
	private String name;
	private Long age;
	private String nationality;
	// choose 1 to appear which series particpate in:
	// private List<ChapterRest> chapters;

//	public List<ChapterRest> getChapters() {
//		return chapters;
//	}
//
//	public void setChapters(List<ChapterRest> chapters) {
//		this.chapters = chapters;
//	}

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

	public Long getAge() {
		return age;
	}

	public void setAge(Long age) {
		this.age = age;
	}

	public String getNationality() {
		return nationality;
	}

	public void setNationality(String nationality) {
		this.nationality = nationality;
	}

}
