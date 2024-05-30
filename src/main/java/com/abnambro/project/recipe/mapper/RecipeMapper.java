package com.abnambro.project.recipe.mapper;

import com.abnambro.project.recipe.entity.Ingredient;
import com.abnambro.project.recipe.entity.Instruction;
import com.abnambro.project.recipe.entity.Recipe;
import com.abnambro.project.recipe.model.RecipeRequest;
import com.abnambro.project.recipe.model.get_recipe.response.IngredientResponse;
import com.abnambro.project.recipe.model.get_recipe.response.InstructionResponse;
import com.abnambro.project.recipe.model.get_recipe.response.RecipeResponse;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class RecipeMapper {

    public List<RecipeResponse> mapToRecipeResponse(List<Recipe> content) {
        log.info(content.toString());
        return content.stream().map(this::mapToRecipeResponse).toList();
    }

    public RecipeResponse mapToRecipeResponse(Recipe recipe) {
        return Optional.of(recipe)
                .map(item -> {
                    List<Instruction> instructions = item.getInstructions();
                    List<Ingredient> ingredients = item.getIngredients();
                    return RecipeResponse.builder()
                            .recipeId(item.getId().toString())
                            .name(item.getName())
                            .type(item.getType())
                            .numberOfServings(item.getServings())
                            .ingredientResponses(getIngredientResponses(ingredients))
                            .instructionResponses(getInstructionResponses(instructions))
                            .build();
                })
                .orElseThrow(() -> new RuntimeException("Error in mapping Recipe"));
    }

    private static List<InstructionResponse> getInstructionResponses(List<Instruction> instructions) {
        return instructions.stream()
                .map(instruction -> InstructionResponse.builder()
                        .instruction(instruction.getDescription())
                        .build())
                .toList();
    }

    private static List<IngredientResponse> getIngredientResponses(List<Ingredient> ingredients) {
        return ingredients.stream()
                .map(ingredient ->
                        IngredientResponse.builder().name(ingredient.getName()).build())
                .toList();
    }

    public Recipe mapToRecipeEntity(RecipeRequest request) {
        List<String> instructions = request.getInstructions();
        List<String> ingredientIds = request.getIngredients();
        AtomicInteger count = new AtomicInteger(0);
        Recipe recipeEntity = new Recipe();
        recipeEntity.setName(request.getName());
        recipeEntity.setType(request.getType().toString());
        recipeEntity.setServings(request.getNumberOfServings());
        recipeEntity.setName(request.getName());
        recipeEntity.setInstructions(mapToInstructionEntity(instructions, count, recipeEntity));
        recipeEntity.setIngredients(mapToIngredientEntity(ingredientIds, recipeEntity));
        return recipeEntity;
    }

    private static List<Instruction> mapToInstructionEntity(
            List<String> instructions, AtomicInteger count, Recipe recipeEntity) {
        return instructions.stream()
                .map(instruction -> {
                    Instruction instructionEntity = new Instruction();
                    instructionEntity.setDescription(instruction);
                    instructionEntity.setStep(count.incrementAndGet());
                    instructionEntity.setRecipe(recipeEntity);
                    return instructionEntity;
                })
                .toList();
    }

    private static List<Ingredient> mapToIngredientEntity(List<String> ingredientIds, Recipe recipeEntity) {
        return ingredientIds.stream()
                .map(ingredientName -> {
                    Ingredient ingredientEntity = new Ingredient();
                    ingredientEntity.setName(ingredientName);
                    ingredientEntity.setRecipe(recipeEntity);
                    return ingredientEntity;
                })
                .toList();
    }
}
