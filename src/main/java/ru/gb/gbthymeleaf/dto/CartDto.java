package ru.gb.gbthymeleaf.dto;

import lombok.*;
import ru.gb.gbthymeleaf.entity.Cart;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CartDto   {

        private Long id;
        private List<CartProductDto> cartProducts;

        public CartDto (Cart cart){
                this.id = cart.getId();
                this.cartProducts = cart.getCartProducts().stream()
                        .map(CartProductDto::new).collect(Collectors.toList());
        }
}
