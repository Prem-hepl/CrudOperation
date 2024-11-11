package com.springcrud.crudoperation.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class SuccessResponse<T> {
    private Integer statusCode = 200;
    private String statusMesssage = "Success";
    private T data;
}
