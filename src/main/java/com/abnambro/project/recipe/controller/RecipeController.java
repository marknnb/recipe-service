package com.abnambro.project.recipe.controller;

import com.abnambro.project.recipe.model.RecipeRequest;
import com.abnambro.project.recipe.model.RecipeSearch;
import com.abnambro.project.recipe.model.create_recipe.response.CreateRecipeResponse;
import com.abnambro.project.recipe.model.get_recipe.response.RecipeResponse;
import com.abnambro.project.recipe.service.RecipeService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import jakarta.validation.Valid;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "api/v1/recipe")
@Slf4j
public class RecipeController {
    private final RecipeService recipeService;

    public RecipeController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    @GetMapping(path = "/page/{page}/size/{size}")
    public List<RecipeResponse> getRecipeList(
            @PathVariable(name = "page") int page, @PathVariable(name = "size") int size) {
        log.info("Getting the recipes");
        return recipeService.getRecipeList(page, size);
    }

    @ApiOperation(value = "List one recipe by its ID", response = RecipeResponse.class)
    @ApiResponses(
            value = {
                @ApiResponse(code = 200, message = "Successful request"),
                @ApiResponse(code = 404, message = "Recipe not found by the given ID")
            })
    @GetMapping(value = "/{id}")
    public RecipeResponse getRecipe(
            @ApiParam(value = "Recipe ID", required = true) @PathVariable(name = "id") Integer id) {
        log.info("Getting the recipe by its id. Id: {}", id);
        return recipeService.getRecipeById(id);
    }

    @PostMapping
    @ApiOperation(value = "Create a recipe")
    @ApiResponses(
            value = {
                @ApiResponse(code = 201, message = "Recipe created"),
                @ApiResponse(code = 400, message = "Bad input")
            })
    @ResponseStatus(HttpStatus.CREATED)
    public CreateRecipeResponse createRecipe(
            @ApiParam(value = "Properties of the recipe", required = true) @Valid @RequestBody RecipeRequest request) {
        log.info("Creating the recipe with properties");
        return recipeService.createRecipe(request);
    }

    @PutMapping(value = "/{id}")
    public void updateRecipe(@PathVariable("id") Long recipeId, @RequestBody RecipeRequest request) {
        log.info("Updating the recipe by given properties");
        recipeService.updateRecipe(recipeId, request);
    }

    @ApiOperation(value = "Delete the recipe")
    @ApiResponses(
            value = {
                @ApiResponse(code = 200, message = "Successful operation"),
                @ApiResponse(code = 400, message = "Invalid input"),
                @ApiResponse(code = 404, message = "Recipe not found by the given ID")
            })
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> deleteRecipeById(@PathVariable("id") Long recipeId) {
        recipeService.deleteRecipeById(recipeId);
        return ResponseEntity.noContent().build();
    }

    @ApiOperation(value = "Search recipes by given parameters")
    @ApiResponses(
            value = {
                @ApiResponse(code = 200, message = "Successful request"),
                @ApiResponse(code = 404, message = "Different error messages related to criteria and recipe")
            })
    @PostMapping(value = "/search")
    public List<RecipeResponse> filterRecipe(@RequestBody RecipeSearch recipeSearch) {
        log.info("Updating the recipe by given properties");
        return recipeService.filterRecipe(recipeSearch);
    }
}
