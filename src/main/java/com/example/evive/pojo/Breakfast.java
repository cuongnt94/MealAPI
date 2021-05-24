package com.example.evive.pojo;

import lombok.*;

@Getter
@Setter
@ToString

public class Breakfast extends Meal
{
    public Breakfast()
    {
        setType("breakfast");
        setSide("Toast");
        setDrink("Coffee");
        setMain("Eggs");
        setDesert(null);

        setNumMain(0);
        setNumSide(0);
        setNumDrink(0);
        setNumDesert(0);
    }

    @Override
    public String toString()
    {
        StringBuilder sb = new StringBuilder();
        sb.append(getMain());
        sb.append(", ");
        sb.append(getSide());
        if (getNumSide() > 1) {
            sb.append("(" + getNumSide() + ")");
        }
        sb.append(", ");

        if(getNumDrink() == 0)
        {
            sb.append("Water");
        }
        else if(getNumDrink() >= 1)
        {
            sb.append(getDrink());
            if(getNumDrink() > 1)
            {
                sb.append("(" + getNumDrink() + ")");
            }
        }

        return sb.toString();
    }
}
