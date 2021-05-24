package com.example.evive.pojo;
import lombok.*;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

//@Builder
public class Meal implements Serializable
{
    private String type;
    private String main;
    private String side;
    private String drink;
    private String desert;

    private Integer numMain;
    private Integer numSide;
    private Integer numDrink;
    private Integer numDesert;

}
