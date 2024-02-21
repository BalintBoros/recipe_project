package com.hobbyProject.RecipeProject.mappers.impl;

import com.hobbyProject.RecipeProject.domain.dto.UserDto;
import com.hobbyProject.RecipeProject.domain.entities.UserEntity;
import com.hobbyProject.RecipeProject.mappers.Mapper;
//import org.modelmapper.ModelMapper;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class UserMapperImpl implements Mapper<UserEntity, UserDto> {
    private ModelMapper modelMapper;

//    @Bean
//    public ModelMapper modelMapper() {
//        return new ModelMapper();
//    }

    public UserMapperImpl(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Override
    public UserDto mapTo(UserEntity userEntity) {
        return modelMapper.map(userEntity, UserDto.class);
    }

    @Override
    public UserEntity mapFrom(UserDto userDto) {
        return modelMapper.map(userDto, UserEntity.class);
    }
}
