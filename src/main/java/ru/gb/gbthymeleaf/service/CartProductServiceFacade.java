package ru.gb.gbthymeleaf.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.gb.gbthymeleaf.dto.CartDto;
import ru.gb.gbthymeleaf.dto.CartProductDto;
import ru.gb.gbthymeleaf.dto.ProductDto;
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

    public CartDto findCartById(Long cartId) {
        return new CartDto(cartService.findById(cartId));
    }

    public ProductDto findProductById(Long productId) {
        return new ProductDto(productService.findById(productId));
    }

    public CartDto saveAndUpdate(Long productId, Long cartId) {
        Cart cart = cartService.findById(cartId);
        Product product = productService.findById(productId);

        CartProduct cartProduct = cart.getCartProducts().stream().filter(cp -> cp.getProduct().getId().equals(product.getId())).findFirst().orElse(null);

        if (cartProduct == null) {
            cart.getCartProducts().add(CartProduct.create(product, cart));
        } else {
            cartProduct.setCount(cartProduct.getCount() + 1L);
        }

        product.setCount(product.getCount() - 1L);
        if(product.getCount() == 0) product.setStatus(Status.DISABLED);

        productService.save(product);
        return new CartDto(cartService.save(cart));
    }


    public void decreaseCartProduct(CartProductDto cartProductDto) {
        Product product = productService.findById(cartProductDto.getProductId());
        product.setCount(product.getCount() +1L);
        product.setStatus(Status.ACTIVE);
        productService.save(product);
        CartProduct cartProduct = cartProductService.findById(cartProductDto.getId());
        if (cartProduct.getCount() == 1) {
            cartProductService.delete(cartProduct);
        } else {
            cartProduct.setCount(cartProduct.getCount() - 1L);
            cartProductService.save(cartProduct);
        }
    }

    public void increaseCartProduct(CartProductDto cartProductDto) {
        Product product = productService.findById(cartProductDto.getProductId());
        if(product.getCount() > 0){
            product.setCount(product.getCount() - 1L);
            if(product.getCount() == 0) product.setStatus(Status.DISABLED);
            productService.save(product);

            CartProduct cartProduct = cartProductService.findById(cartProductDto.getId());
            cartProduct.setCount(cartProduct.getCount() +1L);
            cartProductService.save(cartProduct);
        }
    }
}
