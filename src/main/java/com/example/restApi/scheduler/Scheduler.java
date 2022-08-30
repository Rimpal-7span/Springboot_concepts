package com.example.restApi.scheduler;

import com.example.restApi.model.User;
import com.example.restApi.repository.Userdao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Optional;

import static java.time.LocalTime.now;

@Component
public class Scheduler {
    @Autowired
    Userdao userdao;

    @Scheduled(cron  = "0 0/20 * * * ?")
    public void updateUser()
    {
        Optional<User> user = userdao.findById(1L);
        if(user.isPresent())
        {
            User getUser = user.get();
            System.out.println("id is==>"+getUser.getId());
            getUser.setUpdatedDate(LocalDateTime.now());
            userdao.save(getUser);
            System.out.println("Updated date and time is==>"+getUser.getUpdatedDate());
        }


    }

}
