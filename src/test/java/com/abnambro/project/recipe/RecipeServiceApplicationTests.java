package com.abnambro.project.recipe;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;

@SpringBootTest
@Import(TestConfigs.class)
class RecipeServiceApplicationTests {

    @Test
    void contextLoads() {}
}
