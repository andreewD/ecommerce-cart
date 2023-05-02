package com.onebox.ecommerce.entrypoints;

import com.onebox.ecommerce.entrypoints.converter.DTOToDomainConverter;
import com.onebox.ecommerce.entrypoints.converter.DomainToDTOConverter;
import com.onebox.ecommerce.entrypoints.request.ProductDTO;
import com.onebox.ecommerce.entrypoints.response.product.ListProductsRS;
import com.onebox.ecommerce.entrypoints.response.product.ProductRS;
import com.onebox.usecases.product.AddProductUseCaseImpl;
import com.onebox.usecases.product.AddProductUseCaseParams;
import com.onebox.usecases.product.AddProductUseCaseResult;
import com.onebox.usecases.product.ListProductsUseCaseImpl;
import com.onebox.usecases.product.ListProductsUseCaseResult;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.onebox.ecommerce.entrypoints.ProductApi.BASE;

@RestController
@AllArgsConstructor
@RequestMapping(BASE)
public class ProductEntrypoint {
    private final ListProductsUseCaseImpl listProductsUseCase;
    private final AddProductUseCaseImpl addProductUseCase;
    private final DTOToDomainConverter dtoToDomainConverter;
    private final DomainToDTOConverter domainToDTOConverter;

    @Operation(summary = "List products", description = "List products")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",content = @Content(
                    schema = @Schema(implementation = ListProductsRS.class)
            ))
    })
    @GetMapping
    public ResponseEntity<?> listAllProducts() {
        ListProductsUseCaseResult result = listProductsUseCase.execute();

        return result.wasSuccessful()? new ResponseEntity<>(domainToDTOConverter.convert(result), HttpStatus.OK):
                new ResponseEntity<>(result, HttpStatus.CONFLICT);

    }

    @Operation(summary = "Create product", description = "Create product")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",content = @Content(
                    schema = @Schema(implementation = ProductRS.class)
            ))
    })
    @PostMapping
    public ResponseEntity<?> createProduct(@RequestBody ProductDTO productDTO) {

        AddProductUseCaseParams params = AddProductUseCaseParams.builder()
                .product(dtoToDomainConverter.convert(productDTO))
                .build();

        AddProductUseCaseResult result = addProductUseCase.execute(params);

        return result.wasSuccessful()? new ResponseEntity<>(domainToDTOConverter.convert(result), HttpStatus.OK):
                new ResponseEntity<>(result, HttpStatus.CONFLICT);

    }
}
