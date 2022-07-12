package com.stock.stockapp.wscontroller;
import com.stock.stockapp.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
//@Controller or @Component("Topic") should be used as annotion to retrieve data by using websocket
@Component("Products")
public class WsProduct {

    @Autowired
    ProductService productService;
    @Autowired
    SimpMessagingTemplate smpMsg;

    @Scheduled(cron = "*/5 * * * * *")
    public void informClient(){
        smpMsg.convertAndSend("/topic/Products",productService.getListOfProducts());
    }

}
