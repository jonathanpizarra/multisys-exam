package com.multisys.dishtansya.init;


import com.multisys.dishtansya.model.Product;
import com.multisys.dishtansya.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DataInitializer {

    @Autowired
    private ProductRepository productRepository;

    @Bean
    public CommandLineRunner initData() {
        return args -> {
            Product product = new Product();
            product.setName("p1");
            product.setAvailableStock(100);
            productRepository.save(product);
            System.out.println("id: "+ product.getId());
        };
    }
}