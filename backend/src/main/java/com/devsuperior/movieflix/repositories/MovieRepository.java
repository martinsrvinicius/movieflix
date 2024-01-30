package com.devsuperior.movieflix.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.devsuperior.movieflix.dto.MovieMinDTO;
import com.devsuperior.movieflix.entities.Movie;
import com.devsuperior.movieflix.projections.MovieMinProjection;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Long>{

	@Query(nativeQuery = true, value = "SELECT id, title, sub_Title, year, img_Url FROM tb_movie WHERE "
			+ "(genre_id = :genreId OR "
			+ ":genreId IS NULL) "
			+ "ORDER BY title ASC")                                                                         
	Page<MovieMinProjection> findAllCustomSQL(Long genreId, Pageable pageable);
	
	@Query("SELECT new com.devsuperior.movieflix.dto.MovieMinDTO(m.id, m.title, m.subTitle, m.year, m.imgUrl) "
			+ "FROM Movie m "
			+ "WHERE (m.genre.id = :genreId OR :genreId IS NULL) "
			+ "ORDER BY m.title ASC")
	Page<MovieMinDTO> findAllCustomJPQL(Long genreId, Pageable pageable);
}
