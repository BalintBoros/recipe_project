package com.hobbyProject.RecipeProject.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
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

}
