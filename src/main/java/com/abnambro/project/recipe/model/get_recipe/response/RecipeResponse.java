package com.abnambro.project.recipe.model.get_recipe.response;

import lombok.Builder;

import java.util.List;

@Builder
public class RecipeResponse {
    public String recipeId;
    public String name;
    public String type;
    public int numberOfServings;
    public List<String> ingredientResponses;
    public List<String> instructionResponses;
}
