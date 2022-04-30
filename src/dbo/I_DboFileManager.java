/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dbo;

import java.util.ArrayList;

/**
 *
 * @author nguye
 */
public interface I_DboFileManager<E> {

    public ArrayList<E> readFile() throws Exception;

    public void writeFile(ArrayList<E> arr) throws Exception;

    public boolean checkFile();
}
