package com.hobbyProject.RecipeProject.domain.entities;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import java.util.HashSet;
import java.util.Set;

@Data
@EqualsAndHashCode(exclude = {"createdRecipes", "favouriteRecipes"})
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
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
//    @Fetch(FetchMode.JOIN)
    @JsonManagedReference
    private Set<RecipeEntity> createdRecipes = new HashSet<>();

    @JsonIgnore
    public Set<RecipeEntity> getCreatedRecipes() {
        return createdRecipes;
    }

    @ManyToMany
    @JoinTable(name = "favourite_recipes",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "recipe_id"))
    @Fetch(FetchMode.JOIN)
    @JsonManagedReference
    private Set<RecipeEntity> favouriteRecipes;

    @JsonIgnore
    public Set<RecipeEntity> getFavouriteRecipes() {
        return favouriteRecipes;
    }

    @Override
    public String toString() {
        return "UserEntity{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                '}';
    }

}
