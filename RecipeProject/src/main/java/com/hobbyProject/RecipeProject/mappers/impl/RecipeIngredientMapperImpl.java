package com.hobbyProject.RecipeProject.mappers.impl;

import com.hobbyProject.RecipeProject.domain.dto.RecipeIngredientDto;
import com.hobbyProject.RecipeProject.domain.entities.RecipeIngredientEntity;
import com.hobbyProject.RecipeProject.mappers.Mapper;
import org.modelmapper.ModelMapper;

public class RecipeIngredientMapperImpl implements Mapper<RecipeIngredientEntity, RecipeIngredientDto> {

    private ModelMapper modelMapper;

    public RecipeIngredientMapperImpl(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }


    @Override
    public RecipeIngredientDto mapTo(RecipeIngredientEntity recipeIngredientEntity) {
        return modelMapper.map(recipeIngredientEntity, RecipeIngredientDto.class);
    }

    @Override
    public RecipeIngredientEntity mapFrom(RecipeIngredientDto recipeIngredientDto) {
        return modelMapper.map(recipeIngredientDto, RecipeIngredientEntity.class);
    }
}
