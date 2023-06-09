package com.onebox.ecommerce.entrypoints.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DeleteProductToCartDTO {
    private Integer productId;
    private Integer quantity;
}
