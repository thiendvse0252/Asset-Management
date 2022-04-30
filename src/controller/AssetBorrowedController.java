/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dbo.AssetBorrowDbo;
import java.util.ArrayList;
import model.AssetBorrowed;

/**
 *
 * @author nguye
 */
public class AssetBorrowedController {

    ArrayList<AssetBorrowed> arrayList = null;

    private String raiseAssetBorrowedId() throws Exception {
        try {
            String result = "";
            int maxIndex = 1;
            if (arrayList == null) {
                maxIndex = 1;
                result = "B" + String.format("%03d", maxIndex);
            } else {
                maxIndex = arrayList.size() + 1;
                result = "B" + String.format("%03d", maxIndex);
                AssetBorrowed temp = this.getAssetBorrowed(result);
                while (temp != null) {
                    maxIndex += 1;
                    result = "B" + String.format("%03d", maxIndex);
                    temp = this.getAssetBorrowed(result);
                }
            }
            return result;
        } catch (Exception ex) {
            throw ex;
        }
    }

    public ArrayList<AssetBorrowed> getAllAssetBorrowed() throws Exception {
        return arrayList;
    }

    public boolean addAssetBorrowed(AssetBorrowed assetBorrowed) throws Exception {
        if (arrayList == null) {
            arrayList = new ArrayList<>();
        }
        assetBorrowed.settID(raiseAssetBorrowedId());
        arrayList.add(assetBorrowed);
        this.writeData();
        return true;
    }

    public boolean delAssetBorrowed(AssetBorrowed assetBorrowed) throws Exception {
        boolean result = false;
        if (arrayList != null) {
            result = true;
            arrayList.remove(assetBorrowed);
        }
        return result;
    }

    public AssetBorrowed getAssetBorrowed(String id) throws Exception {
        AssetBorrowed result = null;
        if (arrayList != null) {
            int size = arrayList.size();
            boolean flag = true;
            for (int i = 0; flag && i < size; i++) {
                AssetBorrowed temp = ((AssetBorrowed) arrayList.get(i));
                if (temp.getID().equals(id)) {
                    result = temp;
                    flag = false;
                }
            }
        }
        return result;
    }

    public ArrayList<AssetBorrowed> serachAssetBorrowedByUserName(String key) throws Exception {
        ArrayList<AssetBorrowed> result = null;
        if (arrayList != null) {
            int size = arrayList.size();
            for (int i = 0; i < size; i++) {
                AssetBorrowed temp = ((AssetBorrowed) arrayList.get(i));
                if (temp.getEmp().getEmpID().equalsIgnoreCase(key)) {
                    if (result == null) {
                        result = new ArrayList<>();
                    }
                    result.add(temp);
                }
            }
        }
        return result;
    }

    public boolean loadData() throws Exception {
        boolean result = false;
        AssetBorrowDbo dbo = new AssetBorrowDbo();
        if (dbo.checkFile()) {
            arrayList = dbo.readFile();            
            result = true;
        }
        return result;
    }

    public boolean writeData() throws Exception {
        boolean result = false;
        AssetBorrowDbo dbo = new AssetBorrowDbo();
        dbo.writeFile(arrayList);
        result = true;
        return result;
    }

}
