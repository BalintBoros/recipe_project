package com.hobbyProject.RecipeProject.services;

import com.hobbyProject.RecipeProject.domain.entities.UserEntity;

import java.util.List;
import java.util.Optional;

public interface UserService {

    UserEntity save(UserEntity userEntity);

    List<UserEntity> findAll();

    Optional<UserEntity> findById(Long id);

    boolean isExists(Long id);
    UserEntity particalUpdate(Long id, UserEntity userEntity);

    void deleteById(Long id);
}
