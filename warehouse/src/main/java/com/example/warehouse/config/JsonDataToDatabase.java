package com.example.warehouse.config;

import com.example.warehouse.model.Vehicle;
import com.example.warehouse.model.Warehouse;
import com.example.warehouse.model.WarehousesData;
import com.example.warehouse.repository.WarehouseRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
public class JsonDataToDatabase implements CommandLineRunner {

    private final WarehouseRepository warehouseRepository;
    ObjectMapper mapper;

    public JsonDataToDatabase(WarehouseRepository warehouseRepository) {
        this.warehouseRepository = warehouseRepository;
        mapper = new ObjectMapper();
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    }

    public List<Warehouse> getWarehouse() throws JsonProcessingException {
        Warehouse[] warehouses = mapper.readValue(WarehousesData.jsonData, Warehouse[].class);
        var warehouseList = Arrays.asList(warehouses);
        sortWarehouseByDate(warehouseList);
        return warehouseList;
    }

    public List<Warehouse> sortWarehouseByDate(List<Warehouse> warehouses) {
        for (Warehouse warehouse : warehouses) {
            var vehicles = sortByDate(warehouse.getCars().getVehicles());
            warehouse.getCars().setVehicles(vehicles);
        }
        return warehouses;
    }

    public List<Vehicle> sortByDate(List<Vehicle> vehicles) {
        vehicles.sort((vehicle1, vehicle2) -> {
            if (vehicle1.getDateAdded() == null || vehicle2.getDateAdded() == null)
                return 0;
            return vehicle1.getDateAdded().compareTo(vehicle2.getDateAdded());
        });
        return vehicles;
    }

    public String getPrettyJson(Object object) throws JsonProcessingException {
        return mapper.writerWithDefaultPrettyPrinter().writeValueAsString(object);
    }

    @Override
    public void run(String... args) throws Exception {
        warehouseRepository.deleteAll();
        warehouseRepository.saveAll(getWarehouse());
    }
}
