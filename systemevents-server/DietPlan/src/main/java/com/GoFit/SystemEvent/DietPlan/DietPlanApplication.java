package com.GoFit.SystemEvent.DietPlan;

import com.GoFit.SystemEvent.DietPlan.Service.EventService;
import com.GoFit.SystemEvent.EventResponse;
import com.GoFit.SystemEvent.EventServiceGrpc;
import io.grpc.Server;
import io.grpc.ServerBuilder;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

import java.io.IOException;

@SpringBootApplication
public class DietPlanApplication {

	public static void main(String[] args) {
		SpringApplication.run(DietPlanApplication.class, args);


	}

}
