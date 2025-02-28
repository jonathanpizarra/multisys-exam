package com.multisys.dishtansya.repository;

import com.multisys.dishtansya.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
