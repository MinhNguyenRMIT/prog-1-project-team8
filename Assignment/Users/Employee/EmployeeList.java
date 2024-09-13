package Assignment.Users.Employee;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class EmployeeList {

    // List to hold all employee data
    public static List<Employee> readEmployeesFromTXT(String filePath) throws IOException, ParseException {
        List<Employee> employees = new ArrayList<>();
        String line;
        String delimiter = " ";

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            while ((line = br.readLine()) != null) {
                String[] data = line.split(delimiter);

                if (data.length < 12) {
                    System.out.println("Skipping invalid line: " + line);
                    continue; // Skipping invalid entries
                }

                int employeeNumber = Integer.parseInt(data[0]);
                String username = data[1];
                String password = data[2];
                String fullName = data[3] + " " + data[4];  // Concatenate first and last name
                Date dob = new SimpleDateFormat("yyyy-MM-dd").parse(data[5]);
                String address = data[6] + " " + data[7] + " " + data[8];
                String phoneNumber = data[9];
                String email = data[10];
                String userType = data[11];
                String status = data[12];
                String position = data[13];

                Employee employee = new Employee(employeeNumber, username, password, fullName, dob, address, phoneNumber, email, userType, status, position);
                employees.add(employee);
            }
        }

        return employees;
    }

}
