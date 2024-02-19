package com.hobbyProject.RecipeProject.mappers.impl;

import com.hobbyProject.RecipeProject.domain.dto.RecipeDto;
import com.hobbyProject.RecipeProject.domain.entities.RecipeEntity;
import com.hobbyProject.RecipeProject.mappers.Mapper;
import org.modelmapper.ModelMapper;

public class RecipeMapperImpl implements Mapper<RecipeEntity, RecipeDto> {

    private ModelMapper modelMapper;

    public RecipeMapperImpl(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Override
    public RecipeDto mapTo(RecipeEntity recipeEntity) {
        return modelMapper.map(recipeEntity, RecipeDto.class);
    }

    @Override
    public RecipeEntity mapFrom(RecipeDto recipeDto) {
        return modelMapper.map(recipeDto, RecipeEntity.class);
    }

}
