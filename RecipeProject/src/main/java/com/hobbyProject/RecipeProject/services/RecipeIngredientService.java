package com.hobbyProject.RecipeProject.services;

import com.hobbyProject.RecipeProject.domain.entities.RecipeIngredientEntity;

import java.util.List;
import java.util.Optional;

public interface RecipeIngredientService {
    RecipeIngredientEntity save(RecipeIngredientEntity recipeIngredientEntity);
    List<RecipeIngredientEntity> findAll();
    Optional<RecipeIngredientEntity> findById(Long id);
    boolean isExists(Long id);
    void deleteById(Long id);
}