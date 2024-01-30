package com.devsuperior.movieflix.dto;

import java.io.Serializable;

import com.devsuperior.movieflix.entities.Movie;
import com.devsuperior.movieflix.entities.Review;
import com.devsuperior.movieflix.entities.User;

public class ReviewDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long id;
	private String text;
	private Movie movie;
	private User user;
	
	public ReviewDTO() {
	}

	public ReviewDTO(Long id, String text, Movie movie, User user) {
		this.id = id;
		this.text = text;
		this.movie = movie;
		this.user = user;
	}
	
	public ReviewDTO(Review review) {
		id = review.getId();
		text = review.getText();
		movie = review.getMovie();
		user = review.getUser();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public Movie getMovie() {
		return movie;
	}

	public void setMovie(Movie movie) {
		this.movie = movie;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
}
