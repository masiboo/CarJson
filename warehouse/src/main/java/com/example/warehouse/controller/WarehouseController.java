package com.example.warehouse.controller;

import com.example.warehouse.model.Warehouse;
import com.example.warehouse.service.WarehouseService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiParam;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = WarehouseController.REQUEST_URL)
public class WarehouseController {

    public static final String REQUEST_URL = "/api/v1/revisions/";
    public static final String GET_ALL_WARE_HOUSES = "allWarehouse";
    private final String PAGE_LIMIT = "100";

    private final WarehouseService warehouseService;

    public WarehouseController(WarehouseService warehouseService) {
        this.warehouseService = warehouseService;
    }

    @GetMapping(value = GET_ALL_WARE_HOUSES, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", dataType = "integer", paramType = "query",
                    value = "page number"),
            @ApiImplicitParam(name = "size", dataType = "integer", paramType = "query",
                    value = "Number of records per page."),
    })
    public List<Warehouse> getAllWarehouse(
            Pageable pageable,
            @ApiParam(value = "sortBy - eg. created, id, fileName")
            @RequestParam(value = "sort_by", required = false, defaultValue = "created") String sortBy,

            @ApiParam(value = "sortDirection (desc/asc)")
            @RequestParam(value = "sort_direction", required = false, defaultValue = "asc") String sortDirection,

            @RequestParam(value = "limit", required = false, defaultValue = PAGE_LIMIT) int limit) {

        var direction = Sort.Direction.fromString(sortDirection);
        var sort = Sort.by(direction, sortBy);
        pageable = PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), sort);

        var warehouses = warehouseService.getAllWareHouses();

        printAll(warehouses);
       // return warehouseService.buildPageable(warehouses, pageable);
        return warehouses;

    }

    private void printAll(List<Warehouse> warehouses) {
        warehouses.forEach(warehouse -> {
            System.out.println("warehouse id: " + warehouse.getId());
            System.out.println("warehouse name: " + warehouse.getName());
            System.out.println("warehouse Location lat: " + warehouse.getLocation().getLat());
            System.out.println("warehouse Location lat: " + warehouse.getLocation().getLon());
            System.out.println("Car location: " + warehouse.getCars().getLocation());
            warehouse.getCars().getVehicles().forEach(v -> {
                System.out.println("id: " + v.getId());
                System.out.println("mark: " + v.getMark());
                System.out.println("model: " + v.getModel());
                System.out.println("price: " + v.getPrice());
                System.out.println("added date: " + v.getDateAdded());
                System.out.println("year: " + v.getYear());
                System.out.println("isLicensed: " + v.isLicensed());
            });
        });
    }

}
