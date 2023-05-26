package com.GoFit.DietPlan;

import com.GoFit.SystemEvent.EventRequest;
import com.GoFit.SystemEvent.EventResponse;
import com.GoFit.SystemEvent.EventServiceGrpc;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.Server;
import io.grpc.netty.NettyServerBuilder;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;


@EnableEurekaClient
@SpringBootApplication
@EnableFeignClients
@EnableSwagger2
@EnableDiscoveryClient
public class DietPlanApplication {
@Value("${spring.rabbitmq.host}")
String host;

	@Value("${spring.rabbitmq.username}")
	String username;

	@Value("${spring.rabbitmq.password}")
	String password;

	public DietPlanApplication() throws IOException {
	}

	public static void main(String[] args) {
		SpringApplication.run(DietPlanApplication.class, args);


	}

	@Bean
	CachingConnectionFactory connectionFactory1() {
		CachingConnectionFactory cachingConnectionFactory = new CachingConnectionFactory(host);
		cachingConnectionFactory.setUsername(username);
		cachingConnectionFactory.setPassword(password);
		return cachingConnectionFactory;
	}
	@Bean
	public MessageConverter jsonMessageConverter1() {
		return new Jackson2JsonMessageConverter();
	}
	@Bean
	public RabbitTemplate rabbitTemplate1(ConnectionFactory connectionFactory1) {
		final RabbitTemplate rabbitTemplate1 = new RabbitTemplate(connectionFactory1);
		rabbitTemplate1.setMessageConverter(jsonMessageConverter1());
		return rabbitTemplate1;
	}




}
