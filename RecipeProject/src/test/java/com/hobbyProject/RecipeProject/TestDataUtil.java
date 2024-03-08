package com.hobbyProject.RecipeProject;

import com.hobbyProject.RecipeProject.domain.dto.RecipeDto;
import com.hobbyProject.RecipeProject.domain.dto.UserDto;
import com.hobbyProject.RecipeProject.domain.entities.IngredientEntity;
import com.hobbyProject.RecipeProject.domain.entities.RecipeEntity;
import com.hobbyProject.RecipeProject.domain.entities.RecipeImageEntity;
import com.hobbyProject.RecipeProject.domain.entities.UserEntity;

import java.util.Collections;
import java.util.List;
import java.util.Set;

public final class TestDataUtil {

    private TestDataUtil() {
    }

    public static UserEntity createTestUserEntityA() {
        Set<RecipeEntity> emptySet = Collections.emptySet();
        return new UserEntity().builder()
                .id(1L)
                .username("test Ryan Ghostling")
                .password("testRyanPassword")
                .email("tsetryan@email.com")
                .createdRecipes(emptySet)
                .favouriteRecipes(emptySet).build();
    }


    public static UserDto createTestUserDtoA() {
        Set<RecipeDto> emptySet = Collections.emptySet();
        return new UserDto().builder()
                .id(1L)
                .username("test Ryan Ghostling")
                .password("testRyanPassword")
                .email("tsetryan@email.com")
                .createdRecipes(emptySet)
                .favouriteRecipes(emptySet).build();
    }

    public static UserEntity createTestUserEntityB() {
        Set<RecipeEntity> emptySet = Collections.emptySet();
        return new UserEntity().builder()
                .id(2L)
                .username("test Ryan Ghostling")
                .password("testRyanPassword")
                .email("tsetryan@email.com")
                .createdRecipes(emptySet)
                .favouriteRecipes(emptySet).build();
    }

    public static UserDto createTestUserDtoB() {
        Set<RecipeDto> emptySet = Collections.emptySet();
        return new UserDto().builder()
                .id(2L)
                .username("test Ryan Ghostling")
                .password("testRyanPassword")
                .email("tsetryan@email.com")
                .createdRecipes(emptySet)
                .favouriteRecipes(emptySet).build();
    }

    public static RecipeEntity createTestRecipeEntityA(UserEntity creator, Set<UserEntity> users,
                                                   Set<IngredientEntity> ingredients, List<RecipeImageEntity> images) {
        return new RecipeEntity().builder()
            .id(10L)
            .name("test recipeA")
            .description("test descriptionA")
            .instructions("test instructionsA")
            .creator(creator)
            .favoritedByUsers(users)
            .ingredients(ingredients)
            .images(images)
            .build();
    }

    public static RecipeEntity createTestRecipeEntityB(UserEntity creator, Set<UserEntity> users,
                                                   Set<IngredientEntity> ingredients, List<RecipeImageEntity> images) {
        return new RecipeEntity().builder()
            .id(11L)
            .name("test recipeB")
            .description("test descriptionB")
            .instructions("test instructionsB")
            .creator(creator)
            .favoritedByUsers(users)
            .ingredients(ingredients)
            .images(images)
            .build();
    }

}
