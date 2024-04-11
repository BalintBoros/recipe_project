package com.hobbyProject.RecipeProject.controllers;

import com.hobbyProject.RecipeProject.domain.dto.UserDto;
import com.hobbyProject.RecipeProject.domain.entities.UserEntity;
import com.hobbyProject.RecipeProject.mappers.Mapper;
import com.hobbyProject.RecipeProject.services.UserService;
import org.apache.catalina.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@RestController
@RequestMapping("/api")
public class UserController {

    private UserService userService;

    private Mapper<UserEntity, UserDto> userMapper;

    public UserController(UserService userService, Mapper<UserEntity, UserDto> userMapper) {
        this.userService = userService;
        this.userMapper = userMapper;
    }

    @PostMapping(path = "/users")
    public ResponseEntity<UserDto> createUser(@RequestBody UserDto userDto) {
        UserEntity userEntity = userMapper.mapFrom(userDto);
        UserEntity savedUserEntity = userService.save(userEntity);
        UserDto savedUserDto = userMapper.mapTo(savedUserEntity);
        return new ResponseEntity<>(savedUserDto, HttpStatus.CREATED);
    }

    @GetMapping(path = "/users")
    public ResponseEntity<Iterable<UserDto>> getAllUsers() {
        Iterable<UserEntity> userEntities = userService.findAll().stream().toList();
//        Iterable<UserDto> userDtos = userEntities.stream()   userMapper.mapTo(userEntities);
        Iterable<UserDto> userDtos = StreamSupport.stream(userEntities.spliterator(), false)
                                          .map(userMapper::mapTo)
                                          .collect(Collectors.toList());
        return new ResponseEntity<>(userDtos, HttpStatus.OK);
//        return userEntities.stream().map(userMapper::mapTo).collect(Collectors.toList());
    }
/**
 * other solution
 * */
/*    @GetMapping(path = "/users")
    public ResponseEntity<Iterable<UserDto>> getAllUsers() {
        try {
            Iterable<UserEntity> userEntities = userService.findAll(); // Assuming this method fetches all users

            // Mapping entities to DTOs
            Iterable<UserDto> userDtos = StreamSupport.stream(userEntities.spliterator(), false)
                    .map(userMapper::mapTo)
                    .collect(Collectors.toList());

            return ResponseEntity.ok(userDtos); // Return 200 OK with the list of user DTOs
        } catch (Exception e) {
            System.out.println("getAllUsers has failed");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }*/

    @GetMapping(path = "/users/{id}")
    public ResponseEntity<UserDto> getSingleUser(@PathVariable("id") Long id) {
        Optional<UserEntity> userEntitieOpt = userService.findById(id);
        UserEntity userEntity = userEntitieOpt.get();
        UserDto userDto = userMapper.mapTo(userEntity);
        return new ResponseEntity<>(userDto, HttpStatus.OK);
    //        return userEntities.stream().map(userMapper::mapTo).collect(Collectors.toList());
    }

    @PatchMapping(path = "/users/{id}")
    public ResponseEntity<UserDto> particalUserUpdate(
            @PathVariable("id") Long id,
            @RequestBody UserDto userDto){
        if(!userService.isExists(id)){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        UserEntity userEntity = userMapper.mapFrom(userDto);
        UserEntity updatedUserEntity = userService.particalUpdate(id, userEntity);
        return new ResponseEntity<>(userMapper.mapTo(updatedUserEntity), HttpStatus.OK);
    }

    @DeleteMapping(path = "/users/{id}")
    public ResponseEntity deleteUser(@PathVariable("id") Long id){
        if(!userService.isExists(id)){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        userService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
