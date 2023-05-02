package com.onebox.ecommerce.entrypoints.converter;

import com.onebox.ecommerce.entrypoints.request.ProductDTO;
import com.onebox.entities.Product;

public class DTOToDomainConverter {
    public Product convert(ProductDTO productDTO) {
        return Product.builder()
                .name(productDTO.getName())
                .description(productDTO.getDescription())
                .price(productDTO.getPrice())
                .stock(productDTO.getStock())
                .build();
    }
}
