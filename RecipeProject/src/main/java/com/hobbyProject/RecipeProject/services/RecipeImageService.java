package com.hobbyProject.RecipeProject.services;

import com.hobbyProject.RecipeProject.domain.entities.RecipeImageEntity;

import java.util.List;
import java.util.Optional;

public interface RecipeImageService {
    RecipeImageEntity save(RecipeImageEntity recipeImageEntity);
    List<RecipeImageEntity> findAll();
    Optional<RecipeImageEntity> findById(Long id);
    boolean isExists(Long id);
    void deleteById(Long id);
}