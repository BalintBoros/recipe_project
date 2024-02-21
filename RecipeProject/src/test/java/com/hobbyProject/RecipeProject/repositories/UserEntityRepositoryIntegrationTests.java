package com.hobbyProject.RecipeProject.repositories;

import com.hobbyProject.RecipeProject.TestDataUtil;
import com.hobbyProject.RecipeProject.domain.entities.UserEntity;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Optional;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class UserEntityRepositoryIntegrationTests {

    private UserRepository userRepositoryForTest;

    @Autowired
    public UserEntityRepositoryIntegrationTests(UserRepository userRepositoryForTest) {
        this.userRepositoryForTest = userRepositoryForTest;
    }

    @Test
    public void testThatMultipleUsersCanBeCreatedAndCalled(){
        UserEntity userEntity = TestDataUtil.createTestUserEntityA();
        userRepositoryForTest.save(userEntity);
        Optional<UserEntity> result = userRepositoryForTest.findById(userEntity.getId());
        assertThat(result).isPresent();
        assertThat(result.get()).isEqualTo(userEntity);
    }


}
