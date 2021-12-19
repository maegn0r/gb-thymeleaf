package ru.gb.gbthymeleaf.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.gb.gbthymeleaf.dto.CartDto;
import ru.gb.gbthymeleaf.dto.CartProductDto;
import ru.gb.gbthymeleaf.service.CartProductServiceFacade;

import javax.annotation.PostConstruct;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/cart")
public class CartController {

    private CartDto cartDto;
    private final CartProductServiceFacade service;

    @PostConstruct
    private void init() {
        cartDto = service.findCartById(7L);
    }

    @GetMapping
    public ResponseEntity<?> getCartProductList() {
        cartDto = service.findCartById(cartDto.getId());
        return new ResponseEntity<>(cartDto, HttpStatus.OK);
    }

    @PutMapping("/addToCart/{productId}")
    public ResponseEntity<?> addProductToCart(@PathVariable(name = "productId") Long productId) {

        cartDto = service.saveAndUpdate(productId, cartDto.getId());
        return new ResponseEntity<>(cartDto, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteProductFromCart(@PathVariable(name = "id") Long id) {
        CartProductDto cartProductDto = cartDto.getCartProducts()
                .stream()
                .filter(item -> item
                        .getId()
                        .equals(id))
                .findFirst().get();

        service.decreaseCartProduct(cartProductDto);
    }

    @PutMapping("/increase/{id}")
    public void increaseProductsCountInCart(@PathVariable(name = "id") Long id) {
        CartProductDto cartProductDto = cartDto.getCartProducts()
                .stream()
                .filter(item -> item.getId()
                        .equals(id))
                .findFirst().get();
        service.increaseCartProduct(cartProductDto);
    }
}

