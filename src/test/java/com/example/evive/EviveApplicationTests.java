package com.example.evive;

import com.example.evive.pojo.Breakfast;
import com.example.evive.pojo.Dinner;
import com.example.evive.pojo.Lunch;
import com.example.evive.pojo.Meal;
import com.example.evive.service.ErrorMessage;
import com.example.evive.service.MealService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;
import static org.assertj.core.api.Assertions.*;
@SpringBootTest
class EviveApplicationTests {

    /*
    Breakfast test
    */
    @Test
    void breakfast_test()
    {

        Meal meal = new Breakfast();
        MealService mealService = new MealService();
        /*
        Test all the basic properties of breakfast
         */
        assertThat(meal.getType()).isEqualToIgnoringCase("breakfast");
        assertThat(meal.getMain()).isEqualToIgnoringCase("eggs");
        assertThat(meal.getSide()).isEqualToIgnoringCase("toast");
        assertThat(meal.getDrink()).isEqualToIgnoringCase("coffee");

        /*
        2 test cases with base scenario in different orders.
         */
        meal = new Breakfast();
        assertThat(mealService.setupMeal("1,2,3", meal)).isTrue();
        assertThat(mealService.checkValid(meal)).isEqualToIgnoringCase("");
        assertThat(meal.toString()).isEqualToIgnoringCase("Eggs, Toast, Coffee");

        meal = new Breakfast();
        assertThat(mealService.setupMeal("3,2,1", meal)).isTrue();
        assertThat(mealService.checkValid(meal)).isEqualToIgnoringCase("");
        assertThat(meal.toString()).isEqualToIgnoringCase("Eggs, Toast, Coffee");

        /*
        When drink is not ordered, water is provided.
         */
        meal = new Breakfast();
        assertThat(mealService.setupMeal("1,2", meal)).isTrue();
        assertThat(mealService.checkValid(meal)).isEqualToIgnoringCase("");
        assertThat(meal.toString()).isEqualToIgnoringCase("Eggs, Toast, Water");

        /*
        When multiple coffee is ordered, add the number after it
         */
        meal = new Breakfast();
        assertThat(mealService.setupMeal("1,2,3,3,3", meal)).isTrue();
        assertThat(mealService.checkValid(meal)).isEqualToIgnoringCase("");
        assertThat(meal.toString()).isEqualToIgnoringCase("Eggs, Toast, Coffee(3)");

        /*
        Test case when Side is missing
         */
        meal = new Breakfast();
        assertThat(mealService.setupMeal("1", meal)).isTrue();
        assertThat(mealService.checkValid(meal)).isEqualToIgnoringCase("Unable to process: Side is missing");

        /*
        Test case when Main is missing
         */
        meal = new Breakfast();
        assertThat(mealService.setupMeal("2", meal)).isTrue();
        assertThat(mealService.checkValid(meal)).isEqualToIgnoringCase("Unable to process: Main is missing");

        /*
        Test case when input number is invalid
         */
        meal = new Breakfast();
        assertThat(mealService.setupMeal("1,2,3,4,5", meal)).isFalse();

        /*
        Test case when we include desert in breakfast
         */
        meal = new Breakfast();
        assertThat(mealService.setupMeal("1,2,3,4", meal)).isTrue();
        assertThat(mealService.checkValid(meal)).isEqualToIgnoringCase("Unable to process: " +
                "Desert is not allowed to be ordered for breakfast");

    }

    /*
    Lunch test
    */
    @Test
    void lunch_test()
    {
        Meal meal = new Lunch();
        MealService mealService = new MealService();

        /*
        Test all the basic properties of lunch
         */
        assertThat(meal.getType()).isEqualToIgnoringCase("lunch");
        assertThat(meal.getMain()).isEqualToIgnoringCase("salad");
        assertThat(meal.getSide()).isEqualToIgnoringCase("chips");
        assertThat(meal.getDrink()).isEqualToIgnoringCase("soda");

        /*
        Base test case with valid input
         */
        meal = new Lunch();
        assertThat(mealService.setupMeal("1,2,3", meal)).isTrue();
        assertThat(mealService.checkValid(meal)).isEqualToIgnoringCase("");
        assertThat(meal.toString()).isEqualToIgnoringCase("Salad, Chips, Soda");

        /*
        When drink is not ordered, water is provided.
         */
        meal = new Lunch();
        assertThat(mealService.setupMeal("1,2", meal)).isTrue();
        assertThat(mealService.checkValid(meal)).isEqualToIgnoringCase("");
        assertThat(meal.toString()).isEqualToIgnoringCase("Salad, Chips, Water");

        /*
        When multiple Chips is ordered, add the number after it
        When drink is added, there would be no water
         */
        meal = new Lunch();
        assertThat(mealService.setupMeal("1,2,2,3", meal)).isTrue();
        assertThat(mealService.checkValid(meal)).isEqualToIgnoringCase("");
        assertThat(meal.toString()).isEqualToIgnoringCase("Salad, Chips(2), Soda");

        /*
        When main is ordered more than one
         */
        meal = new Lunch();
        assertThat(mealService.setupMeal("1,1,2,3", meal)).isTrue();
        assertThat(mealService.checkValid(meal)).isEqualToIgnoringCase("Unable to process: Salad cannot be ordered more than once");

        /*
        Test case when we include desert in breakfast
         */
        meal = new Lunch();
        assertThat(mealService.setupMeal("1,2,3,4", meal)).isTrue();
        assertThat(mealService.checkValid(meal)).isEqualToIgnoringCase("Unable to process: " +
                "Desert is not allowed to be ordered for lunch");

        /*
        Test case when Side is missing
         */
        meal = new Lunch();
        assertThat(mealService.setupMeal("1", meal)).isTrue();
        assertThat(mealService.checkValid(meal)).isEqualToIgnoringCase("Unable to process: Side is missing");

        /*
        Test case when Main is missing
         */
        meal = new Lunch();
        assertThat(mealService.setupMeal("2", meal)).isTrue();
        assertThat(mealService.checkValid(meal)).isEqualToIgnoringCase("Unable to process: Main is missing");

        /*
        Test case when input number is invalid
         */
        meal = new Lunch();
        assertThat(mealService.setupMeal("1,2,3,4,5", meal)).isFalse();

    }

    /*
    Dinner test
     */
    @Test
    void dinner_test()
    {
        Meal meal = new Dinner();
        MealService mealService = new MealService();

        /*
        Test all the basic properties of dinner
         */
        assertThat(meal.getType()).isEqualToIgnoringCase("dinner");
        assertThat(meal.getMain()).isEqualToIgnoringCase("steak");
        assertThat(meal.getSide()).isEqualToIgnoringCase("potatoes");
        assertThat(meal.getDrink()).isEqualToIgnoringCase("wine");
        assertThat(meal.getDesert()).isEqualToIgnoringCase("cake");

        /*
        Base test case with valid input
         */
        meal = new Dinner();
        assertThat(mealService.setupMeal("1,2,3,4", meal)).isTrue();
        assertThat(mealService.checkValid(meal)).isEqualToIgnoringCase("");
        assertThat(meal.toString()).isEqualToIgnoringCase("Steak, Potatoes, Wine, Water, Cake");

        /*
        When drink is not ordered, water is provided.
         */
        meal = new Dinner();
        assertThat(mealService.setupMeal("1,2,4", meal)).isTrue();
        assertThat(mealService.checkValid(meal)).isEqualToIgnoringCase("");
        assertThat(meal.toString()).isEqualToIgnoringCase("Steak, Potatoes, Water, Cake");

        /*
        Test case when Side and Desert is missing
         */
        meal = new Dinner();
        assertThat(mealService.setupMeal("1,3", meal)).isTrue();
        assertThat(mealService.checkValid(meal)).isEqualToIgnoringCase("Unable to process: Side is missing, Desert is missing");

        /*
        Test case when Main and Desert is missing
         */
        meal = new Dinner();
        assertThat(mealService.setupMeal("2,3", meal)).isTrue();
        assertThat(mealService.checkValid(meal)).isEqualToIgnoringCase("Unable to process: Main is missing, Desert is missing");

        /*
        Test case when Desert is missing
         */
        meal = new Dinner();
        assertThat(mealService.setupMeal("1,2", meal)).isTrue();
        assertThat(mealService.checkValid(meal)).isEqualToIgnoringCase("Unable to process: Desert is missing");


        /*
        Test case when multiple main/side are ordered.
         */
        meal = new Dinner();
        assertThat(mealService.setupMeal("1,1,2,2,3,3,4,4", meal)).isTrue();
        assertThat(mealService.checkValid(meal)).isEqualToIgnoringCase("Unable to process: Steak cannot be " +
                "ordered more than once, Potatoes cannot be ordered more than once, " +
                "Wine cannot be ordered more than once, Cake cannot be ordered more than once");

        /*
        Test case when input number is invalid
         */
        meal = new Dinner();
        assertThat(mealService.setupMeal("1,2,3,4,5", meal)).isFalse();

    }
}
