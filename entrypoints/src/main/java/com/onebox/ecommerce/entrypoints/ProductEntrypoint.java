package com.onebox.ecommerce.entrypoints;

import com.onebox.usecases.product.ListProductsUseCaseImpl;
import com.onebox.usecases.product.ListProductsUseCaseResult;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.onebox.ecommerce.entrypoints.ProductApi.BASE;

@RestController
@AllArgsConstructor
@RequestMapping(BASE)
public class ProductEntrypoint {
    private final ListProductsUseCaseImpl listProductsUseCaseImpl;
    @GetMapping("/")
    public ResponseEntity<?> listAllProducts() {
        ListProductsUseCaseResult result = listProductsUseCaseImpl.execute();

        return result.wasSuccessful()? new ResponseEntity<>(result, HttpStatus.OK):
                new ResponseEntity<>(result, HttpStatus.CONFLICT);

    }
}