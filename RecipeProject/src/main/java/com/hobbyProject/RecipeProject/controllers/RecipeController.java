package com.hobbyProject.RecipeProject.controllers;

import com.hobbyProject.RecipeProject.domain.dto.RecipeDto;
import com.hobbyProject.RecipeProject.domain.entities.RecipeEntity;
import com.hobbyProject.RecipeProject.mappers.Mapper;
import com.hobbyProject.RecipeProject.services.RecipeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@RestController
@RequestMapping("/api")
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

    @GetMapping(path = "/recipes")
    public ResponseEntity<Iterable<RecipeDto>> getAllRecipes(){
        Iterable<RecipeEntity> recipeEntities = recipeService.findAll().stream().toList();
        Iterable<RecipeDto> recipeDtos = StreamSupport.stream(recipeEntities.spliterator(), false)
                .map(recipeMapper::mapTo)
                .collect(Collectors.toList());

        return new ResponseEntity<>(recipeDtos, HttpStatus.OK);
    }

    @PatchMapping(path = "recipes/{id}")
    public ResponseEntity<RecipeDto> particalRecipeUpdate(
            @PathVariable("id") Long id,
            @RequestBody RecipeDto recipeDto){
        if(!recipeService.isExists(id)){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }else{
            RecipeEntity recipeEntity = recipeMapper.mapFrom(recipeDto);
            RecipeEntity updatedRecipeEntity = recipeService.particalUpdate(id, recipeEntity);
            return new ResponseEntity<>(recipeMapper.mapTo(updatedRecipeEntity), HttpStatus.OK);
        }
    }

    @DeleteMapping(path = "recipes/{id}")
    public ResponseEntity deleteRecipe(@PathVariable("id") Long id){
        if(!recipeService.isExists(id)){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }else{
            recipeService.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }



}
