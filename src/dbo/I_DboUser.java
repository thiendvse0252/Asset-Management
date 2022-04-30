/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dbo;

/**
 *
 * @author nguye
 * @param <E>
 */
public interface I_DboUser<E> {

    public E login(String userName, String passWord) throws Exception;
}
