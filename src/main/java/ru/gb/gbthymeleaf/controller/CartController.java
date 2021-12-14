package ru.gb.gbthymeleaf.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.gb.gbthymeleaf.entity.Cart;
import ru.gb.gbthymeleaf.entity.CartProduct;
import ru.gb.gbthymeleaf.entity.Product;
import ru.gb.gbthymeleaf.service.CartProductService;
import ru.gb.gbthymeleaf.service.CartService;
import ru.gb.gbthymeleaf.service.ProductService;

import javax.annotation.PostConstruct;
import java.util.Comparator;
import java.util.List;


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
        cart = cartService.findById(cart.getId());
        List<CartProduct> cartProductList = cart.getCartProducts();
        cartProductList.sort(Comparator.comparing(CartProduct::getId));
        model.addAttribute("cartProducts", cartProductList);
        return "cart-product-list";
    }

    @GetMapping("/addToCart")
    public String addProductToCart(@RequestParam(name = "productId") Long productId) {
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
                cartProductService.delete(cartProduct);
            } else {
                cartProduct.setCount(cartProduct.getCount() - 1L);
                cart = cartService.save(cart);
            }

        return "redirect:/cart";
    }
    @GetMapping("/increase")
    public String increaseProductsCountInCart(@RequestParam(name = "id") Long id) {
        cart.getCartProducts()
                .stream()
                .filter(item -> item.getId()
                        .equals(id))
                .findFirst().ifPresent(item -> item.setCount(item.getCount()+1L));
        cart = cartService.save(cart);
        return "redirect:/cart";
    }
}

