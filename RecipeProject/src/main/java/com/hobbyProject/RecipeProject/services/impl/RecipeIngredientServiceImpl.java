package com.hobbyProject.RecipeProject.services.impl;

import com.hobbyProject.RecipeProject.domain.entities.RecipeIngredientEntity;
import com.hobbyProject.RecipeProject.repositories.RecipeIngredientRepository;
import com.hobbyProject.RecipeProject.services.RecipeIngredientService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.StreamSupport;

@Service
public class RecipeIngredientServiceImpl implements RecipeIngredientService {

    private RecipeIngredientRepository recipeIngredientRepository;

    public RecipeIngredientServiceImpl(RecipeIngredientRepository recipeIngredientRepository) {
        this.recipeIngredientRepository = recipeIngredientRepository;
    }

    @Override
    public RecipeIngredientEntity save(RecipeIngredientEntity recipeIngredientEntity) {
        return recipeIngredientRepository.save(recipeIngredientEntity);
    }

    @Override
    public List<RecipeIngredientEntity> findAll() {
        return StreamSupport.stream(recipeIngredientRepository.findAll().spliterator(), false).toList();
    }

    @Override
    public Optional<RecipeIngredientEntity> findById(Long id) {
        return recipeIngredientRepository.findById(id);
    }

    @Override
    public boolean isExists(Long id) {
        return recipeIngredientRepository.existsById(id);
    }

    @Override
    public void deleteById(Long id) {
        recipeIngredientRepository.deleteById(id);
    }
}