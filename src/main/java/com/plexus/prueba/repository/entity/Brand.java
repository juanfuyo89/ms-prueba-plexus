package com.plexus.prueba.repository.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
@Table(name="BRANDS")
@NamedQuery(name="Brand.findAll", query="SELECT b FROM Brand b")
public class Brand {

    @Id
    @Column(name="BRAND_ID")
    private Integer brandId;

    private String name;

}
