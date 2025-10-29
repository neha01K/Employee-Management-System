package ems.example.employeeManagementSystem;


import com.ems.entity.Employee;
import com.ems.repository.EmployeeRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
public class EmployeeTest {

    @Autowired
    EmployeeRepository employeeRepository;

    @Test
    public void testEmployeeRepository(){
        List<Employee> employeeList = employeeRepository.findAll();
        System.out.println(employeeList);
    }

}
