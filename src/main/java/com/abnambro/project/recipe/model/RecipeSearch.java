package com.abnambro.project.recipe.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RecipeSearch {
    @ApiModelProperty(notes = "The Type of the recipe", example = "VEGETARIAN")
    String recipeType;

    @ApiModelProperty(notes = "No. of service for Recipe", example = "4")
    Integer servings;

    @ApiModelProperty(notes = "Ingredients included  in Recipe", example = "Onions")
    String ingredientName;

    @ApiModelProperty(notes = "Ingredients must not be  in Recipe", example = "Pasta")
    String excludeIngredientName;

    @ApiModelProperty(notes = "Instruction text  must not be  in Recipe", example = "Pasta")
    String instructionText;
}
