package com.hobbyProject.RecipeProject.services.impl;

import com.hobbyProject.RecipeProject.domain.entities.UserEntity;
import com.hobbyProject.RecipeProject.repositories.UserRepository;
import com.hobbyProject.RecipeProject.services.UserService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.StreamSupport;

@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserEntity save(UserEntity userEntity) {
        return userRepository.save(userEntity);
    }

    @Override
    public List<UserEntity> findAll() {
        return StreamSupport.stream(userRepository
                .findAll().spliterator(),
                false)
                .toList();
    }

    @Override
    public Optional<UserEntity> findById(Long id) {
        return userRepository.findById(id);
    }

    @Override
    public boolean isExists(Long id){
        return userRepository.existsById(id);
    }

    @Override
    public UserEntity particalUpdate(Long id, UserEntity userEntity) {
        userEntity.setId(id);

        return userRepository.findById(id).map(user -> {
            if (userEntity.getUsername() != null) {
                user.setUsername(userEntity.getUsername());
            }
            if (userEntity.getPassword() != null) {
                user.setPassword(userEntity.getPassword());
            }
            if (userEntity.getEmail() != null) {
                user.setEmail(userEntity.getEmail());
            }
            if (userEntity.getFavouriteRecipes() != null) {
                user.setFavouriteRecipes(userEntity.getFavouriteRecipes());
            }
            return userRepository.save(user);
        }).orElseThrow(() -> new RuntimeException("User with id: " + id + " not found"));

    }

    @Override
    public void deleteById(Long id) {
        userRepository.deleteById(id);
    }
}
