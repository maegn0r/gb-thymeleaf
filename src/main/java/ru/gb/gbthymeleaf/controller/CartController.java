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


@Controller
@RequiredArgsConstructor
@RequestMapping("/cart")
public class CartController {

    private final CartService cartService;
    private Cart cart;
    private final ProductService productService;
    private final CartProductService cartProductService;

    @GetMapping
    public String getCartProductList(Model model) {
        if (cart == null) {
            cart = cartService.save(cartService.createEmptyCart());
        }
        model.addAttribute("cart", cartService.findById(cart.getId()));
        return "cart-product-list";
    }
    @GetMapping("/addToCart")
    public String addProductToCart(@RequestParam(name = "productId") Long productId){
        if (cart == null) {
            cart = cartService.save(cartService.createEmptyCart());
        }
        Product product = productService.findById(productId);
        cartProductService.save(CartProduct.create(product,cart));

            return "redirect:/product/all";
        }
    }

