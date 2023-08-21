package com.employee.timesheet;

import java.time.LocalDate;

public class TimeSheetEntry {
    private LocalDate date;
    private int hoursWorked;

    public TimeSheetEntry() {
    }

    public TimeSheetEntry(LocalDate date, int hoursWorked) {
        this.date = date;
        this.hoursWorked = hoursWorked;
    }

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

