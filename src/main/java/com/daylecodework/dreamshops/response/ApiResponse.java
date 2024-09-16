package com.daylecodework.dreamshops.response;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@AllArgsConstructor
@Data
public class ApiResponse {
    private String message;
    private Object data;
}
