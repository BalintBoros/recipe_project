package com.hobbyProject.RecipeProject.services.impl;

import com.hobbyProject.RecipeProject.domain.entities.RecipeEntity;
import com.hobbyProject.RecipeProject.repositories.RecipeRepository;
import com.hobbyProject.RecipeProject.services.RecipeService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.StreamSupport;

@Service
public class RecipeServiceImpl implements RecipeService {

    private RecipeRepository recipeRepository;

    public RecipeServiceImpl(RecipeRepository recipeRepository) {
        this.recipeRepository = recipeRepository;
    }

    @Override
    public RecipeEntity save(RecipeEntity recipeEntity) {
        return recipeRepository.save(recipeEntity);
    }

    @Override
    public List<RecipeEntity> findAll() {
        return StreamSupport.stream(recipeRepository.findAll().spliterator(), false).toList();
    }

    @Override
    public Optional<RecipeEntity> findById(Long id) {
        return recipeRepository.findById(id);
    }

    @Override
    public boolean isExists(Long id) {
        return recipeRepository.existsById(id);
    }

    @Override
    public RecipeEntity particalUpdate(Long id, RecipeEntity recipeEntity) {
        recipeEntity.setId(id);
        return recipeRepository.findById(id).map(recipe -> {
            if (recipeEntity.getName() != null) {
                recipe.setName(recipeEntity.getName());
            }
            if (recipeEntity.getDescription() != null) {
                recipe.setDescription(recipeEntity.getDescription());
            }
            if (recipeEntity.getIngredients() != null) {
                recipe.setIngredients(recipeEntity.getIngredients());
            }
            if (recipeEntity.getFavoritedByUsers() != null) {
                recipe.setFavoritedByUsers(recipeEntity.getFavoritedByUsers());
            }
            if (recipeEntity.getImages() != null) {
                recipe.setImages(recipeEntity.getImages());
            }
            return recipeRepository.save(recipe);
        }).orElseGet(() -> recipeRepository.save(recipeEntity));
    }

    @Override
    public void deleteById(Long id) {
        recipeRepository.deleteById(id);
    }
}
