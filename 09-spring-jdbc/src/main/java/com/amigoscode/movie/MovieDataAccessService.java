package com.amigoscode.movie;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public class MovieDataAccessService implements MovieDao {

    private final JdbcTemplate jdbcTemplate;

    public MovieDataAccessService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Movie> selectMovies() {
        final String sql = "SELECT * FROM movie LIMIT 100";
        return jdbcTemplate.query(sql, new MovieRowMapper());
    }

    @Override
    public int insertMovie(Movie movie) {
        final String sql = "INSERT INTO movie(name, release_date) VALUES (?, ?)";
        return jdbcTemplate.update(sql, movie.getName(), movie.getReleaseDate());
    }

    @Override
    public int deleteMovie(int id) {
        final String sql = "DELETE FROM movie WHERE id=?";
        return jdbcTemplate.update(sql, id);

    }

    @Override
    public Optional<Movie> selectMovieById(int id) {
        final String sql = "SELECT * FROM movie WHERE id = ?";
        List<Movie> movies = jdbcTemplate.query(sql, new MovieRowMapper(), id);
        return movies.stream().findFirst();
    }

}
