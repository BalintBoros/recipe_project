package com.hobbyProject.RecipeProject.domain.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RecipeImageDto {

    private Long id;
    private RecipeDto recipe;
    private String url;
}
