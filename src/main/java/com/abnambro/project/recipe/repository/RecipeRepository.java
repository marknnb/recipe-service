package com.abnambro.project.recipe.repository;

import com.abnambro.project.recipe.entity.Recipe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface RecipeRepository extends JpaRepository<Recipe, Long>, JpaSpecificationExecutor<Recipe> {
    @Query(value = "SELECT DISTINCT r.* FROM Recipe r " +
            "LEFT JOIN Ingredient i ON r.id = i.recipe_id " +
            "LEFT JOIN Instruction instr ON r.id = instr.recipe_id " +
            "WHERE (:isVegetarian IS NULL OR r.recipe_type = :isVegetarian) " +
            "AND (:servings IS NULL OR r.servings = :servings) " +
            "AND (:ingredientName IS NULL OR i.name LIKE CONCAT ('%',:ingredientName,'%') ) " +
            "AND (:excludeIngredientName IS NULL OR i.name not LIKE CONCAT ('%',:excludeIngredientName,'%') )" +
            "AND (:instructionText IS NULL OR instr.description LIKE CONCAT ('%',:instructionText,'%'))",
            nativeQuery = true)
    List<Recipe> findRecipesByCriteria(@Param("isVegetarian") String recipeType,
                                       @Param("servings") Integer servings,
                                       @Param("ingredientName") String ingredientName,
                                       @Param("excludeIngredientName") String excludeIngredientName,
                                       @Param("instructionText") String instructionText);

}
