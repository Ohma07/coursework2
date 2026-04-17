package service;

import model.AttendanceRecord;
import model.Employee;

import java.io.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class FileService {

    public static void save(List<AttendanceRecord> records) {
        try (PrintWriter writer = new PrintWriter("data.csv")) {

            for (AttendanceRecord r : records) {
                writer.println(
                        r.getEmployee().getId() + "," +
                                r.getEmployee().getName() + "," +
                                r.getEmployee().getEmail() + "," +
                                r.getAttendanceType() + "," +
                                r.getTime()
                );
            }

        } catch (IOException e) {
            System.out.println("Save error");
        }
    }

    public static List<AttendanceRecord> load() {
        List<AttendanceRecord> list = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader("data.csv"))) {
            String line;

            while ((line = br.readLine()) != null) {
                String[] d = line.split(",");

                Employee emp = new Employee(
                        Integer.parseInt(d[0]),
                        d[1],
                        d[2]
                );

                AttendanceRecord r = new AttendanceRecord(
                        emp,
                        d[3],
                        LocalDateTime.parse(d[4])
                );

                list.add(r);
            }

        } catch (IOException e) {
            System.out.println("No previous data");
        }

        return list;
    }
}