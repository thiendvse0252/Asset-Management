/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.util.ArrayList;
import model.Asset;
import model.AssetBorrowed;
import model.AssetRequest;

/**
 *
 * @author nguye
 */
public class AssetManager {

    AssetController assetController;
    AssetBorrowedController assetBorrowedCtrl;
    AssetRequestController assetRequestCtrl;

    public AssetManager() throws Exception {
        try {
            initData();
        } catch (Exception ex) {
            throw ex;
        }
    }

    private void initData() throws Exception {
        try {
            assetController = new AssetController();
            assetController.loadData();

            assetRequestCtrl = new AssetRequestController();
            assetRequestCtrl.loadData();
            ArrayList<AssetRequest> arrAssetReq = assetRequestCtrl.getAllAssetRequest();
            if (arrAssetReq != null) {
                for (AssetRequest req : arrAssetReq) {
                    if (req != null) {
                        Asset asset = req.getAsset();
                        if (asset != null) {
                            asset = assetController.getAsset(asset.getAssetID());
                            req.setAsset(asset);
                        }
                    }
                }
            }

            assetBorrowedCtrl = new AssetBorrowedController();
            assetBorrowedCtrl.loadData();
            ArrayList<AssetBorrowed> arrAssetBor = assetBorrowedCtrl.getAllAssetBorrowed();
            if (arrAssetBor != null) {
                for (AssetBorrowed req : arrAssetBor) {
                    if (req != null) {
                        Asset asset = req.getAsset();
                        if (asset != null) {
                            asset = assetController.getAsset(asset.getAssetID());
                            req.setAsset(asset);
                        }
                    }
                }
            }
        } catch (Exception ex) {
            throw ex;
        }
    }

    public AssetController getAssetController() {
        return assetController;
    }

    public void setAssetController(AssetController assetController) {
        this.assetController = assetController;
    }

    public AssetBorrowedController getAssetBorrowedCtrl() {
        return assetBorrowedCtrl;
    }

    public AssetRequestController getAssetRequestCtrl() {
        return assetRequestCtrl;
    }

    public boolean approveRequest(AssetRequest assetReq) throws Exception {
        try {
            boolean result = false;
            if (assetReq != null) {
                Asset asset = assetReq.getAsset();
                if (asset != null) {
                    int avaible = asset.getCurQuantity() - assetReq.getQuantity();
                    if (avaible >= 0) {
                        asset.setCurQuantity(avaible);
                        AssetBorrowed assetBor = new AssetBorrowed("", assetReq.getEmp(), assetReq.getAsset(), assetReq.getQuantity(), assetReq.getrDate(), null);
                        assetRequestCtrl.delAssetRequest(assetReq);
                        assetBorrowedCtrl.addAssetBorrowed(assetBor);

                        assetController.writeData();
                        assetRequestCtrl.writeData();
                        assetBorrowedCtrl.writeData();
                        result = true;
                    }
                }
            }
            return result;
        } catch (Exception ex) {
            throw ex;
        }
    }

    public boolean returnAssetst(AssetBorrowed assetBow) throws Exception {
        try {
            boolean result = false;
            if (assetBow != null) {
                Asset asset = assetBow.getAsset();
                if (asset != null) {
                    int avaible = asset.getCurQuantity() + assetBow.getQuantity();

                    asset.setCurQuantity(avaible);
                    assetBorrowedCtrl.delAssetBorrowed(assetBow);

                    assetController.writeData();
                    assetBorrowedCtrl.writeData();
                    result = true;

                }
            }
            return result;
        } catch (Exception ex) {
            throw ex;
        }
    }
}
