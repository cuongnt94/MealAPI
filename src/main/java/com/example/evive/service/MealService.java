package com.example.evive.service;


import com.example.evive.pojo.Dinner;
import com.example.evive.pojo.Meal;

import java.util.HashMap;
import java.util.Map;

public class MealService {

    public MealService()
    {

    }

    /*
    Check if the input number is in range of [1,4] and set up the meal based on it.
     */
    public boolean setupMeal(String s, Meal meal)
    {
        String[] arr = s.split(",");
        Map<Integer, Integer> map = new HashMap<>();
        for(String t: arr)
        {
            int temp = Integer.parseInt(t);
            map.put(temp, map.getOrDefault(temp, 0) + 1);
        }

        for(Map.Entry<Integer, Integer> element: map.entrySet())
        {
            int key = element.getKey();
            int value = element.getValue();

            if(key == 1)
            {
                meal.setNumMain(value);
            }
            else if(key == 2)
            {
                meal.setNumSide(value);
            }
            else if(key == 3)
            {
                meal.setNumDrink(value);
            }
            else if(key == 4)
            {
                meal.setNumDesert(value);
            }

        }

        map.remove(1);
        map.remove(2);
        map.remove(3);
        map.remove(4);
        if(map.size() != 0)
        {
            return false;
        }
        else
        {
            return true;
        }

    }


    /*
    Return the error message based on the Rules:
    1. An order consists of a meal and collection of comma separated item Ids.
    2. The system should return the name of the items ordered
    3. The system should always return items in the following order: meal, side, drink
    4. If multiple items are ordered, the number of items should be indicated
    5. Each order must contain a main and a side
    6. If no drink is ordered, water should be returned
    7. At breakfast, multiple cups of coffee can be ordered
    8. At lunch, multiple sides can be ordered
    9. At dinner,dessert must be ordered
    10. At dinner, water is always provided
     */
    public String checkValid(Meal meal)
    {
        StringBuilder sb = new StringBuilder();

        if(meal.getNumMain() == 0)
        {
            sb.append("Main is missing");
        }
        else if(meal.getNumMain() > 1)
        {
            sb.append(meal.getMain());
            sb.append(ErrorMessage.MORETHANONCE);
        }

        if(meal.getNumSide() == 0)
        {
            if(sb.toString().length() != 0)
                sb.append(", ");

            sb.append("Side is missing");
        }

        if(meal.getType().equalsIgnoreCase("breakfast"))
        {
            if(meal.getNumSide() > 1)
            {
                if(sb.toString().length() > 0)
                    sb.append(", ");

                sb.append(meal.getSide());
                sb.append(ErrorMessage.MORETHANONCE);
            }

            if(meal.getNumDesert() > 0)
            {
                if(sb.toString().length() > 0)
                    sb.append(", ");

                sb.append(ErrorMessage.DESERT_ERROR);
                sb.append(" for " + meal.getType());
            }
        }
        else if(meal.getType().equalsIgnoreCase("lunch"))
        {
            if(meal.getNumDrink() > 1)
            {
                if(sb.toString().length() != 0)
                    sb.append(", ");

                sb.append(meal.getDrink());
                sb.append(ErrorMessage.MORETHANONCE);
            }

            if(meal.getNumDesert() > 0)
            {
                if(sb.toString().length() > 0)
                    sb.append(", ");

                sb.append(ErrorMessage.DESERT_ERROR);
                sb.append(" for " + meal.getType());
            }

        }
        else if(meal.getType().equalsIgnoreCase("dinner"))
        {
            if(meal.getNumSide() > 1)
            {
                if(sb.toString().length() > 0)
                    sb.append(", ");

                sb.append(meal.getSide());
                sb.append(ErrorMessage.MORETHANONCE);
            }

            if(meal.getNumDrink() > 1)
            {
                if(sb.toString().length() > 0)
                    sb.append(", ");

                sb.append(meal.getDrink());
                sb.append(ErrorMessage.MORETHANONCE);
            }

            if(meal.getNumDesert() == 0)
            {
                if(sb.toString().length() > 0)
                    sb.append(", ");

                sb.append("Desert is missing");
            }
            else if(meal.getNumDesert() > 1)
            {
                if(sb.toString().length() > 0)
                    sb.append(", ");

                sb.append(meal.getDesert());
                sb.append(ErrorMessage.MORETHANONCE);
            }
        }
        if(sb.toString().length() > 0)
            return "Unable to process: " + sb.toString();
        else
            return "";
    }
}
