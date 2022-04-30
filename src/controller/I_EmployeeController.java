/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.util.ArrayList;

/**
 *
 * @author nguye
 */
public interface I_EmployeeController<E> {

    public E login(String userName, String passWord) throws Exception;

    public ArrayList<E> getAllUser() throws Exception;

    public boolean addUser(E user) throws Exception;

    public boolean delUser(E user) throws Exception;

    public E getUser(String userName) throws Exception;
}
