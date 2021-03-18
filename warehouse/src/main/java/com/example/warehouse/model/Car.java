package com.example.warehouse.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class Car {

    private String location;
    private List<Vehicle> vehicles = new ArrayList<>();
}
