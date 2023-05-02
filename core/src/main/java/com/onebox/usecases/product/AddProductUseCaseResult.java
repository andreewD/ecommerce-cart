package com.onebox.usecases.product;

import com.onebox.entities.Product;
import com.onebox.utils.UseCaseResult;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Builder
@Data
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
@NoArgsConstructor
public class AddProductUseCaseResult extends UseCaseResult {
    private Product product;
}
