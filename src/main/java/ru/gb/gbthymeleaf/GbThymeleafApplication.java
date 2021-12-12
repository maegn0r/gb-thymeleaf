package ru.gb.gbthymeleaf;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import ru.gb.gbthymeleaf.entity.Product;
import ru.gb.gbthymeleaf.entity.enums.Status;
import ru.gb.gbthymeleaf.service.ProductService;

import java.math.BigDecimal;
import java.time.LocalDate;

@SpringBootApplication
public class GbThymeleafApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(GbThymeleafApplication.class, args);

//        ProductService productService = context.getBean(ProductService.class);
//
//
//        Product молоко = Product.builder()
//                .title("Молоко")
//                .cost(new BigDecimal(100))
//                .manufactureDate(LocalDate.now())
//                .status(Status.ACTIVE)
//                .build();
//
//        Product savedProduct = productService.save(молоко);
//
//        savedProduct.setTitle("Батон");
//
//        productService.save(savedProduct);
//
//        System.out.println(productService.findAll());
//
//        System.out.println(productService.findById(1L));
//
//        productService.deleteById(1L);

//        System.out.println(productService.findById(savedProduct.getId()));


    }

}
