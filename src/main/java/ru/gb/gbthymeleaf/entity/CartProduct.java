package ru.gb.gbthymeleaf.entity;


import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "cart_product")
public class CartProduct {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "cost")
    private BigDecimal cost;

    @Column(name = "count")
    private Long count;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    @ManyToOne()
    @JoinColumn(name = "cart_id")
    private Cart cart;

    public static CartProduct create (Product p, Cart c){
        return CartProduct.builder()
                .cost(p.getCost())
                .product(p)
                .count(1L)
                .cart(c)
                .build();
    }



}
