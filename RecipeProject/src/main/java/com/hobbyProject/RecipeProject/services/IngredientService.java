package com.hobbyProject.RecipeProject.services;

import com.hobbyProject.RecipeProject.domain.entities.IngredientEntity;
import com.hobbyProject.RecipeProject.domain.entities.RecipeEntity;

import java.util.List;
import java.util.Optional;

public interface IngredientService {

    IngredientEntity save(IngredientEntity ingredientEntity);

    List<IngredientEntity> findAll();

    Optional<IngredientEntity> findById(Long id);

    boolean isExists(Long id);

//    If needed, rewrite this method!
//    RecipeEntity particalUpdate(Long id, RecipeEntity recipeEntity);

    void deleteById(Long id);

}
