package com.hobbyProject.RecipeProject.services.impl;

import com.hobbyProject.RecipeProject.domain.entities.IngredientEntity;
import com.hobbyProject.RecipeProject.repositories.IngredientRepository;
import com.hobbyProject.RecipeProject.services.IngredientService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.StreamSupport;

@Service
public class IngredientServiceImpl implements IngredientService {

    private IngredientRepository ingredientRepository;

    public IngredientServiceImpl(IngredientRepository ingredientRepository) {
        this.ingredientRepository = ingredientRepository;
    }

    @Override
    public IngredientEntity save(IngredientEntity ingredientEntity) {
        return ingredientRepository.save(ingredientEntity);
    }

    @Override
    public List<IngredientEntity> findAll() {
        return StreamSupport.stream(ingredientRepository.
                findAll().spliterator(),false).toList();
    }

    @Override
    public Optional<IngredientEntity> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public boolean isExists(Long id) {
        return ingredientRepository.existsById(id);
    }

    @Override
    public void deleteById(Long id) {
        ingredientRepository.deleteById(id);
    }
}
