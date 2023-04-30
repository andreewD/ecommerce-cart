package com.onebox.usecases.cart;

import com.onebox.entities.Cart;
import com.onebox.utils.UseCaseResult;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.List;

@Builder
@Data
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
@NoArgsConstructor
public class ListCartsUseCaseResult extends UseCaseResult {
    private List<Cart> carts;
}
