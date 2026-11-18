package org.example.billingservice;

import org.example.billingservice.entities.Bill;
import org.example.billingservice.entities.ProductItem;
import org.example.billingservice.repository.BillRepository;
import org.example.billingservice.repository.ProductItemRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import java.util.Date;

@SpringBootApplication
@EnableFeignClients
public class BillingServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(BillingServiceApplication.class, args);
    }

    @Bean
    CommandLineRunner start(BillRepository billRepository, ProductItemRepository productItemRepository) {
        return args -> {
            Bill bill = Bill.builder().billingDate(new Date()).customerId(1L).build();
            billRepository.save(bill);
            for (Long i = 1L; i <= 3L; i++) {
                ProductItem productItem = ProductItem.builder()
                        .productId(i)
                        .price(100.0 * i)
                        .quantity(1 + (int)(Math.random()*10))
                        .bill(bill)
                        .build();
                productItemRepository.save(productItem);
            }
        };
    }
}