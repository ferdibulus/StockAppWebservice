package com.stock.stockapp.repository;

import com.stock.stockapp.entity.Products;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductDao extends JpaRepository<Products,Integer> {
    List<Products> findAllById(Iterable<Integer> integers);
}
