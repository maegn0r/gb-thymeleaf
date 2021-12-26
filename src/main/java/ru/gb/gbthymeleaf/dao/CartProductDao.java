package ru.gb.gbthymeleaf.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;
import ru.gb.gbthymeleaf.entity.CartProduct;

public interface CartProductDao extends JpaRepository<CartProduct, Long> {

    @Transactional
    @Modifying
    @Query("delete from CartProduct cp where cp.id = ?1")
    void customDelete(Long id);
}
