package ru.gb.gbthymeleaf.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.gb.gbthymeleaf.dto.ProductDto;
import ru.gb.gbthymeleaf.service.ProductService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/product")
public class ProductController {

    private final ProductService productService;

    @GetMapping()
    public List<ProductDto> getProductList() {
        return productService.findAll();
    }

//    @GetMapping
//    public String showForm(Model model, @RequestParam(name = "id", required = false) Long id) {
//        Product product;
//        if (id != null) {
//            product = productService.findById(id);
//        } else {
//            product = new Product();
//        }
//        model.addAttribute("product", product);
//        return "product-form";
//    }
//
//    @PostMapping
//    public String saveProduct(Product product) {
//        product.setManufactureDate(LocalDate.now());
//        productService.save(product);
//        return "redirect:/product/all";
//    }
//
//    @GetMapping("/delete")
//    public String deleteById(@RequestParam(name = "id") Long id) {
//        productService.deleteById(id);
//        return "redirect:/product/all";
//    }
}
