package com.amigoscode.movie;

import com.amigoscode.actor.Actor;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

public class Movie {

    private final Integer id;
    private final String name;
    private final List<Actor> actors;
    private final LocalDate releaseDate;

    public Movie(Integer id, String name, List<Actor> actors, LocalDate releaseDate) {
        this.id = id;
        this.name = name;
        this.actors = actors;
        this.releaseDate = releaseDate;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public List<Actor> getActors() {
        return actors;
    }

    public LocalDate getReleaseDate() {
        return releaseDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Movie movie = (Movie) o;
        return id.equals(movie.id) && name.equals(movie.name) && actors.equals(movie.actors) && releaseDate.equals(movie.releaseDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, actors, releaseDate);
    }

    @Override
    public String toString() {
        return "Movie{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", actors=" + actors +
                ", releaseDate=" + releaseDate +
                '}';
    }
}
