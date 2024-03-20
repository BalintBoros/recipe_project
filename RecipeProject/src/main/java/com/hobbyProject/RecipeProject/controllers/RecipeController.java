package com.hobbyProject.RecipeProject.controllers;

import com.hobbyProject.RecipeProject.domain.dto.RecipeDto;
import com.hobbyProject.RecipeProject.domain.entities.RecipeEntity;
import com.hobbyProject.RecipeProject.mappers.Mapper;
import com.hobbyProject.RecipeProject.services.RecipeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
public class RecipeController {

    private RecipeService recipeService;

    private Mapper<RecipeEntity, RecipeDto> recipeMapper;

    public RecipeController(RecipeService recipeService, Mapper<RecipeEntity, RecipeDto> recipeMapper) {
        this.recipeService = recipeService;
        this.recipeMapper = recipeMapper;
    }

    @PostMapping(path = "/recipes")
    public ResponseEntity<RecipeDto> createRecipe(@RequestBody RecipeDto recipeDto) {
        RecipeEntity recipeEntity = recipeMapper.mapFrom(recipeDto);
        RecipeEntity savedRecipeEntity = recipeService.save(recipeEntity);
        RecipeDto savedRecipeDto = recipeMapper.mapTo(savedRecipeEntity);
        return new ResponseEntity<>(savedRecipeDto, HttpStatus.CREATED);
    }

}
