package com.multisys.dishtansya.controller;

import com.multisys.dishtansya.req.ProductReq;
import com.multisys.dishtansya.service.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.HashMap;
import java.util.Map;

@Controller
public class ProductController {

    ProductService productService;

    public ProductController(ProductService productService){
        this.productService = productService;
    }

    @PostMapping("/order")
    @Operation(description = "order product")
    public ResponseEntity<Map<String, String>> orderProduct(ProductReq req){
        String msg = productService.orderProduct(req);
        Map<String, String> res = new HashMap<>();
        res.put("message", msg);

        if(msg.equalsIgnoreCase("You have successfully ordered this product.")){
            return new ResponseEntity<>(res, HttpStatus.CREATED);

        }else{
            return new ResponseEntity<>(res, HttpStatus.BAD_REQUEST);
        }

    }
}
