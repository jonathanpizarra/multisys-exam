package com.multisys.dishtansya.req;

import io.swagger.v3.oas.annotations.media.Schema;

public class ProductReq {

    @Schema(name = "product_id")
    long productId ;

    @Schema(name = "quantity")
    int quantity;

    public long getProductId() {
        return productId;
    }

    public void setProductId(long productId) {
        this.productId = productId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
