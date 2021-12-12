package ru.gb.gbthymeleaf.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.gb.gbthymeleaf.entity.CartProduct;

public interface CartProductDao extends JpaRepository<CartProduct, Long> {

}
