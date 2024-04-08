package com.hobbyProject.RecipeProject.domain.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EqualsAndHashCode(exclude = {"createdRecipes", "favouriteRecipes"})
public class RecipeDto {

    private Long id;
    private String name;
    private String description;
    private String instructions;
    private String category;
    private int difficulty;
    private String cookTime;
    private double rating;

    private UserDto creator;

    @JsonIgnore
    public UserDto getCreator() {
        return creator;
    }


}
