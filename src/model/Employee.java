/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author nguye
 */
public class Employee implements Serializable {

    private String empID, empName, role, pwd, sex;
    private Date birthDate;

    public Employee() {
    }

    public Employee(String empID) {
        this.empID = empID;
    }

    public Employee(String empID, String empName, Date birthDate,String role, String sex,String pwd) {
        this.empID = empID;
        this.empName = empName;
        this.role = role;
        this.pwd = pwd;
        this.sex = sex;
        this.birthDate = birthDate;
    }

    public String getEmpID() {
        return empID;
    }

    public void setEmpID(String empID) {
        this.empID = empID;
    }

    public String getEmpName() {
        return empName;
    }

    public void setEmpName(String empName) {
        this.empName = empName;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

}
