package com.hobbyProject.RecipeProject.repositories;

import com.hobbyProject.RecipeProject.TestDataUtil;
import com.hobbyProject.RecipeProject.domain.entities.IngredientEntity;
import com.hobbyProject.RecipeProject.domain.entities.RecipeEntity;
import com.hobbyProject.RecipeProject.domain.entities.RecipeImageEntity;
import com.hobbyProject.RecipeProject.domain.entities.UserEntity;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class RecipeEntityRepositoryIntegrationTest {

    private RecipeRepository recipeRepositoryForTest;

    @Autowired
    public RecipeEntityRepositoryIntegrationTest(RecipeRepository recipeRepositoryForTest) {
        this.recipeRepositoryForTest = recipeRepositoryForTest;
    }

    @Test
    public void testThatRecipeCanBeCreatedAndCalled() {
        UserEntity userEntityA = TestDataUtil.createTestUserEntityA();
        UserEntity userEntityB = TestDataUtil.createTestUserEntityB();
        Set<UserEntity> users = Set.of(userEntityA,userEntityB);
        Set<IngredientEntity> ingredients = Set.of(new IngredientEntity().builder().id(44L).name("testIngredient").build());
        List<RecipeImageEntity> images = Collections.emptyList();
        RecipeEntity recipeEntity = TestDataUtil.createTestRecipeEntityA(userEntityA, users, ingredients, images);
        recipeRepositoryForTest.save(recipeEntity);
        Optional<RecipeEntity> result = recipeRepositoryForTest.findById(recipeEntity.getId());
        assertThat(result).isNotNull();
        assertThat(result).isPresent();
        Assertions.assertEquals(recipeEntity, result.get());
    }






}
