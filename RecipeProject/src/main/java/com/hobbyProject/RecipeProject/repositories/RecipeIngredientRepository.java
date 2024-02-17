package com.hobbyProject.RecipeProject.repositories;

import com.hobbyProject.RecipeProject.domain.entities.RecipeIngredientEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RecipeIngredientRepository extends CrudRepository<RecipeIngredientEntity, Long> {

}
