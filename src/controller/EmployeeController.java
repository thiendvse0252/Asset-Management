/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dbo.EmployeeDbo;
import java.util.ArrayList;
import model.Employee;

/**
 *
 * @author nguye
 */
public class EmployeeController implements I_EmployeeController<Employee> {

    private ArrayList<Employee> arrEmp = null;

    public EmployeeController() {

    }

    @Override
    public Employee login(String userName, String passWord) throws Exception {
        Employee emp;
        EmployeeDbo dbo = new EmployeeDbo();
        emp = dbo.login(userName, passWord);
        return emp;
    }

    @Override
    public ArrayList<Employee> getAllUser() throws Exception {
        return arrEmp;
    }

    @Override
    public boolean addUser(Employee user) throws Exception {
        if (arrEmp == null) {
            arrEmp = new ArrayList<>();
        }
        arrEmp.add(user);
        return true;
    }

    @Override
    public boolean delUser(Employee user) throws Exception {
        boolean result = true;
        if (arrEmp == null) {
            result = false;
        } else {
            arrEmp.remove(user);
        }
        return result;
    }

    @Override
    public Employee getUser(String userName) throws Exception {
        Employee emp = null;
        if (arrEmp != null) {
            boolean check = false;
            int size = arrEmp.size();
            for (int i = 0; i < size && !check; i++) {
                Employee temp = (Employee) arrEmp.get(i);
                if (temp.getEmpID().equals(userName)) {
                    emp = temp;
                    check = true;
                }
            }
        }
        return emp;
    }
}
