package com.example;

import com.example.model.Role;
import com.example.service.UserService;
import com.example.vo.Registration;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;

@SpringBootApplication
public class FlyspaceReservationApplication implements CommandLineRunner {

    @Autowired
    UserService userService;

    public static void main(String[] args) {
        SpringApplication.run(FlyspaceReservationApplication.class, args);

    }

    @Override
    public void run(String... args) throws Exception {

        Registration admin = new Registration();
        admin.setPassword("admin");
        admin.setEmail("admin@example.com");
        admin.setRoles(new ArrayList<Role>(Arrays.asList(Role.ROLE_ADMIN)));

        userService.register(admin);

        Registration user = new Registration();
        user.setPassword("user");
        user.setEmail("user@example.com");
        user.setRoles(new ArrayList<Role>(Arrays.asList(Role.ROLE_USER, Role.ROLE_ADMIN)));

        userService.register(user);
    }

    @Bean
    public ModelMapper modelMapper() {

        return new ModelMapper();
    }

}
