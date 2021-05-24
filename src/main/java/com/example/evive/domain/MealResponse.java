package com.example.evive.domain;

import com.example.evive.pojo.Meal;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class MealResponse
{
    private ServiceStatus serviceStatus;
    private String meal;
}
