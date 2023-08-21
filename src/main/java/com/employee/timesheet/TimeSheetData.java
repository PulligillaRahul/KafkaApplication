package com.employee.timesheet;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class TimeSheetData {
    private String employeeName;
    private List<TimeSheetEntry> timeSheetEntries = new ArrayList<>();

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public List<TimeSheetEntry> getTimeSheetEntries() {
        return timeSheetEntries;
    }

    public void setTimeSheetEntries(List<TimeSheetEntry> timeSheetEntries) {
        this.timeSheetEntries = timeSheetEntries;
    }

    public static class TimeSheetEntry {
        private LocalDate date;
        private int hoursWorked;

        public LocalDate getDate() {
            return date;
        }

        public void setDate(LocalDate date) {
            this.date = date;
        }

        public int getHoursWorked() {
            return hoursWorked;
        }

        public void setHoursWorked(int hoursWorked) {
            this.hoursWorked = hoursWorked;
        }
    }
}
