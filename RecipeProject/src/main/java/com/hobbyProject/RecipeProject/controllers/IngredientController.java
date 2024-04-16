package com.hobbyProject.RecipeProject.controllers;

import com.hobbyProject.RecipeProject.domain.dto.IngredientDto;
import com.hobbyProject.RecipeProject.domain.entities.IngredientEntity;
import com.hobbyProject.RecipeProject.mappers.Mapper;
import com.hobbyProject.RecipeProject.services.IngredientService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@RestController
@RequestMapping("/api")
public class IngredientController {

    private IngredientService ingredientService;

    private Mapper<IngredientEntity, IngredientDto> ingredientMapper;

    public IngredientController(IngredientService ingredientService,
                Mapper<IngredientEntity, IngredientDto> ingredientMapper) {
        this.ingredientService = ingredientService;
        this.ingredientMapper = ingredientMapper;
    }

    @PostMapping(path = "/ingredient")
    public ResponseEntity<IngredientDto> createIngredient(
            @RequestBody IngredientDto ingredientDto){
        IngredientEntity ingredientEntity = ingredientMapper.mapFrom(ingredientDto);
        IngredientEntity savedIngredientEntity = ingredientService.save(ingredientEntity);
        IngredientDto savedIngredientDto = ingredientMapper.mapTo(savedIngredientEntity);
        return new ResponseEntity<>(savedIngredientDto, HttpStatus.CREATED);
    }

    @GetMapping(path = "/ingredient")
    public ResponseEntity<Iterable<IngredientDto>> getAllIngredients(){
        Iterable<IngredientEntity> ingredientEntities = ingredientService.findAll().stream().toList();
        Iterable<IngredientDto> ingredientDtos = StreamSupport.stream(ingredientEntities.spliterator(), false)
                .map(ingredientMapper::mapTo)
                .collect(Collectors.toList());
        return new ResponseEntity<>(ingredientDtos, HttpStatus.OK);
    }

}
