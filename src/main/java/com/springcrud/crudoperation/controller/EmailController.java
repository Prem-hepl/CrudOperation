package com.springcrud.crudoperation.controller;

import com.springcrud.crudoperation.dto.EmailDto.EmailDto;
import com.springcrud.crudoperation.model.email.Email;
import com.springcrud.crudoperation.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EmailController {
    @Autowired
    private EmailService emailService;

    @PostMapping("/email")
    public String sendEmail(@RequestBody EmailDto emailDto){
        return emailService.sendEmail(emailDto);
    }
}
