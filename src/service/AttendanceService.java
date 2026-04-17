package service;

import model.AttendanceRecord;
import java.util.ArrayList;
import java.util.List;

public class AttendanceService {

    private List<AttendanceRecord> records = new ArrayList<>();

    // ✅ FIX: this method was missing or incorrect
    public void setRecords(List<AttendanceRecord> records) {
        this.records = records;
    }

    public List<AttendanceRecord> getRecords() {
        return records;
    }

    public void addRecord(AttendanceRecord r) {
        records.add(r);
    }

    public void updateRecord(int index, AttendanceRecord r) {
        if (index >= 0 && index < records.size()) {
            records.set(index, r);
        }
    }

    public void deleteRecord(int index) {
        if (index >= 0 && index < records.size()) {
            records.remove(index);
        }
    }
}
