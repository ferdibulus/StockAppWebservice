package com.stock.stockapp.handler;

import com.stock.stockapp.entity.Products;
import com.stock.stockapp.service.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Queue;
import java.util.concurrent.*;

@Component
@AllArgsConstructor
public class ProductHandler {

    private BlockingQueue<Products> products;

    private ProductService productService;

    @PostConstruct
    public void consumeQueue(){
        ExecutorService distirbutor = Executors.newSingleThreadExecutor();
        ExecutorService worker = Executors.newFixedThreadPool(50);

        distirbutor.execute(()->{
                while(true){
                    try {
                        System.out.println();
                        Products p1 = this.products.take();
                        if(p1 != null){
                           worker.execute(()->productService.save(p1));
                        }
                    } catch (Exception e) {
                        Thread.currentThread().interrupt();
                    }
                }
                });
    }

    public Products handle(Products p) throws InterruptedException {
        try {
            products.add(p);
            System.out.println();
        }catch (Exception e){
            Thread.currentThread().interrupt();
        }
        return p;
    }
}
