package com.employee.timesheet;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class KafkaConsumer {

    private final ObjectMapper objectMapper;

    public KafkaConsumer(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @KafkaListener(topics = "timesheet_topic", groupId = "test-group")
    public void consumeTimesheet(String message) {
        try {
            TimeSheetData timeSheetData = objectMapper.readValue(message, TimeSheetData.class);
            validateAndProcessTimeSheet(timeSheetData);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private void validateAndProcessTimeSheet(TimeSheetData timeSheetData) {
        Map<String, List<String>> processingStatusMap = new HashMap<>();

        for (TimeSheetData.TimeSheetEntry entry : timeSheetData.getTimeSheetEntries()) {
            LocalDate date = entry.getDate();
            int hoursWorked = entry.getHoursWorked();
            String employeeName = timeSheetData.getEmployeeName();

            if (date.getDayOfWeek() != DayOfWeek.SATURDAY && date.getDayOfWeek() != DayOfWeek.SUNDAY) {
                if (hoursWorked == 8 || hoursWorked == 0) {
                    addStatus(processingStatusMap, employeeName, "processed");
                } else {
                    addStatus(processingStatusMap, employeeName, "rejected");
                }
            } else {
                if (hoursWorked == 0) {
                    addStatus(processingStatusMap, employeeName, "processed");
                } else {
                    addStatus(processingStatusMap, employeeName, "rejected");
                }
            }
        }

        processingStatusMap.forEach((employee, statusList) -> {
            System.out.println("Employee: " + employee + ", Status: " + statusList);
        });
    }

    private void addStatus(Map<String, List<String>> map, String key, String status) {
        map.computeIfAbsent(key, k -> new ArrayList<>()).add(status);
    }

}