package com.pluralsight.blog.data;

import com.pluralsight.blog.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RestResource;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;


@Repository
public interface PostRepository extends JpaRepository<Post,Long> {
     @RestResource(rel = "contains-title",path="containsTitle")
    default List<Post> findByTitleContaining(final String title) {
        return findAll().stream().filter(c -> c.getTitle().contains(title))
                .collect(Collectors.toList());
    }

    default List<Post> findByAuthor_Lastname(final String lastname) {
        return findAll().stream().filter(c -> c.getAuthor().getLastname().contains(lastname))
                .collect(Collectors.toList());
    }
}