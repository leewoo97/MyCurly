package com.dldnwls.mycurly.domain.ingredient.entity;

import com.dldnwls.mycurly.domain.personalrecipe.entity.PersonalRecipe;
import com.dldnwls.mycurly.domain.standardrecipe.entity.StandardRecipe;
import jakarta.persistence.*;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Entity
public class Ingredient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "personalrecipe_id")
    private PersonalRecipe personalRecipe;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "standardRecipe_id")
    private StandardRecipe standardRecipe;
}
