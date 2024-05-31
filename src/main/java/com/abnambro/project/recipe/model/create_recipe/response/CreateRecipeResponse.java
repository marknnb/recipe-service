package com.abnambro.project.recipe.model.create_recipe.response;

import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class CreateRecipeResponse {
    @ApiModelProperty(notes = "Id of created Recipe", example = "1234")
    String recipeId;
}
