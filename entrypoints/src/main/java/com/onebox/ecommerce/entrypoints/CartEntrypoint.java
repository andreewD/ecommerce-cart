package com.onebox.ecommerce.entrypoints;


import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import static com.onebox.ecommerce.entrypoints.CartApi.BASE;

@RestController
@AllArgsConstructor
@RequestMapping(BASE)
public class CartEntrypoint {
    @GetMapping("/")
    public String index() {
        return "Greetings from Spring Boot!";
    }
}
