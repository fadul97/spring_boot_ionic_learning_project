package com.leonardofadul.springboot.ionic.learning.project;

import com.leonardofadul.springboot.ionic.learning.project.domain.*;
import com.leonardofadul.springboot.ionic.learning.project.domain.enums.ClientType;
import com.leonardofadul.springboot.ionic.learning.project.domain.enums.PaymentState;
import com.leonardofadul.springboot.ionic.learning.project.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.text.SimpleDateFormat;
import java.util.Arrays;

@SpringBootApplication
public class SpringBootIonicLearningProjectApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootIonicLearningProjectApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
	}
}
