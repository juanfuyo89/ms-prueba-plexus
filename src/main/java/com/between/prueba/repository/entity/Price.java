package com.between.prueba.repository.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
@Table(name="PRICES")
@NamedQuery(name="Price.findAll", query="SELECT p FROM Price p")
public class Price {

    @Id
    @Column(name="PRICE_ID")
    private Integer priceId;

    @Column(name="BRAND_ID")
    private Integer brandId;

    @Column(name="START_DATE")
    private LocalDateTime startDate;

    @Column(name="END_DATE")
    private LocalDateTime endDate;

    @Column(name="PRICE_LIST")
    private Integer priceList;

    @Column(name="PRODUCT_ID")
    private Integer productId;

    private Integer priority;

    private Double price;

    private String curr;

}
