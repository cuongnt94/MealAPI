package com.example.evive.pojo;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@ToString

public class Dinner extends Meal
{
    public Dinner()
    {
        setType("dinner");
        setSide("Potatoes");
        setDrink("Wine");
        setMain("Steak");
        setDesert("Cake");


        setNumMain(0);
        setNumSide(0);
        setNumDrink(0);
        setNumDesert(0);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getMain());
        sb.append(", ");
        sb.append(getSide());
        if (getNumSide() > 1) {
            sb.append("(" + getNumSide() + ")");
        }

        if(getNumDrink() > 0)
        {
            sb.append(", ");
            sb.append(getDrink());
        }

        sb.append(", Water");

        if(getNumDesert() > 0)
        {
            sb.append(", ");
            sb.append(getDesert());
        }

        return sb.toString();
    }

}
