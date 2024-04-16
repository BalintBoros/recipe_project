package com.hobbyProject.RecipeProject.services.impl;

import com.hobbyProject.RecipeProject.domain.entities.RecipeImageEntity;
import com.hobbyProject.RecipeProject.repositories.RecipeImageRepository;
import com.hobbyProject.RecipeProject.services.RecipeImageService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.StreamSupport;

@Service
public class RecipeImageServiceImpl implements RecipeImageService {

    private RecipeImageRepository recipeImageRepository;

    public RecipeImageServiceImpl(RecipeImageRepository recipeImageRepository) {
        this.recipeImageRepository = recipeImageRepository;
    }

    @Override
    public RecipeImageEntity save(RecipeImageEntity recipeImageEntity) {
        return recipeImageRepository.save(recipeImageEntity);
    }

    @Override
    public List<RecipeImageEntity> findAll() {
        return StreamSupport.stream(recipeImageRepository.findAll().spliterator(), false).toList();
    }

    @Override
    public Optional<RecipeImageEntity> findById(Long id) {
        return recipeImageRepository.findById(id);
    }

    @Override
    public boolean isExists(Long id) {
        return recipeImageRepository.existsById(id);
    }

    @Override
    public void deleteById(Long id) {
        recipeImageRepository.deleteById(id);
    }
}