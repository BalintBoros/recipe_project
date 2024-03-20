package com.hobbyProject.RecipeProject.domain.dto;

import com.hobbyProject.RecipeProject.domain.entities.RecipeEntity;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
        @Builder
@EqualsAndHashCode(exclude = {"createdRecipes", "favouriteRecipes"})
public class UserDto {

    private Long id;

    private String username;

    private String email;

    private String password;

    private Set<RecipeDto> createdRecipes;

    private Set<RecipeDto> favouriteRecipes;

}
