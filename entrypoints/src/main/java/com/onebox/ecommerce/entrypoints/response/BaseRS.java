package com.onebox.ecommerce.entrypoints.response;

import com.onebox.utils.StatusCode;
import lombok.Data;

@Data
public class BaseRS {
    public StatusCode statusCode;
}
