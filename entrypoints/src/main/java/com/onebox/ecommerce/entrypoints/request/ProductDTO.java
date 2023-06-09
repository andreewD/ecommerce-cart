package com.onebox.ecommerce.entrypoints.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductDTO {
    @Schema(example = "Product name")
    private String name;
    @Schema(example = "Product description")
    private String description;
    @Schema(example = "10.0")
    private Double price;
    @Schema(example = "100")
    private Integer stock;
}
