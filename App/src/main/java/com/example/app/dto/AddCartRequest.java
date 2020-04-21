package com.example.app.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
@AllArgsConstructor
public class AddCartRequest {
    @NotBlank(message = "product_id不能为空")
    private Integer product_id;
}
