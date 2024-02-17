package com.hobbyProject.RecipeProject.repositories;

import com.hobbyProject.RecipeProject.domain.entities.IngredientEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IngredientRepository extends CrudRepository<IngredientEntity, Long> {

}
