package com.plexus.prueba.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductPriceResponse {

    private Integer productId;
    private Integer brandId;
    private Integer priceList;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private Double price;

}
