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
import model.Asset;

/**
 *
 * @author nguye
 */
public class AssetDbo implements I_DboFileManager<Asset> {

    private final String fileName = "src/dbo/asset.dat";

    @Override
    public ArrayList<Asset> readFile() throws Exception {
        ArrayList<Asset> result = null;
        FileInputStream fileIn = null;
        ObjectInputStream objectIn = null;
        try {
            fileIn = new FileInputStream(fileName);
            objectIn = new ObjectInputStream(fileIn);
            Object obj;
            while ((obj = objectIn.readObject()) != null) {
                if (obj instanceof Asset) {
                    if (result == null) {
                        result = new ArrayList<>();
                    }
                    result.add((Asset) obj);
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

    @Override
    public void writeFile(ArrayList<Asset> arr) throws Exception {
        try {
            FileOutputStream fileOut = new FileOutputStream(fileName);
            ObjectOutputStream objectOut = new ObjectOutputStream(fileOut);

            for (Asset obj : arr) {
                objectOut.writeObject(obj);
            }
            objectOut.close();

            fileOut.close();
        } catch (Exception ex) {
            throw ex;
        }
    }

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
