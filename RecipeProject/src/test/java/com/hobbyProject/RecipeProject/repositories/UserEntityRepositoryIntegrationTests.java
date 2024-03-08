package com.hobbyProject.RecipeProject.repositories;

import com.hobbyProject.RecipeProject.TestDataUtil;
import com.hobbyProject.RecipeProject.domain.entities.UserEntity;
import com.hobbyProject.RecipeProject.services.UserService;
import com.hobbyProject.RecipeProject.services.impl.UserServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
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
    //fixme
    @Test
    public void testThatUsersCanBeCreatedAndCalled(){
        UserEntity userEntity = TestDataUtil.createTestUserEntityA();
        userRepositoryForTest.save(userEntity);
        Optional<UserEntity> result = userRepositoryForTest.findById(userEntity.getId());
        assertThat(result).isPresent();
        Assertions.assertEquals(userEntity, result.get());
//        assertThat(result.get()).isEqualTo(userEntity);
    }

    @Test
    public void testThatMultipleUsersCanBeCreatedAndCalled() {
        UserEntity userEntityA = TestDataUtil.createTestUserEntityA();
        userRepositoryForTest.save(userEntityA);
        UserEntity userEntityB = TestDataUtil.createTestUserEntityB();
        userRepositoryForTest.save(userEntityB);
        Iterable<UserEntity> result = userRepositoryForTest.findAll();
        assertThat(result).containsAnyElementsOf(List.of(userEntityA, userEntityB));
        assertThat(result).contains(userEntityA, userEntityB);
    }

    @Test
    public void testThatUserCanBeUpdated() {
        UserEntity userEntity = TestDataUtil.createTestUserEntityA();
        userRepositoryForTest.save(userEntity);
        userEntity.setUsername("BrandNew");
        userRepositoryForTest.save(userEntity);
        Optional<UserEntity> result = userRepositoryForTest.findById(userEntity.getId());
        assertThat(result).isPresent();
        assertThat(result.get().getUsername()).isEqualTo("BrandNew");
    }

    @Test
    public void testThatUserCanBeDeleted() {
        UserEntity userEntity = TestDataUtil.createTestUserEntityA();
        userRepositoryForTest.save(userEntity);
        userRepositoryForTest.deleteById(userEntity.getId());
        Optional<UserEntity> result = userRepositoryForTest.findById(userEntity.getId());
        assertThat(result).isEmpty();
    }

}
