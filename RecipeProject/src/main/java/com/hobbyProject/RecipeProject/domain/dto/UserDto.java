package com.hobbyProject.RecipeProject.domain.dto;

import com.hobbyProject.RecipeProject.domain.entities.RecipeEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
        @Builder
public class UserDto {

    private Long id;

    private String username;

    private String email;

    private String password;

    private Set<RecipeDto> createdRecipes;

    private Set<RecipeDto> favouriteRecipes;

}
