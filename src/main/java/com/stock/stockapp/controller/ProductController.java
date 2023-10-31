package com.stock.stockapp.controller;

import com.stock.stockapp.entity.Products;
import com.stock.stockapp.handler.ProductHandler;
import com.stock.stockapp.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/rest")
public class ProductController {

    @Autowired
    ProductService productService;

    private final ProductHandler productHandler;

    @GetMapping("/products")
    public List<Products> getListOfProducts(){
        return productService.getListOfProducts();
    }

    @PostMapping("/products")
    public void save(@RequestBody Products products) throws InterruptedException {
        productHandler.handle(products);
    }
    @PutMapping("/products/{id}")
    public Products update(@RequestBody Products item,@PathVariable Integer id){
        return productService.update(item,id);
    }

    @DeleteMapping("/products/{id}")
    public String  delete(@PathVariable Integer id) {
           return productService.deleteById(id);
    }

}
