package com.hobbyProject.RecipeProject.repositories;

import com.hobbyProject.RecipeProject.domain.entities.RecipeIngredientEntity;
import org.springframework.data.repository.CrudRepository;

public interface RecipeImageRepository extends CrudRepository<RecipeIngredientEntity, Long> {

}
