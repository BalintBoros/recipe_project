package com.hobbyProject.RecipeProject;

import com.hobbyProject.RecipeProject.domain.dto.UserDto;
import com.hobbyProject.RecipeProject.domain.entities.UserEntity;

public final class TestDataUtil {

    private TestDataUtil() {
    }

    public static UserEntity createTestUserEntityA() {
        return new UserEntity().builder()
                .id(1L)
                .username("test Ryan Ghostling")
                .password("testRyanPassword")
                .email("tsetryan@email.com").build();
    }

    public static UserDto createTestUserDtoA() {
        return new UserDto().builder()
                .id(1L)
                .username("test Ryan Ghostling")
                .password("testRyanPassword")
                .email("tsetryan@email.com").build();
    }

    public static UserEntity createTestUserEntityB() {
        return new UserEntity().builder()
                .id(2L)
                .username("test Ryan Ghostling")
                .password("testRyanPassword")
                .email("tsetryan@email.com").build();
    }

    public static UserDto createTestUserDtoB() {
        return new UserDto().builder()
                .id(2L)
                .username("test Ryan Ghostling")
                .password("testRyanPassword")
                .email("tsetryan@email.com").build();
    }

}
