package com.stock.stockapp.service;

import com.stock.stockapp.entity.Products;
import com.stock.stockapp.repository.ProductDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    @Autowired
    ProductDao productDao;

    @Autowired
    private EntityManager entityManager;

    public List<Products> getListOfProducts() {
        return productDao.findAll();
    }

    public void save(Products products) {
        productDao.save(products);
    }

    public Optional<Products> findById(Integer id) {
        return productDao.findById(id);
    }

    public void deleteById(Integer id) {
        productDao.deleteById(id);
    }
}

