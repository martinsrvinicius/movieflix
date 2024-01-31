package com.devsuperior.movieflix.services;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.devsuperior.movieflix.dto.ReviewDTO;
import com.devsuperior.movieflix.entities.Review;
import com.devsuperior.movieflix.entities.User;
import com.devsuperior.movieflix.repositories.MovieRepository;
import com.devsuperior.movieflix.repositories.ReviewRepository;
import com.devsuperior.movieflix.services.exceptions.ResourceNotFoundException;

@Service
public class ReviewService implements Serializable {
	private static final long serialVersionUID = 1L;

	@Autowired
	private ReviewRepository repository;

	@Autowired
	private MovieRepository movieRepository;

	@Autowired
	private AuthService authService;

	@Transactional(readOnly = true)
	public List<ReviewDTO> reviews(Long id) {
		List<ReviewDTO> list = repository.findReviews(id);
		return list;
	}

	@Transactional
	public ReviewDTO insert(ReviewDTO dto) {
		User user = authService.authenticated();
		try {
		Review entity = new Review();
		copyDtoToEntity(dto, entity, user);
		entity = repository.save(entity);
		return new ReviewDTO(entity);
		} catch(Exception e) {
			throw new ResourceNotFoundException("Entity not found");
		}
	}

	private void copyDtoToEntity(ReviewDTO dto, Review entity, User user) {
		entity.setMovie(movieRepository.getOne(dto.getMovieId()));
		entity.setText(dto.getText());
		entity.setUser(user);
	}
}
