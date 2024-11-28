package com.springcrud.crudoperation.springbatch.itemreader;

import com.springcrud.crudoperation.model.User;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;
import org.springframework.stereotype.Component;

import java.util.Iterator;
import java.util.List;

@Component
public class UserReader implements ItemReader<User> {

    private final Iterator<User> userIterator;

    public UserReader(List<User> userIterator) {
        this.userIterator = userIterator.iterator();
    }

    @Override
    public User read() throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException {
        if (userIterator.hasNext()){
            return userIterator.next();
        }else {
            return null;
        }
    }
}
