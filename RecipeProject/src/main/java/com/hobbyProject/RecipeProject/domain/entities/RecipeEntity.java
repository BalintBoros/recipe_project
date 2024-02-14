package com.hobbyProject.RecipeProject.domain.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "recipes")
public class RecipeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "recipe_id_seq")
    private Long id;
    private String name;
    private String description;
    private String instructions;
    private String category;
    private int difficulty;
    private String cookTime;
    private double rating;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private UserEntity creator;

    @ManyToMany(mappedBy = "favouriteRecipes")
    private Set<UserEntity> users = new HashSet<>();

}
