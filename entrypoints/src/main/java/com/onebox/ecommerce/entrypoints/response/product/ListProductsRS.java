package com.onebox.ecommerce.entrypoints.response.product;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ListProductsRS{
    @Schema(example = "[{\"id\": 1, \"name\": \"Product name\", \"description\": \"Product description\", \"price\": 10.0, \"stock\": 100}, {\"id\": 2, \"name\": \"Product name\", \"description\": \"Product description\", \"price\": 10.0, \"stock\": 100}, {\"id\": 3, \"name\": \"Product name\", \"description\": \"Product description\", \"price\": 10.0, \"stock\": 100}, {\"id\": 4, \"name\": \"Product name\", \"description\": \"Product description\", \"price\": 10.0, \"stock\": 100}, {\"id\": 5, \"name\": \"Product name\", \"description\": \"Product description\", \"price\": 10.0, \"stock\": 100}, {\"id\": 6, \"name\": \"Product name\", \"description\": \"Product description\", \"price\": 10.0, \"stock\": 100}, {\"id\": 7, \"name\": \"Product name\", \"description\": \"Product description\", \"price\": 10.0, \"stock\": 100}, {\"id\": 8, \"name\": \"Product name\", \"description\": \"Product description\", \"price\": 10.0, \"stock\": 100}, {\"id\": 9, \"name\": \"Product name\", \"description\": \"Product description\", \"price\": 10.0, \"stock\": 100}]")
    private List<ProductRS> products;
}
