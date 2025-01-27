package com.springcrud.crudoperation.model.email;

import lombok.Data;

@Data
public class Email {
    private String recipient;
    private String subject;
    private String body;
}
