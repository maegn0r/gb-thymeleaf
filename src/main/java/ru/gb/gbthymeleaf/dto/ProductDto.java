package ru.gb.gbthymeleaf.dto;

import lombok.*;
import ru.gb.gbthymeleaf.entity.Product;
import ru.gb.gbthymeleaf.entity.enums.Status;

import java.math.BigDecimal;
import java.time.LocalDate;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductDto {

    private Long id;
    private String title;
    private BigDecimal cost;
    private LocalDate manufactureDate;
    private Long count;
    private Status status;

    public ProductDto (Product product){
        this.id = product.getId();
        this.title = product.getTitle();
        this.cost = product.getCost();
        this.manufactureDate = product.getManufactureDate();
        this.count = product.getCount();
        this.status = product.getStatus();
    }
}
