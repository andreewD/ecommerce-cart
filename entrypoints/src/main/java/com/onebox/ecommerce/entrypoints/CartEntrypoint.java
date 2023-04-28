package com.onebox.ecommerce.entrypoints;

import com.onebox.usecases.cart.GetCartByIdUseCaseImpl;
import com.onebox.usecases.cart.GetCartByIdUseCaseParams;
import com.onebox.usecases.cart.GetCartByIdUseCaseResult;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.UUID;

import static com.onebox.ecommerce.entrypoints.CartApi.BASE;

@RestController
@AllArgsConstructor
@RequestMapping(BASE)
public class CartEntrypoint {
    private final GetCartByIdUseCaseImpl getCartByIdUseCase;

    @Operation(summary = "Get cart by id", description = "Get cart by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200")
    })
    @GetMapping("/")
    public ResponseEntity<?> getCartById(@PathVariable UUID cartId) {
        GetCartByIdUseCaseParams params = GetCartByIdUseCaseParams.builder()
                .cartId(cartId)
                .build();

        GetCartByIdUseCaseResult result = getCartByIdUseCase.execute(params);

        return result.wasSuccessful()? new ResponseEntity<>(result, HttpStatus.OK) :
                new ResponseEntity<>(result, HttpStatus.CONFLICT);
    }
}
