package ru.gb.gbthymeleaf.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import ru.gb.gbthymeleaf.entity.Cart;
import ru.gb.gbthymeleaf.entity.CartProduct;
import ru.gb.gbthymeleaf.entity.Product;
import ru.gb.gbthymeleaf.entity.enums.Status;

@Transactional
@Service
@RequiredArgsConstructor
public class CartProductServiceFacade {
    private final CartProductService cartProductService;
    private final CartService cartService;
    private final ProductService productService;

    public Cart findCartById(Long cartId) {
        return cartService.findById(cartId);
    }

    public Product findProductById(Long productId) {
        return productService.findById(productId);
    }

    public Cart saveAndUpdate(Cart cart, Product product) {
        product.setCount(product.getCount() - 1L);
        if(product.getCount() == 0) product.setStatus(Status.DISABLED);
        productService.save(product);
        return cartService.save(cart);
    }


    public void decreaseCartProduct(CartProduct cartProduct) {
        Product product = productService.findById(cartProduct.getProduct().getId());
        product.setCount(product.getCount() +1L);
        product.setStatus(Status.ACTIVE);
        productService.save(product);

        if (cartProduct.getCount() == 1) {
            cartProductService.delete(cartProduct);
        } else {
            cartProduct.setCount(cartProduct.getCount() - 1L);
            cartProductService.save(cartProduct);
        }
    }

    public void increaseCartProduct(CartProduct cartProduct) {
        Product product = productService.findById(cartProduct.getProduct().getId());
        if(product.getCount() > 0){
            product.setCount(product.getCount() - 1L);
            if(product.getCount() == 0) product.setStatus(Status.DISABLED);
            productService.save(product);

            cartProduct.setCount(cartProduct.getCount() +1L);
            cartProductService.save(cartProduct);
        }
    }
}
