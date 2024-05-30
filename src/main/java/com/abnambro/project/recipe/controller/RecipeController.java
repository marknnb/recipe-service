package com.abnambro.project.recipe.controller;

import com.abnambro.project.recipe.model.RecipeRequest;
import com.abnambro.project.recipe.model.RecipeSearch;
import com.abnambro.project.recipe.model.create_recipe.response.CreateRecipeResponse;
import com.abnambro.project.recipe.model.get_recipe.response.RecipeResponse;
import com.abnambro.project.recipe.service.RecipeService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
        List<RecipeResponse> recipeList = recipeService.getRecipeList(page, size);
        return recipeList;
    }

    @GetMapping(value = "/{id}")
    public RecipeResponse getRecipe(@PathVariable(name = "id") Integer id) {
        log.info("Getting the recipe by its id. Id: {}", id);
        return recipeService.getRecipeById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CreateRecipeResponse createRecipe(@Valid @RequestBody RecipeRequest request) {
        log.info("Creating the recipe with properties");
        return recipeService.createRecipe(request);
    }

    @PutMapping(value = "/{id}")
    public void updateRecipe(@PathVariable("id") Long recipeId, @RequestBody RecipeRequest request) {
        log.info("Updating the recipe by given properties");
        recipeService.updateRecipe(recipeId, request);
    }

    @PostMapping(value = "/search")
    public List<RecipeResponse> filterRecipe(@RequestBody RecipeSearch recipeSearch) {
        log.info("Updating the recipe by given properties");
        return recipeService.filterRecipe(recipeSearch);
    }


}
