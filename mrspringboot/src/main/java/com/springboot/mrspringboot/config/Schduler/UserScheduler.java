package com.springboot.mrspringboot.config.Schduler;

import com.springboot.mrspringboot.DataStore.Pojo;
import com.springboot.mrspringboot.DataStore.User;
import com.springboot.mrspringboot.service.GetSentimenatal;
import com.springboot.mrspringboot.serviceerepo.Emailservice;
import com.springboot.mrspringboot.serviceerepo.Querclass;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class UserScheduler {

    @Autowired
    private Emailservice emailService;

    @Autowired
    private Querclass queryClass;

    @Autowired
    private GetSentimenatal getSentimental;

    // Scheduler runs every day at 9 AM
    @Scheduled(cron = "0 0 9 * * 0")
    public void runUserScheduler() {
        List<User> users = queryClass.getuser();

        for (User user : users) {
            List<Pojo> filteredPojos = user.getGetall().stream()
                    .filter(x -> x.getDate().isAfter(LocalDateTime.now().minus(7, ChronoUnit.DAYS)))
                    .collect(Collectors.toList());

            // Convert list of Pojo data to string (assuming toString gives relevant content)
            String entry = filteredPojos.stream()
                    .map(Pojo::toString)
                    .collect(Collectors.joining(" "));

            String sentiment = String.valueOf(getSentimental.getsentinemtnal(entry));
            emailService.send(user.getEmail(), "Weekly Sentiment Analysis", sentiment);
        }

        System.out.println("User Scheduler executed successfully at: " + LocalDateTime.now());
    }
}
