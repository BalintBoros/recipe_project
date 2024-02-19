package com.hobbyProject.RecipeProject.mappers.impl;

import com.hobbyProject.RecipeProject.domain.dto.IngredientDto;
import com.hobbyProject.RecipeProject.domain.entities.IngredientEntity;
import com.hobbyProject.RecipeProject.mappers.Mapper;
import org.modelmapper.ModelMapper;

public class IngredientMapperImpl implements Mapper<IngredientEntity, IngredientDto> {

    private ModelMapper modelMapper;

    public IngredientMapperImpl(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Override
    public IngredientDto mapTo(IngredientEntity ingredientEntity) {
        return modelMapper.map(ingredientEntity, IngredientDto.class);
    }

    @Override
    public IngredientEntity mapFrom(IngredientDto ingredientDto) {
        return modelMapper.map(ingredientDto, IngredientEntity.class);
    }
}
