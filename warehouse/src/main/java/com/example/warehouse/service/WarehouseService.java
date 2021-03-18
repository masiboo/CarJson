package com.example.warehouse.service;


import com.example.warehouse.model.Warehouse;
import com.example.warehouse.repository.WarehouseRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static java.lang.Math.toIntExact;

@Service
public class WarehouseService {

    private final WarehouseRepository warehouseRepository;

    public WarehouseService(WarehouseRepository warehouseRepository) {
        this.warehouseRepository = warehouseRepository;
    }

    public Page<Warehouse> buildPageable(List<Warehouse> warehouses, Pageable pageable){
        int total = warehouses.size();
        int start = toIntExact(pageable.getOffset());
        int end = Math.min((start + pageable.getPageSize()), total);
        List<Warehouse> returnWarehouseList = new ArrayList<>();
        if (start <= end) {
            returnWarehouseList = warehouses.subList(start, end);
        }
        return new PageImpl<>(returnWarehouseList, pageable, total);

    }


    public List<Warehouse> getAllWareHouses() {
        return warehouseRepository.findAll();
    }

}
