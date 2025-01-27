package com.springcrud.crudoperation.service.serviceimpl;

import com.springcrud.crudoperation.dto.EmailDto.EmailDto;
import com.springcrud.crudoperation.repository.EmailRepository;
import com.springcrud.crudoperation.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailServiceImpl implements EmailService {
    @Autowired
    private JavaMailSender javaMailSender;
    @Autowired
    private EmailRepository emailRepository;


    @Override
    public String sendEmail(EmailDto emailDto) {


        return "Mail sent Successfully";
    }
}
