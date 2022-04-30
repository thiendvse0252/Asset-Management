/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dbo;

import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import model.Employee;

/**
 *
 * @author nguye
 */
public class EmployeeDbo implements I_DboUser<Employee>, I_DboFileManager<Employee> {

    private final String fileName = "src/dbo/employee.dat";

    /**
     * Scan all user in database (File) and compare userName and passWord
     *
     * @param userName
     * @param passWord
     * @return Employee; if fail return null
     * @throws Exception
     */
    @Override
    public Employee login(String userName, String passWord) throws Exception {
        Employee result = null;
        FileInputStream fileIn = null;
        ObjectInputStream objectIn = null;
        boolean check = false;
        try {
            fileIn = new FileInputStream(fileName);
            objectIn = new ObjectInputStream(fileIn);
            Object obj;
            while (!check && (obj = objectIn.readObject()) != null) {
                if (obj instanceof Employee) {
                    Employee user = (Employee) obj;
                    if (user.getEmpID().equals(userName) && user.getPwd().equals(passWord)) {
                        result = user;
                        check = true;
                    }
                }
            }
            objectIn.close();
            fileIn.close();
        } catch (EOFException eof) {

        } catch (IOException ex) {
            throw ex;
        } finally {
            if (objectIn != null) {
                objectIn.close();
            }
            if (fileIn != null) {
                fileIn.close();
            }
        }
        return result;
    }

    /**
     * *
     * Scan all user in database (File) and add to ArrayList
     *
     * @return ArrayList<Employee> ; if fail return null
     * @throws Exception
     */
    @Override
    public ArrayList<Employee> readFile() throws Exception {
        ArrayList<Employee> result = null;

        FileInputStream fileIn = null;
        ObjectInputStream objectIn = null;
        try {
            fileIn = new FileInputStream(fileName);
            objectIn = new ObjectInputStream(fileIn);
            Object obj;
            while ((obj = objectIn.readObject()) != null) {
                if (obj instanceof Employee) {
                    if (result == null) {
                        result = new ArrayList<>();
                    }
                    result.add((Employee) obj);
                }
            }
            objectIn.close();
            fileIn.close();
        } catch (EOFException eof) {

        } catch (IOException ex) {
            throw ex;
        } finally {
            if (objectIn != null) {
                objectIn.close();
            }
            if (fileIn != null) {
                fileIn.close();
            }
        }
        return result;
    }

    /**
     * *
     * Scan all user in ArrayList and write to database (File)
     *
     * @param arr
     * @throws Exception
     */
    @Override
    public void writeFile(ArrayList<Employee> arr) throws Exception {
        try {
            FileOutputStream fileOut = new FileOutputStream(fileName);
            ObjectOutputStream objectOut = new ObjectOutputStream(fileOut);

            for (Employee obj : arr) {
                objectOut.writeObject(obj);
            }
            objectOut.close();

            fileOut.close();
        } catch (Exception ex) {
            throw ex;
        }
    }

    /**
     * Check database is Exist
     *
     * @return
     */
    @Override
    public boolean checkFile() {
        boolean result;
        File f;
        try {
            f = new File(fileName);//open file
            result = f.exists();
        } catch (Exception ex) {
            result = false;
        }
        return result;
    }
}
