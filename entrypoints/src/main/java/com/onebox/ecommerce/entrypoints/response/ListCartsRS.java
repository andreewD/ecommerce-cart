package com.onebox.ecommerce.entrypoints.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ListCartsRS extends BaseRS{
    private List<CartRS> carts;
}
