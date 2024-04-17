package com.hobbyProject.RecipeProject.controllers;

import com.hobbyProject.RecipeProject.domain.dto.RecipeIngredientDto;
import com.hobbyProject.RecipeProject.domain.entities.RecipeIngredientEntity;
import com.hobbyProject.RecipeProject.mappers.Mapper;
import com.hobbyProject.RecipeProject.services.RecipeIngredientService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@RestController
@RequestMapping("/api")
public class RecipeIngredientController {

    private RecipeIngredientService recipeIngredientService;

    private Mapper<RecipeIngredientEntity, RecipeIngredientDto> recipeIngredientMapper;

    public RecipeIngredientController(RecipeIngredientService recipeIngredientService,
                Mapper<RecipeIngredientEntity, RecipeIngredientDto> recipeIngredientMapper) {
        this.recipeIngredientService = recipeIngredientService;
        this.recipeIngredientMapper = recipeIngredientMapper;
    }

    @PostMapping(path = "/recipeIngredient")
    public ResponseEntity<RecipeIngredientDto> createRecipeIngredient(
            @RequestBody RecipeIngredientDto recipeIngredientDto){
        RecipeIngredientEntity recipeIngredientEntity = recipeIngredientMapper.mapFrom(recipeIngredientDto);
        RecipeIngredientEntity savedRecipeIngredientEntity = recipeIngredientService.save(recipeIngredientEntity);
        RecipeIngredientDto savedRecipeIngredientDto = recipeIngredientMapper.mapTo(savedRecipeIngredientEntity);
        return new ResponseEntity<>(savedRecipeIngredientDto, HttpStatus.CREATED);
    }

    @GetMapping(path = "/recipeIngredient")
    public ResponseEntity<Iterable<RecipeIngredientDto>> getAllRecipeIngredients(){
        Iterable<RecipeIngredientEntity> recipeIngredientEntities = recipeIngredientService.findAll().stream().toList();
        Iterable<RecipeIngredientDto> recipeIngredientDtos = StreamSupport.stream(recipeIngredientEntities.spliterator(), false)
                .map(recipeIngredientMapper::mapTo)
                .collect(Collectors.toList());
        return new ResponseEntity<>(recipeIngredientDtos, HttpStatus.OK);
    }

}