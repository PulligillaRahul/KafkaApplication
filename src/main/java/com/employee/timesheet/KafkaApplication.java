package com.employee.timesheet;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.io.InputStream;

@SpringBootApplication
public class KafkaApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(KafkaApplication.class, args);

        KafkaPublisher kafkaPublisher = context.getBean(KafkaPublisher.class);

        try {
            ObjectMapper objectMapper = context.getBean(ObjectMapper.class);

            InputStream inputStream = KafkaApplication.class.getResourceAsStream("/timesheet.json");
            if (inputStream != null) {
                TimeSheetData timeSheetData = objectMapper.readValue(inputStream, TimeSheetData.class);
                kafkaPublisher.publishTimeSheetData(timeSheetData);
            } else {
                System.out.println("timesheet.json not found.");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        context.close();
    }
}
