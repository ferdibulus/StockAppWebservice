package com.stock.stockapp.controller;

import com.stock.stockapp.entity.Products;
import com.stock.stockapp.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class ProductController {

    @Autowired
    ProductService productService;

    @GetMapping("/products")
    public List<Products> getListOfProducts(){
        return productService.getListOfProducts();
    }

    @PostMapping("/products")
    public String save(@RequestBody Products products){
        try {
            productService.save(products);
        }catch (Exception e){
            return "Fail";
        }
        return "Success";
    }
    @PutMapping("/products/{id}")
    public String update(@RequestBody Products item,@PathVariable Integer id){
        try{
            Optional<Products> product = productService.findById(id);
            product.map(temp->{
                temp.setName(item.getName());
                temp.setBarcode(item.getBarcode());
                temp.setAmount(item.getAmount());
                temp.setPrice(item.getPrice());
                productService.save(temp);
                return "";
            });
        }catch (Exception e) {
            return "Fail";
        }
        return "Success";
    }

    @DeleteMapping("/products/{id}")
    public String  delete(@PathVariable Integer id) {
        try{
            productService.deleteById(id);
        }catch (Exception e){
            return "Fail";
        }
        return "Success";
    }

}
