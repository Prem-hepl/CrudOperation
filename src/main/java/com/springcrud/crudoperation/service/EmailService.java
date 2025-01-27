package com.springcrud.crudoperation.service;

import com.springcrud.crudoperation.dto.EmailDto.EmailDto;

public interface EmailService {
    String sendEmail(EmailDto emailDto);
}
