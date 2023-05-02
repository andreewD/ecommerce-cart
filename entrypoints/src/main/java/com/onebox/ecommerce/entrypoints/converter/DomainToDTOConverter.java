package com.onebox.ecommerce.entrypoints.converter;

import com.onebox.ecommerce.entrypoints.response.CartItemRS;
import com.onebox.ecommerce.entrypoints.response.CartRS;
import com.onebox.ecommerce.entrypoints.response.ListCartsRS;
import com.onebox.ecommerce.entrypoints.response.product.ListProductsRS;
import com.onebox.ecommerce.entrypoints.response.product.ProductRS;
import com.onebox.entities.Cart;
import com.onebox.entities.Product;
import com.onebox.usecases.cart.AddProductToCartUseCaseResult;
import com.onebox.usecases.cart.ListCartsUseCaseResult;
import com.onebox.usecases.product.AddProductUseCaseResult;
import com.onebox.usecases.product.ListProductsUseCaseResult;

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
                .createdAt(cart.getCreatedAt())
                .updatedAt(cart.getUpdatedAt())
                .build();
    }
    public CartRS convert(AddProductToCartUseCaseResult result) {
        return convert(result.getCart());
    }

    public ListCartsRS convert(ListCartsUseCaseResult result){
        List<CartRS> carts = new ArrayList<>();
        for (Cart cart : result.getCarts()) {
            carts.add(convert(cart));
        }

        ListCartsRS listCartsRS = new ListCartsRS();
        listCartsRS.setCarts(carts);
        return listCartsRS;
    }

    public ProductRS convert(Product product){
        ProductRS productRS = new ProductRS();
        productRS.setId(product.getProduct_seq());
        productRS.setName(product.getName());
        productRS.setDescription(product.getDescription());
        productRS.setPrice(product.getPrice());
        productRS.setStock(product.getStock());
        return productRS;
    }
    public ProductRS convert(AddProductUseCaseResult result){
        return convert(result.getProduct());
    }

    public ListProductsRS convert(ListProductsUseCaseResult result){
        List<ProductRS> products = new ArrayList<>();
        for (Product product : result.getProducts()) {
            products.add(convert(product));
        }

        ListProductsRS listProductsRS = new ListProductsRS();
        listProductsRS.setProducts(products);
        return listProductsRS;
    }

}
