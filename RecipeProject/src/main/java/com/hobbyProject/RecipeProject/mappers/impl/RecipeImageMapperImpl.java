package com.hobbyProject.RecipeProject.mappers.impl;

import com.hobbyProject.RecipeProject.domain.dto.RecipeImageDto;
import com.hobbyProject.RecipeProject.domain.entities.RecipeImageEntity;
import com.hobbyProject.RecipeProject.mappers.Mapper;
import org.modelmapper.ModelMapper;

public class RecipeImageMapperImpl implements Mapper<RecipeImageEntity, RecipeImageDto>{

    private ModelMapper modelMapper;

    public RecipeImageMapperImpl(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Override
    public RecipeImageDto mapTo(RecipeImageEntity recipeImageEntity) {
        return modelMapper.map(recipeImageEntity, RecipeImageDto.class);
    }

    @Override
    public RecipeImageEntity mapFrom(RecipeImageDto recipeImageDto) {
        return modelMapper.map(recipeImageDto, RecipeImageEntity.class);
    }
}
