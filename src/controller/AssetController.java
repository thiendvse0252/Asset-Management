/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dbo.AssetDbo;
import java.util.ArrayList;
import model.Asset;

/**
 *
 * @author nguye
 */
public class AssetController implements I_AssetController<Asset> {

    ArrayList<Asset> arrayList = null;

    @Override
    public ArrayList<Asset> getAllAsset() throws Exception {
        return arrayList;
    }

    @Override
    public boolean addAsset(Asset asset) throws Exception {
        if (arrayList == null) {
            arrayList = new ArrayList<>();
        }
        arrayList.add(asset);
        this.writeData();
        return true;
    }

    @Override
    public boolean delAsset(Asset asset) throws Exception {
        boolean result = false;
        if (arrayList != null) {
            result = true;
            arrayList.remove(asset);
        }
        return result;
    }

    @Override
    public Asset getAsset(String id) throws Exception {
        Asset result = null;
        if (arrayList != null) {
            int size = arrayList.size();
            boolean flag = true;
            for (int i = 0; flag && i < size; i++) {
                Asset temp = ((Asset) arrayList.get(i));
                if (temp.getAssetID().equals(id)) {
                    result = temp;
                    flag = false;
                }
            }
        }
        return result;
    }

    @Override
    public ArrayList<Asset> serachAssetByName(String key) throws Exception {
        ArrayList<Asset> result = null;
        if (arrayList != null) {
            int size = arrayList.size();
            for (int i = 0; i < size; i++) {
                Asset temp = ((Asset) arrayList.get(i));
                if (temp.getAssetName().toLowerCase().contains(key.toLowerCase())) {
                    if (result == null) {
                        result = new ArrayList<>();
                    }
                    result.add(temp);
                }
            }
        }
        return result;
    }

    @Override
    public boolean loadData() throws Exception {
        boolean result = false;
        AssetDbo dbo = new AssetDbo();
        if (dbo.checkFile()) {
            arrayList = dbo.readFile();
            result = true;
        }
        return result;
    }

    @Override
    public boolean writeData() throws Exception {
        boolean result = false;
        AssetDbo dbo = new AssetDbo();
        dbo.writeFile(arrayList);
        result = true;
        return result;
    }

}
