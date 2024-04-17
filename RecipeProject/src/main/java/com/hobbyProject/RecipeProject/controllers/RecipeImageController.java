package com.hobbyProject.RecipeProject.controllers;

import com.hobbyProject.RecipeProject.domain.dto.RecipeImageDto;
import com.hobbyProject.RecipeProject.domain.entities.RecipeImageEntity;
import com.hobbyProject.RecipeProject.mappers.Mapper;
import com.hobbyProject.RecipeProject.services.RecipeImageService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@RestController
@RequestMapping("/api")
public class RecipeImageController {

    private RecipeImageService recipeImageService;

    private Mapper<RecipeImageEntity, RecipeImageDto> recipeImageMapper;

    public RecipeImageController(RecipeImageService recipeImageService,
                Mapper<RecipeImageEntity, RecipeImageDto> recipeImageMapper) {
        this.recipeImageService = recipeImageService;
        this.recipeImageMapper = recipeImageMapper;
    }

    @PostMapping(path = "/recipeImage")
    public ResponseEntity<RecipeImageDto> createRecipeImage(
            @RequestBody RecipeImageDto recipeImageDto){
        RecipeImageEntity recipeImageEntity = recipeImageMapper.mapFrom(recipeImageDto);
        RecipeImageEntity savedRecipeImageEntity = recipeImageService.save(recipeImageEntity);
        RecipeImageDto savedRecipeImageDto = recipeImageMapper.mapTo(savedRecipeImageEntity);
        return new ResponseEntity<>(savedRecipeImageDto, HttpStatus.CREATED);
    }

    @GetMapping(path = "/recipeImage")
    public ResponseEntity<Iterable<RecipeImageDto>> getAllRecipeImages(){
        Iterable<RecipeImageEntity> recipeImageEntities = recipeImageService.findAll().stream().toList();
        Iterable<RecipeImageDto> recipeImageDtos = StreamSupport.stream(recipeImageEntities.spliterator(), false)
                .map(recipeImageMapper::mapTo)
                .collect(Collectors.toList());
        return new ResponseEntity<>(recipeImageDtos, HttpStatus.OK);
    }

}