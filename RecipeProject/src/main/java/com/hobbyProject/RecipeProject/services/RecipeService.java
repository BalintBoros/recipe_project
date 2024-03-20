package com.hobbyProject.RecipeProject.services;

import com.hobbyProject.RecipeProject.domain.entities.RecipeEntity;

import java.util.List;
import java.util.Optional;

public interface RecipeService {

    RecipeEntity save(RecipeEntity recipeEntity);

    List<RecipeEntity> findAll();

    Optional<RecipeEntity> findById(Long id);

    boolean isExists(Long id);

    RecipeEntity particalUpdate(Long id, RecipeEntity recipeEntity);

    void deleteById(Long id);

}
