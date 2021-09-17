package com.ooy.hotels.booking;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
@EnableEurekaClient
public class HotelBookingServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(HotelBookingServiceApplication.class, args);
	}

}
