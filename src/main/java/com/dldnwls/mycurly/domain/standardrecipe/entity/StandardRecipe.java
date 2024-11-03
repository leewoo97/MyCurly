package com.dldnwls.mycurly.domain.standardrecipe.entity;

import com.dldnwls.mycurly.domain.ingredient.entity.Ingredient;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
@Entity
public class StandardRecipe {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @OneToMany(mappedBy = "standardRecipe")
    private List<Ingredient> ingredientList = new ArrayList<>();
}
