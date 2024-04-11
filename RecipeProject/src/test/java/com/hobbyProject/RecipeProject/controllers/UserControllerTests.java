package com.hobbyProject.RecipeProject.controllers;
import com.hobbyProject.RecipeProject.TestDataUtil;
import com.hobbyProject.RecipeProject.domain.dto.UserDto;
import com.hobbyProject.RecipeProject.domain.entities.UserEntity;
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

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@AutoConfigureMockMvc
public class UserControllerTests {

    private UserService userService;

    private MockMvc mockMvc;

    private ObjectMapper objectMapper;

    @Autowired
    public UserControllerTests(UserService userService, MockMvc mockMvc) {
        this.userService = userService;
        this.mockMvc = mockMvc;
        this.objectMapper = new ObjectMapper();
    }

    @Test
    public void testThatUserCreateReturnsHttpCreated() throws Exception {
        UserEntity userEntityA = TestDataUtil.createTestUserEntityA();

        userEntityA.setId(null);
        String userJson = objectMapper.writeValueAsString(userEntityA);
        mockMvc.perform(MockMvcRequestBuilders.post("/api/users")
                .contentType(MediaType.APPLICATION_JSON)
                .content(userJson))
            .andExpect(
                MockMvcResultMatchers.status().isCreated()
        );

    }

    @Test
    public void testThatListUsersReturnsHttpOk() throws Exception {
        /*mockMvc.perform(MockMvcRequestBuilders.get("/api/users")
                        .contentType(MediaType.APPLICATION_JSON))
            .andExpect(
                MockMvcResultMatchers.status().isOk()
        );*/
        mockMvc.perform(MockMvcRequestBuilders.get("/api/users")
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(MockMvcResultMatchers.status().isOk())
            .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON));

    }

    @Test
    public void testThatPartialUpdateExistingUserReturnsHttpStatus200Ok() throws Exception {
        UserEntity testUserEntity = TestDataUtil.createTestUserEntityA();
        UserEntity savedUser = userService.save(testUserEntity);

        UserDto testUserDto = TestDataUtil.createTestUserDtoA();
        testUserDto.setUsername("UPDATED_username");
        String userDtoJson = objectMapper.writeValueAsString(testUserDto);

        mockMvc.perform(
                MockMvcRequestBuilders.patch("/api/users/" + savedUser.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(userDtoJson))
                .andExpect(
                        MockMvcResultMatchers.status().isOk()
                );
    }

}
