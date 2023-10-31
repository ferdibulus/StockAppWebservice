package com.stock.stockapp.bean;

import com.stock.stockapp.entity.Products;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Queue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.LinkedTransferQueue;

@Configuration
public class ProductQueue{

    @Bean
    public BlockingQueue<Products> productCommandQueue() {
        return new LinkedBlockingQueue<>();
    }
}
