package com.onebox.ecommerce.entrypoints;

import com.onebox.ecommerce.entrypoints.converter.DomainToDTOConverter;
import com.onebox.ecommerce.entrypoints.request.AddProductToCartDTO;
import com.onebox.ecommerce.entrypoints.response.ListCartsRS;
import com.onebox.usecases.cart.AddCartUseCaseImpl;
import com.onebox.usecases.cart.AddCartUseCaseResult;
import com.onebox.usecases.cart.AddProductToCartUseCaseImpl;
import com.onebox.usecases.cart.AddProductToCartUseCaseParams;
import com.onebox.usecases.cart.AddProductToCartUseCaseResult;
import com.onebox.usecases.cart.DeleteCartUseCaseImpl;
import com.onebox.usecases.cart.DeleteCartUseCaseParams;
import com.onebox.usecases.cart.DeleteCartUseCaseResult;
import com.onebox.usecases.cart.GetCartByIdUseCaseImpl;
import com.onebox.usecases.cart.GetCartByIdUseCaseParams;
import com.onebox.usecases.cart.GetCartByIdUseCaseResult;
import com.onebox.usecases.cart.ListCartsUseCaseImpl;
import com.onebox.usecases.cart.ListCartsUseCaseResult;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.transaction.HeuristicMixedException;
import jakarta.transaction.HeuristicRollbackException;
import jakarta.transaction.NotSupportedException;
import jakarta.transaction.RollbackException;
import jakarta.transaction.SystemException;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.UUID;

import static com.onebox.ecommerce.entrypoints.CartApi.BASE;

@RestController
@AllArgsConstructor
@RequestMapping(BASE)
public class CartEntrypoint {
    private final GetCartByIdUseCaseImpl getCartByIdUseCase;
    private final AddCartUseCaseImpl addCartUseCase;
    private final ListCartsUseCaseImpl listCartsUseCase;
    private final DeleteCartUseCaseImpl deleteCartUseCase;
    private final AddProductToCartUseCaseImpl addProductToCartUseCase;
    private final DomainToDTOConverter domainToDTOConverter;

    @Operation(summary = "List carts", description = "List carts")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",content = @Content(
                    schema = @Schema(implementation = ListCartsRS.class)))
    })
    @GetMapping("/")
    public ResponseEntity<?> listCarts() {
        ListCartsUseCaseResult result = listCartsUseCase.execute();
        return result.wasSuccessful()? new ResponseEntity<>(domainToDTOConverter.convert(result), HttpStatus.OK) :
                new ResponseEntity<>(result, HttpStatus.CONFLICT);
    }

    @Operation(summary = "Get cart by id", description = "Get cart by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200")
    })
    @GetMapping("/{id}")
    public ResponseEntity<?> getCartById(@PathVariable UUID id) {
        GetCartByIdUseCaseParams params = GetCartByIdUseCaseParams.builder()
                .cartId(id)
                .build();

        GetCartByIdUseCaseResult result = getCartByIdUseCase.execute(params);

        return result.wasSuccessful()? new ResponseEntity<>(result, HttpStatus.OK) :
                new ResponseEntity<>(result, HttpStatus.CONFLICT);
    }

    @Operation(summary = "Add cart", description = "Add cart")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200")
    })
    @PostMapping("/")
    public ResponseEntity<?> addCart() throws HeuristicRollbackException, SystemException, HeuristicMixedException, NotSupportedException, RollbackException {
        AddCartUseCaseResult result =addCartUseCase.execute();
        return result.wasSuccessful()? new ResponseEntity<>(result, HttpStatus.OK) :
                new ResponseEntity<>(result, HttpStatus.CONFLICT);
    }

    @Operation(summary = "Delete cart", description = "Delete cart")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCart(@PathVariable UUID id) {
        DeleteCartUseCaseParams params = DeleteCartUseCaseParams.builder()
                .cartId(id)
                .build();

        DeleteCartUseCaseResult result = deleteCartUseCase.execute(params);

        return result.wasSuccessful()? new ResponseEntity<>(result, HttpStatus.OK) :
                new ResponseEntity<>(result, HttpStatus.CONFLICT);

    }

    @PostMapping("/{id}/products")
    public ResponseEntity<?> addProductToCart(@PathVariable UUID id, @RequestBody @Valid AddProductToCartDTO addProductToCartDTO) {
        AddProductToCartUseCaseParams params = AddProductToCartUseCaseParams.builder()
                .cartId(id)
                .productId(addProductToCartDTO.getProductId())
                .quantity(addProductToCartDTO.getQuantity())
                .build();

        AddProductToCartUseCaseResult result = addProductToCartUseCase.execute(params);

        return result.wasSuccessful()? new ResponseEntity<>(domainToDTOConverter.convert(result), HttpStatus.OK) :
                new ResponseEntity<>(result, HttpStatus.CONFLICT);
    }
}
