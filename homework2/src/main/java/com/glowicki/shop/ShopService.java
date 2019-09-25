package com.glowicki.shop;

import lombok.Getter;
import org.springframework.stereotype.Service;

import java.text.DecimalFormat;
import java.util.Arrays;
import java.util.List;

@Service
@Getter
public class ShopService {

    private static final int DIVIDER = 100;
    List<Product> products;

    public ShopService() {
        Product banana = createProduct("Banana");
        Product apple = createProduct("Apple");
        Product carrot = createProduct("Carrot");
        Product orange = createProduct("Orange");
        Product cherry = createProduct("Cherry");

        products = Arrays.asList(banana, apple, carrot, orange, cherry);
    }

    private Product createProduct(String name) {
        return new Product(name, getRandomPrice(50, 300));
    }

    public double getSumPrice(List<Product> products) {
        return products.stream()
                .map(Product::getPrice)
                .mapToDouble(Double::doubleValue).sum();
    }

    public double countPriceWithDiscount(double price, int discount) {
        return price - countDiscount(discount, price);
    }


    public double countPriceWithTax(int taxRate, double price) {
        return price + countTax(taxRate, price);
    }

    public double roundToTwo(double number) {
        DecimalFormat df = new DecimalFormat("0.00");
        return Double.valueOf(df.format(number).replace(",", "."));
    }

    private Double getRandomPrice(double min, double max) {
        double price = (Math.random() * ((max - min) + 1)) + min;
        return roundToTwo(price);
    }

    private double countTax(int taxRate, double price) {
        return price * taxRate / DIVIDER;
    }

    private double countDiscount(int discount, double price) {
        return price - (price * discount / DIVIDER);
    }
}
