package model;

import java.time.LocalDateTime;

public class AttendanceRecord {
    private Employee employee;   // 🔥 instead of just ID
    private String attendanceType;
    private LocalDateTime time;

    public AttendanceRecord(Employee employee, String attendanceType, LocalDateTime time) {
        this.employee = employee;
        this.attendanceType = attendanceType;
        this.time = time;
    }

    public Employee getEmployee() {
        return employee;
    }

    public String getAttendanceType() {
        return attendanceType;
    }

    public LocalDateTime getTime() {
        return time;
    }
}
