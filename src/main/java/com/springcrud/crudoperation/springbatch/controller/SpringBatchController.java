package com.springcrud.crudoperation.springbatch.controller;

import com.springcrud.crudoperation.springbatch.config.SpringBatchConfig;
import com.springcrud.crudoperation.springbatch.itemwriter.UserWriter;
import com.springcrud.crudoperation.model.User;
import com.springcrud.crudoperation.repository.UserRepository;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecutionException;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.item.Chunk;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

@RestController
public class SpringBatchController {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserWriter userWriter;

    private final JobLauncher jobLauncher;
    private final Job processJob;

    @Autowired
    public SpringBatchController(JobLauncher jobLauncher, SpringBatchConfig springBatchConfig) {
        this.jobLauncher = jobLauncher;
        this.processJob = springBatchConfig.exportUserJob();
    }
    @GetMapping("/export")
    public String exportTickets() {
        try {

            jobLauncher.run(processJob, new org.springframework.batch.core.JobParameters());
            List<User> tickets = userRepository.findAll();
            Chunk<User> userChunk = new Chunk<>();
            userChunk.addAll(tickets);
            userWriter.write(userChunk);
            return "Job started and export completed successfully!";
        } catch (JobExecutionException | IOException e) {
            e.printStackTrace();
            return "Failed to start job or export tickets: " + e.getMessage();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
