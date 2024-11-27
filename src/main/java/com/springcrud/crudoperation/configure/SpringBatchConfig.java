package com.springcrud.crudoperation.configure;

import com.springcrud.crudoperation.itemprocessor.UserProcessor;
import com.springcrud.crudoperation.itemreader.UserReader;
import com.springcrud.crudoperation.itemwriter.UserWriter;
import com.springcrud.crudoperation.model.User;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoTemplate;

import java.util.ArrayList;


@Configuration
@EnableBatchProcessing
public class SpringBatchConfig {

    @Autowired
    private MongoTemplate mongoTemplate;
    @Autowired
    private JobBuilderFactory jobBuilderFactory;
    @Autowired
    private StepBuilderFactory stepBuilderFactory;
    @Autowired
    private UserWriter userWriter;

    public SpringBatchConfig(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }
    @Bean
    public Job exportUserJob() {
        return jobBuilderFactory.get("exportTicketsJob")
                .start(exportUserStep())
                .build();
    }
    @Bean
    public Step exportUserStep() {
        return stepBuilderFactory.get("exportTicketsStep")
                .<User, User>chunk(10)
                .reader(userReader())
                .processor(userProcessor())
                .writer(userItemWriter())
                .build();
    }

    @Bean
    public ItemReader<User> userReader() {
        return new UserReader(new ArrayList<>());
    }
    @Bean
    public ItemProcessor<User, User> userProcessor() {
        return new UserProcessor();
    }
    @Bean
    public ItemWriter<User> userItemWriter() {
        return userWriter;
    }

}
