package com.glowicki.shop;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Profile;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Profile("Plus")
public class ShopPlus {


    @Value("${shop.taxRate}")
    private int taxRate;

    @Autowired
    private ShopService shopService;

    @EventListener(ApplicationReadyEvent.class)
    public void get() {
        List<Product> products = shopService.getProducts();//
        products.forEach(System.out::println);
        double sumPrice = shopService.getSumPrice(products);
        double priceWithTax = shopService.roundToTwo(shopService.countPriceWithTax(taxRate, sumPrice));
        System.out.println("Sum price = " + priceWithTax + " - price with tax");
    }
}
