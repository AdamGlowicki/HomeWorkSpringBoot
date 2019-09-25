package com.glowicki.shop;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Profile;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@Profile("Basic")
public class ShopBasic {

    @Autowired
    private ShopService shopService;

    @EventListener(ApplicationReadyEvent.class)
    public void get() {
        List<Product> products = shopService.getProducts();//
        products.forEach(System.out::println);
        double sumPrice = shopService.getSumPrice(products);
        double price = shopService.roundToTwo(sumPrice);
        System.out.println("Sum price = " + price);
    }
}
