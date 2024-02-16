package com.hobbyProject.RecipeProject.domain.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "recipe_images")
public class RecipeImageEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "recipe_image_id_seq")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "recipe_id")
    private RecipeEntity recipe;

    private String url;
}
