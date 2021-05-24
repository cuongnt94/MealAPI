package com.example.evive.pojo;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString

public class Lunch extends Meal
{
    public Lunch()
    {
        setType("lunch");
        setSide("Chips");
        setDrink("Soda");
        setMain("Salad");
        setDesert(null);

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

        if(getNumDesert() > 0)
        {
            sb.append(", ");
            sb.append(getDesert());
        }

        return sb.toString();
    }
}
