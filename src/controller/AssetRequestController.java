/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dbo.AssetRequestDbo;
import java.util.ArrayList;
import java.util.Comparator;
import model.AssetRequest;

/**
 *
 * @author nguye
 */
public class AssetRequestController {

    ArrayList<AssetRequest> arrayList = null;

    private String raiseAssetRequestId() throws Exception {
        try {
            String result = "";
            int maxIndex = 1;
            if (arrayList == null) {
                maxIndex = 1;
                result = "R" + String.format("%03d", maxIndex);
            } else {
                maxIndex = arrayList.size() + 1;
                result = "R" + String.format("%03d", maxIndex);
                AssetRequest temp = this.getAssetRequest(result);
                while (temp != null) {
                    maxIndex += 1;
                    result = "R" + String.format("%03d", maxIndex);
                    temp = this.getAssetRequest(result);
                }
            }
            return result;
        } catch (Exception ex) {
            throw ex;
        }
    }

    public ArrayList<AssetRequest> getAllAssetRequest() throws Exception {
        return arrayList;
    }

    public boolean addAssetRequest(AssetRequest assetRequest) throws Exception {
        if (arrayList == null) {
            arrayList = new ArrayList<>();
        }
        assetRequest.settID(raiseAssetRequestId());
        arrayList.add(assetRequest);
        this.writeData();
        return true;
    }

    public boolean delAssetRequest(AssetRequest assetRequest) throws Exception {
        boolean result = false;
        if (arrayList != null) {
            result = true;
            arrayList.remove(assetRequest);
            this.writeData();
        }
        return result;
    }

    public AssetRequest getAssetRequest(String id) throws Exception {
        AssetRequest result = null;
        if (arrayList != null) {
            int size = arrayList.size();
            boolean flag = true;
            for (int i = 0; flag && i < size; i++) {
                AssetRequest temp = ((AssetRequest) arrayList.get(i));
                if (temp.getID().equals(id)) {
                    result = temp;
                    flag = false;
                }
            }
        }
        return result;
    }

    public ArrayList<AssetRequest> serachAssetRequestByUserName(String key) throws Exception {
        ArrayList<AssetRequest> result = null;
        if (arrayList != null) {
            int size = arrayList.size();
            for (int i = 0; i < size; i++) {
                AssetRequest temp = ((AssetRequest) arrayList.get(i));
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
        AssetRequestDbo dbo = new AssetRequestDbo();
        if (dbo.checkFile()) {
            arrayList = dbo.readFile();
            if (arrayList != null) {
                Comparator com = new Comparator<AssetRequest>() {
                    @Override
                    public int compare(AssetRequest o1, AssetRequest o2) {
                        return o1.getID().compareTo(o2.getID());
                    }
                };
                arrayList.sort(com);
            }
            result = true;
        }
        return result;
    }

    public boolean writeData() throws Exception {
        boolean result = false;
        AssetRequestDbo dbo = new AssetRequestDbo();
        dbo.writeFile(arrayList);
        result = true;
        return result;
    }

}
