package com.hobbyProject.RecipeProject.repositories;

import com.hobbyProject.RecipeProject.domain.entities.RecipeEntity;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;

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
        RecipeEntity recipeEntity  ;
    }

}
