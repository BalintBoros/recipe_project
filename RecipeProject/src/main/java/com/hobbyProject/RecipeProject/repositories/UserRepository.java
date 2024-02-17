package com.hobbyProject.RecipeProject.repositories;

import com.hobbyProject.RecipeProject.domain.entities.UserEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<UserEntity, Long> {



}
