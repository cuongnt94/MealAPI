package com.example.evive.controller;

import com.example.evive.domain.MealResponse;
import com.example.evive.domain.ServiceStatus;
import com.example.evive.pojo.Breakfast;
import com.example.evive.pojo.Dinner;
import com.example.evive.pojo.Lunch;
import com.example.evive.pojo.Meal;
import com.example.evive.service.MealService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class MealController
{
    private MealService mealService = new MealService();

    private static final String WRONG_INPUT_NUMBER = "WRONG INPUT NUMBER";
    private static final String WRONG_INPUT_TYPE = "INVALID TYPE OF MEAL";
    private static final String ALL_MISSING = "Main is missing, side is missing";
    /*
    Assume that the input will have the form: Type 1,2,3,etc
     */
    @GetMapping("/")
    public MealResponse home(@RequestParam("input") String input)
    {
        Meal meal;
        MealResponse mealResponse = new MealResponse();

        int index = input.indexOf(" ");
        if(index == -1 || index == input.length() - 1)
        {
            prepareResponse(mealResponse, false, ALL_MISSING, null);
            return mealResponse;
        }

        String type = input.substring(0, index);


        if(type.equalsIgnoreCase("breakfast"))
        {
            meal = new Breakfast();
        }
        else if(type.equalsIgnoreCase("lunch"))
        {
            meal = new Lunch();
        }
        else if(type.equalsIgnoreCase("dinner"))
        {
            meal = new Dinner();
        }
        else
        {
            meal = null;
        }

        if(meal == null)
        {
            prepareResponse(mealResponse, false, WRONG_INPUT_TYPE, null);
            return mealResponse;
        }

        String part2 = input.substring(index + 1);
        System.out.println("Input number: " + part2);
        if(!mealService.setupMeal(part2, meal))
        {
            prepareResponse(mealResponse, false, WRONG_INPUT_NUMBER, null);
            return mealResponse;
        }

        String errorMessage = mealService.checkValid(meal);
        if(!errorMessage.equalsIgnoreCase(""))
        {
            prepareResponse(mealResponse, false, errorMessage, null);
            return mealResponse;
        }

        prepareResponse(mealResponse, true, "", meal);

        return mealResponse;
    }

    private void prepareResponse(MealResponse response, boolean success, String errorMessage, Meal meal) {
        response.setServiceStatus(new ServiceStatus(success ? "200" : "500", success, errorMessage));
        if(meal != null)
            response.setMeal(meal.toString());
        else
            response.setMeal("");
        System.out.println("Output: " + meal);
    }

}
