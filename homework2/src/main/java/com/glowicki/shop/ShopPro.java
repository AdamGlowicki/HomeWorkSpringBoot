package com.glowicki.shop;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Profile;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Profile("Pro")
@Getter
@Setter
@ConfigurationProperties(prefix = "shop-data")
public class ShopPro {

    private int taxRate;
    private int discount;

    @Autowired
    private ShopService shopService;

    @EventListener(ApplicationReadyEvent.class)
    public void get() {
        List<Product> products = shopService.getProducts();//
        products.forEach(System.out::println);
        double sumPrice = shopService.getSumPrice(products);
        double priceWithTax = shopService.countPriceWithTax(taxRate, sumPrice);
        double priceWithDiscount = shopService.roundToTwo(shopService.countPriceWithDiscount(priceWithTax, discount));
        System.out.println("Sum price = " + priceWithDiscount + " - price with tax and discount");
    }
}
