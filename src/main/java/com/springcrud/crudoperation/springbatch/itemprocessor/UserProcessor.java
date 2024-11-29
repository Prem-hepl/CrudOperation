package com.springcrud.crudoperation.springbatch.itemprocessor;


import com.springcrud.crudoperation.model.User;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

@Component
public class UserProcessor implements ItemProcessor<User,User> {
    @Override
    public User process(User user) throws Exception {
        return user;
    }
}