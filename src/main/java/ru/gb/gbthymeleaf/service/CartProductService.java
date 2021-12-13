package ru.gb.gbthymeleaf.service;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.gb.gbthymeleaf.dao.CartProductDao;
import ru.gb.gbthymeleaf.entity.CartProduct;

@Service
@RequiredArgsConstructor
@Transactional
public class CartProductService {
    private final CartProductDao cartProductDao;


    public CartProduct save(CartProduct cartProduct) {
        return cartProductDao.save(cartProduct);
    }
    public void delete(CartProduct cartProduct) {
        cartProductDao.customDelete(cartProduct.getId());
    }
}
