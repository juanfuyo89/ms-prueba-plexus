package com.plexus.prueba.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
public class GenericResponse {

    private String status;
    private String message;

}
