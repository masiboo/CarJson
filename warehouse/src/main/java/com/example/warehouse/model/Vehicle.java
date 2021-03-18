package com.example.warehouse.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
public class Vehicle {
    @JsonProperty("_id")
    private int id;
    @JsonProperty("make")
    private String mark;
    private String model;
    @JsonProperty("year_model")
    private int year;
    private double price;
    private boolean licensed;
    @JsonProperty("date_added")
    private Date dateAdded;
}
