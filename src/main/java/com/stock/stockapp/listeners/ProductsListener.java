package com.stock.stockapp.listeners;

import com.stock.stockapp.entity.Products;
import com.stock.stockapp.wscontroller.WsProduct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.util.LinkedCaseInsensitiveMap;

import javax.persistence.*;
import java.util.HashMap;
import java.util.Map;

public class ProductsListener {
    @Autowired
    SimpMessagingTemplate smpMsg;

    Map<String,Products> tempListener = new HashMap<>();

    @PrePersist
    void onPrePersist(Products product) {
        System.out.println("ProductsListener.onPrePersist(): " + product);
    }
    @PostPersist
    void onPostPersist(Products product) {
        tempListener.put("insert",product);
        smpMsg.convertAndSend("/topic/Products",tempListener);
        System.out.println("ProductsListener.onPostPersist(): " + tempListener);
        tempListener.clear();
    }
    @PostLoad
    void onPostLoad(Products product) {
        System.out.println("ProductsListener.onPostLoad(): " + product);
    }
    @PreUpdate
    void onPreUpdate(Products product) {
        System.out.println("ProductsListener.onPreUpdate(): " + product);
    }
    @PostUpdate
    void onPostUpdate(Products product) {
        tempListener.put("update",product);
        smpMsg.convertAndSend("/topic/Products",tempListener);
        System.out.println("ProductsListener.onPostUpdate(): " + product);
        tempListener.clear();
    }
    @PreRemove
    void onPreRemove(Products product) {
        System.out.println("BookListener.onPreRemove(): " + product);
    }
    @PostRemove
    void onPostRemove(Products product) {
        tempListener.put("remove",product);
        smpMsg.convertAndSend("/topic/Products",tempListener);
        System.out.println("BookListener.onPostRemove(): " + product);
        tempListener.clear();
    }
}
