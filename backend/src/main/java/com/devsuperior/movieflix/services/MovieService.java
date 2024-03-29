package com.devsuperior.movieflix.services;

import java.io.Serializable;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.devsuperior.movieflix.dto.MovieDTO;
import com.devsuperior.movieflix.dto.MovieMinDTO;
import com.devsuperior.movieflix.entities.Movie;
import com.devsuperior.movieflix.projections.MovieMinProjection;
import com.devsuperior.movieflix.repositories.MovieRepository;
import com.devsuperior.movieflix.services.exceptions.ResourceNotFoundException;

@Service
public class MovieService implements  Serializable {
	private static final long serialVersionUID = 1L;

	@Autowired
	private MovieRepository repository;
	
	@Transactional(readOnly = true)
	public Page<MovieMinDTO> findAllPagedJPQL(Long genreId, Pageable pageable) {
		Long genre = (genreId == 0) ? null : genreId;
		Page<MovieMinDTO> page = repository.findAllCustomJPQL(genre, pageable);
		return page;
	}
	
	@Transactional(readOnly = true)
	public Page<MovieMinDTO> findAllPagedSQL(Long genreId, Pageable pageable) {
		Long genre = (genreId == 0) ? null : genreId;
		Page<MovieMinProjection> page= repository.findAllCustomSQL(genre, pageable);
		return page.map(x -> new MovieMinDTO(x));
	}
	
	@Transactional(readOnly = true)
	public MovieDTO findById(Long id) {
		Optional<Movie> obj = repository.findById(id);
		Movie entity = obj.orElseThrow(() -> new ResourceNotFoundException("Entity not found"));
		return new MovieDTO(entity, entity.getGenre());
	}
}
