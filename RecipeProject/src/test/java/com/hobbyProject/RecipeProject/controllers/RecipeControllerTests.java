package com.hobbyProject.RecipeProject.controllers;

import com.hobbyProject.RecipeProject.TestDataUtil;
import com.hobbyProject.RecipeProject.domain.entities.*;
import com.hobbyProject.RecipeProject.services.RecipeService;
import com.hobbyProject.RecipeProject.services.UserService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.testcontainers.shaded.com.fasterxml.jackson.core.JsonProcessingException;
import org.testcontainers.shaded.com.fasterxml.jackson.databind.ObjectMapper;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@AutoConfigureMockMvc
public class RecipeControllerTests {

    private RecipeService recipeService;
    private UserService userService;
    private MockMvc mockMvc;
    private ObjectMapper objectMapper;

    @Autowired
    public RecipeControllerTests(RecipeService recipeService, UserService userService, MockMvc mockMvc) {
        this.recipeService = recipeService;
        this.userService = userService;
        this.mockMvc = mockMvc;
        this.objectMapper = new ObjectMapper();
    }

    //TODO: correct the mistake here
    @Test
    public void testThatRecipeCreateReturns201Created() throws Exception {
        UserEntity userEntityA = TestDataUtil.createTestUserEntityA();
        UserEntity savedUser = userService.save(userEntityA);
        Set<UserEntity> users = Set.of(userEntityA);

        List<IngredientEntity> ingredients = List.of(new IngredientEntity()
                .builder().id(null).name("testIngredient").build());
        List<RecipeImageEntity> images = Collections.emptyList();
//        RecipeIngredientEntity
        RecipeEntity recipeEntity = TestDataUtil.createTestRecipeEntityA(savedUser, users, Collections.emptyList(), images);
        recipeEntity.setCookTime("55 min"); recipeEntity.setName("Recipe in controller test");
        recipeEntity.setCategory("Desszert");
        recipeEntity.setDifficulty(2);

        String recipeJson = objectMapper.writeValueAsString(recipeEntity);
        mockMvc.perform(MockMvcRequestBuilders.post("/api/recipes")
                .contentType(MediaType.APPLICATION_JSON)
                .content(recipeJson))
                .andExpect(MockMvcResultMatchers.status().isCreated());

    }

    @Test
    public void testThatListRecipesReturns200Ok() throws Exception{
        mockMvc.perform(MockMvcRequestBuilders.get("/api/recipes")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON));
    }

    public void testThatParticalUpdateExistingUserReturns200Ok(){
        UserEntity userEntityA = TestDataUtil.createTestUserEntityA();
        Set<UserEntity> users = Set.of(userEntityA);

        List<IngredientEntity> ingredients = List.of(new IngredientEntity()
                .builder().id(null).name("testIngredient").build());
        List<RecipeImageEntity> images = Collections.emptyList();
//        RecipeIngredientEntity
        RecipeEntity recipeEntity = TestDataUtil.createTestRecipeEntityA(userEntityA, users, Collections.emptyList(), images);
        recipeEntity.setCookTime("55 min"); recipeEntity.setName("Recipe in controller test");
        recipeEntity.setCategory("Desszert");
        recipeEntity.setDifficulty(2);
        recipeService.save(recipeEntity);


    }

}
