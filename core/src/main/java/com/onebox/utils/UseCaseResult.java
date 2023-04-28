package com.onebox.utils;

import lombok.Data;

@Data
public class UseCaseResult {
    private StatusCode statusCode;
    public boolean wasSuccessful() {
        return StatusCode.SUCCESS.equals(statusCode);
    }
}
