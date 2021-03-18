package com.example.warehouse.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Warehouse {
    @JsonProperty("_id")
    private int id;
    @JsonProperty("name")
    private String name;
    @JsonProperty("location")
    Location location;
    @JsonProperty("cars")
    Car cars;
}
