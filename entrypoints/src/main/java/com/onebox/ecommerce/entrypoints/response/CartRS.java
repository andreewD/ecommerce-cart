package com.onebox.ecommerce.entrypoints.response;

import com.onebox.utils.StatusCode;
import com.onebox.utils.UseCaseResult;
import jdk.jshell.Snippet;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CartRS extends BaseRS{
    private UUID id;
    private List<CartItemRS> products;
    private Double total;
    private Long validUntil;
    private StatusCode statusCode;
}
