package com.onebox.ecommerce.entrypoints.converter;

import com.onebox.ecommerce.entrypoints.response.CartItemRS;
import com.onebox.ecommerce.entrypoints.response.CartRS;
import com.onebox.ecommerce.entrypoints.response.ListCartsRS;
import com.onebox.entities.Cart;
import com.onebox.usecases.cart.AddProductToCartUseCaseResult;
import com.onebox.usecases.cart.ListCartsUseCaseResult;

import java.util.ArrayList;
import java.util.List;

public class DomainToDTOConverter {
    public List<CartItemRS> convert(String cartProducts) {
        List<CartItemRS> products = new ArrayList<>();
        if (cartProducts != null && !cartProducts.isEmpty()) {
            String[] productsData = cartProducts.split(",");
            for (String productData : productsData) {
                String[] productDataArray = productData.split(":");
                CartItemRS item = new CartItemRS();
                item.setProductId(Integer.parseInt(productDataArray[0]));
                item.setQuantity(Integer.parseInt(productDataArray[1]));
                item.setPrice(Double.parseDouble(productDataArray[2]));
                products.add(item);
            }
        }
        return products;
    }
    public CartRS convert(Cart cart) {
        return CartRS.builder()
                .id(cart.getId())
                .products(convert(cart.getProducts()))
                .total(cart.getTotal())
                .validUntil(cart.getValidUntil())
                .build();
    }
    public CartRS convert(AddProductToCartUseCaseResult result) {
        CartRS cartRS = convert(result.getCart());
        cartRS.setStatusCode(result.getStatusCode());
        return cartRS;
    }

    public ListCartsRS convert(ListCartsUseCaseResult result){
        List<CartRS> carts = new ArrayList<>();
        for (Cart cart : result.getCarts()) {
            carts.add(convert(cart));
        }

        ListCartsRS listCartsRS = new ListCartsRS();
        listCartsRS.setCarts(carts);
        listCartsRS.setStatusCode(result.getStatusCode());
        return listCartsRS;
    }

}
