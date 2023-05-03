package com.onebox.usecases.cart;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Builder
@Data
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
@NoArgsConstructor
public class DeleteProductFromCartUseCaseParams {
    private UUID cartId;
    private Integer productId;
    private Integer quantity;
}
