package com.between.prueba.repository;

import com.between.prueba.repository.entity.Price;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface PriceRepository extends JpaRepository<Price, Integer> {

    @Query("SELECT p FROM Price p WHERE p.productId = :productId AND p.brandId = :brandId " +
            "AND :applicationDate BETWEEN p.startDate AND p.endDate")
    Optional<List<Price>> findByDateAndProductAndBrand(@Param("applicationDate") LocalDateTime applicationDate,
                                                       @Param("productId") Integer productId,
                                                       @Param("brandId") Integer brandId);

}
