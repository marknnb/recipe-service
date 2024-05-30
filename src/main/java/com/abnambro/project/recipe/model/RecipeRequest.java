package com.abnambro.project.recipe.model;

import io.swagger.annotations.ApiModelProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@Getter
public class RecipeRequest {
    @NotBlank(message = "{recipeName.notBlank}")
    @ApiModelProperty(notes = "The name of the recipe", example = "Pasta")
    private String name;

    @ApiModelProperty(notes = "The type of the recipe", example = "VEGETARIAN")
    private RecipeType type;

    @NotNull(message = "{numberOfServings.notNull}") @Positive(message = "{numberOfServings.positive}") @ApiModelProperty(notes = "The number of servings per recipe", example = "4")
    private int numberOfServings;

    @NotEmpty
    @ApiModelProperty(notes = "The ids of the ingredients needed to make the recipe", example = "[1,2]")
    private List<String> ingredients;

    @NotEmpty
    @ApiModelProperty(notes = "Instructions to prepare recipe", example = "[cut vegetables,wash rice]")
    private List<String> instructions;
}
