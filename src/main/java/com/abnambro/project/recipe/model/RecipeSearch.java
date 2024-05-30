package com.abnambro.project.recipe.model;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class RecipeSearch {
    String recipeType;
    Integer servings;
    String ingredientName;
    String excludeIngredientName;
    String instructionText;
}
