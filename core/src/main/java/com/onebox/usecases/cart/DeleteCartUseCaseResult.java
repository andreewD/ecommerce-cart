package com.onebox.usecases.cart;

import com.onebox.utils.UseCaseResult;
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
public class DeleteCartUseCaseResult extends UseCaseResult {
    private UUID cartId;
}
