package com.employee.timesheet;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class KafkaPublisher {

    private final KafkaTemplate<String, Object> kafkaTemplate;

    public KafkaPublisher(KafkaTemplate<String, Object> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void publishTimeSheetData(TimeSheetData timeSheetData) {
        kafkaTemplate.send("timesheet_topic", timeSheetData.getEmployeeName(), timeSheetData);
    }
}




