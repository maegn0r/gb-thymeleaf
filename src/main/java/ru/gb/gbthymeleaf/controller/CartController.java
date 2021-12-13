package ru.gb.gbthymeleaf.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.gb.gbthymeleaf.entity.Cart;
import ru.gb.gbthymeleaf.entity.CartProduct;
import ru.gb.gbthymeleaf.entity.Product;
import ru.gb.gbthymeleaf.service.CartProductService;
import ru.gb.gbthymeleaf.service.CartService;
import ru.gb.gbthymeleaf.service.ProductService;

import javax.annotation.PostConstruct;


@Controller
@RequiredArgsConstructor
@RequestMapping("/cart")
public class CartController {

    private final CartService cartService;
    private Cart cart;
    private final ProductService productService;
    private final CartProductService cartProductService;

    @PostConstruct
    private void init() {
        cart = cartService.findById(2L);
    }

    @GetMapping
    public String getCartProductList(Model model) {
//        if (cart == null) {
//            cart = cartService.save(cartService.createEmptyCart());
//        }
        model.addAttribute("cart", cartService.findById(cart.getId()));
        return "cart-product-list";
    }

    @GetMapping("/addToCart")
    public String addProductToCart(@RequestParam(name = "productId") Long productId) {
//        if (cart == null) {
//            cart = cartService.save(cartService.createEmptyCart());
//        }
        Product product = productService.findById(productId);

        CartProduct cartProduct = cart.getCartProducts()
                .stream()
                .filter(item -> item.getProduct()
                        .getId()
                        .equals(productId))
                .findFirst().orElse(null);
        if (cartProduct == null) {
            cart.getCartProducts().add(CartProduct.create(product, cart));
        } else cartProduct.setCount(cartProduct.getCount() + 1L);

        cart = cartService.save(cart);
        return "redirect:/product/all";
    }

    @GetMapping("/delete")
    public String deleteProductFromCart(@RequestParam(name = "id") Long id) {
        CartProduct cartProduct = cart.getCartProducts()
                .stream()
                .filter(item -> item
                        .getId()
                        .equals(id))
                .findFirst().get();

            if (cartProduct.getCount() == 1) {
//                cart.getCartProducts().remove(cartProduct);
//                cart = cartService.save(cart);
                cartProductService.delete(cartProduct);
            } else {
                cartProduct.setCount(cartProduct.getCount() - 1L);
                cart = cartService.save(cart);
            }

        return "redirect:/cart";
    }
}

