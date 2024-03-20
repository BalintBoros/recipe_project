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
import org.springframework.transaction.annotation.Transactional;

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

    @Test
    @Transactional
    public void testThatMultipleRecipesCanBeCreatedAndCalled(){
        UserEntity userEntityA = TestDataUtil.createTestUserEntityA();
        UserEntity userEntityB = TestDataUtil.createTestUserEntityB();
        Set<UserEntity> users = Set.of(userEntityA,userEntityB);
        Set<IngredientEntity> ingredientsA = Set.of(new IngredientEntity().builder().id(44L).name("testIngredientA").build(),new IngredientEntity().builder().id(45L).name("testIngredientB").build());
        Set<IngredientEntity> ingredientsB = Set.of(new IngredientEntity().builder().id(45L).name("testIngredientB").build());
        List<RecipeImageEntity> images = Collections.emptyList();
        RecipeEntity recipeEntityA = TestDataUtil.createTestRecipeEntityA(userEntityA, users, ingredientsA, images);
        RecipeEntity recipeEntityB = TestDataUtil.createTestRecipeEntityB(userEntityB, users, ingredientsB, images);
        recipeRepositoryForTest.save(recipeEntityA);
        recipeRepositoryForTest.save(recipeEntityB);
        Iterable<RecipeEntity> result = recipeRepositoryForTest.findAll();
        assertThat(result).isNotNull();
        assertThat(result).usingRecursiveFieldByFieldElementComparatorIgnoringFields("creator", "favoritedByUsers", "ingredients").contains(recipeEntityA, recipeEntityB);
    }

    @Test
    public void testThatRecipeCanBeUpdated() {
        UserEntity userEntityA = TestDataUtil.createTestUserEntityA();
        Set<UserEntity> users = Set.of(userEntityA);
        Set<IngredientEntity> ingredients = Set.of(new IngredientEntity().builder().id(44L).name("testIngredient").build());
        List<RecipeImageEntity> images = Collections.emptyList();
        RecipeEntity recipeEntity = TestDataUtil.createTestRecipeEntityA(userEntityA, users, ingredients, images);
        recipeRepositoryForTest.save(recipeEntity);
        recipeEntity.setCookTime("55 min"); recipeEntity.setName("BrandNew Recipe Name");
        recipeRepositoryForTest.save(recipeEntity);
        Optional<RecipeEntity> result = recipeRepositoryForTest.findById(recipeEntity.getId());
        assertThat(result).isPresent();
        assertThat(result.get().getName()).isEqualTo("BrandNew Recipe Name");
        assertThat(result.get().getCookTime()).isEqualTo("55 min");
    }




}
