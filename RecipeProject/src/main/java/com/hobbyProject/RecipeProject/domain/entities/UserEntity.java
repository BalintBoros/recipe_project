package com.hobbyProject.RecipeProject.domain.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import java.util.HashSet;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "users")
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_id_seq")
    private Long id;

    private String username;
    private String email;
    private String password; // hashed

    @OneToMany(mappedBy = "creator")
    @Fetch(FetchMode.JOIN)
    private Set<RecipeEntity> createdRecipes = new HashSet<>();

    @ManyToMany
    @JoinTable(name = "favourite_recipes",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "recipe_id"))
    @Fetch(FetchMode.JOIN)
    private Set<RecipeEntity> favouriteRecipes;

    @Override
    public String toString() {
        return "UserEntity{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", createdRecipes=" + (!createdRecipes.isEmpty() ? createdRecipes : "") +
                ", favouriteRecipes=" + (!favouriteRecipes.isEmpty() ? favouriteRecipes : "") +
                '}';
    }

}
