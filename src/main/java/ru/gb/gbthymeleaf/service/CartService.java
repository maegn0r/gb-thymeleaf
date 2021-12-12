package ru.gb.gbthymeleaf.service;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.gb.gbthymeleaf.dao.CartDao;
import ru.gb.gbthymeleaf.entity.Cart;

import java.util.ArrayList;


@Service
@RequiredArgsConstructor
@Transactional
public class CartService {
    private final CartDao cartDao;

    public long count(){
        return cartDao.count();
    }

    public Cart save(Cart cart) {
        return cartDao.save(cart);
    }

    @Transactional(readOnly = true)
    public Cart findById (Long id){
        return cartDao.findById(id).get();
    }

    public Cart createEmptyCart() {
        Cart cart = new Cart();
        cart.setCartProducts(new ArrayList<>());
        return cart;
    }
}
