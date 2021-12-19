package ru.gb.gbthymeleaf.dto;

import lombok.*;
import ru.gb.gbthymeleaf.entity.Cart;
import ru.gb.gbthymeleaf.entity.CartProduct;
import ru.gb.gbthymeleaf.entity.Product;

import javax.persistence.*;
import java.math.BigDecimal;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CartProductDto {

    private Long id;
    private BigDecimal cost;
    private Long count;
    private Long productId;
    private String productTitle;
    private Long cartId;

    public CartProductDto (CartProduct cartProduct){
        this.id = cartProduct.getId();
        this.cost = cartProduct.getCost();
        this.count = cartProduct.getCount();
        this.productId = cartProduct.getProduct().getId();
        this.productTitle = cartProduct.getProduct().getTitle();
        this.cartId = cartProduct.getCart().getId();
    }

    public static CartProductDto create(CartProductDto p, CartDto c) {
        return CartProductDto.builder()
                .cost(p.getCost())
                .productId(p.getProductId())
                .productTitle(p.getProductTitle())
                .count(1L)
                .cartId(c.getId())
                .build();
    }
}
