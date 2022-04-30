/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import controller.AssetController;
import controller.AssetManager;
import controller.AssetRequestController;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import model.Asset;
import model.AssetRequest;
import model.Employee;

/**
 *
 * @author nguye
 */
public class AssetRequestView {

    public void add(AssetManager assetMng, Employee user) throws Exception {
        try {
            boolean cont = true;
            do {
                int subChoice = choiceSubMenuBorrowAsset();
                switch (subChoice) {
                    case 1:
                        addAssetReques(assetMng, user);
                        break;
                    default:
                        cont = false;
                        break;
                }
            } while (cont);
        } catch (Exception ex) {
            throw ex;
        }
    }

    private void addAssetReques(AssetManager assetMng, Employee user) throws Exception {
        try {
            Asset asset = null;
            AssetView assetView = new AssetView();
            AssetController assetCtrl = assetMng.getAssetController();
            AssetRequestController assetReqCtrl = assetMng.getAssetRequestCtrl();
            assetView.showInfomation(assetCtrl.getAllAsset());

            String idPattern = "^A\\d{3}$";
            String id = "";
            boolean checkId = true;
            do {
                id = Utils.getString("Enter your Asset ID (Axxx):", idPattern);
                if ((asset = assetCtrl.getAsset(id)) == null) {
                    System.out.print(id + " is none exist. Again please!");
                } else {
                    checkId = false;
                }
            } while (checkId);

            int quantity = Utils.getInt("Enter Asset quantity:", 0, 100000);

            AssetRequest assetReq = new AssetRequest("", user, asset, quantity, new Date(System.currentTimeMillis()), null);

            if (assetReqCtrl.addAssetRequest(assetReq)) {
                System.out.println("Add asset request success");
            } else {
                System.out.println("Add asset request fail!!!!!");
            }
        } catch (Exception ex) {
            throw ex;
        }
    }

    private int choiceSubMenuBorrowAsset() {
        I_Menu menu = new Menu();
        // ađd menu here
        System.out.println("Borrow Asset Menu");
        menu.addItem("1. Borrow Asset.");
        menu.addItem("2. Return Main menu.");
        menu.showMenu();
        int choice = menu.getChoice();
        return choice;
    }

    public void del(AssetManager assetMng, Employee user) throws Exception {
        try {
            boolean cont = true;
            do {
                int subChoice = choiceSubMenuCancelAsset();
                switch (subChoice) {
                    case 1:
                        cancelReques(assetMng, user);
                        break;
                    default:
                        cont = false;
                        break;
                }
            } while (cont);
        } catch (Exception ex) {
            throw ex;
        }
    }

    private void cancelReques(AssetManager assetMng, Employee user) throws Exception {
        try {
            AssetRequest assetReq = null;
            AssetRequestController assetReqCtrl = assetMng.getAssetRequestCtrl();
            ArrayList<AssetRequest> arrReq = assetReqCtrl.serachAssetRequestByUserName(user.getEmpID());
            showInfomation(arrReq);
            if (arrReq != null) {
                String idPattern = "^R\\d{3}$";
                String id = "";
                boolean checkId = true;
                do {
                    id = Utils.getString("Enter your Borrow Asset ID (Rxxx):", idPattern);
                    if ((assetReq = assetReqCtrl.getAssetRequest(id)) == null) {
                        System.out.print(id + " is none exist. Again please!");
                    } else {
                        checkId = false;
                    }
                } while (checkId);

                if (Utils.confirmYesNo("Are you sure to cancel Asset? (Y/N): ")) {
                    if (assetReqCtrl.delAssetRequest(assetReq)) {
                        System.out.println("Cancel asset request success");
                    } else {
                        System.out.println("Cancel asset request fail!!!!!");
                    }
                }
            }
        } catch (Exception ex) {
            throw ex;
        }
    }

    private int choiceSubMenuCancelAsset() {
        I_Menu menu = new Menu();
        // ađd menu here
        System.out.println("Cancel Asset Menu");
        menu.addItem("1. Cancel Asset.");
        menu.addItem("2. Return Main menu.");
        menu.showMenu();
        int choice = menu.getChoice();
        return choice;
    }

    public void showInfomation(AssetRequest obj) {
        if (obj != null) {
            String pattern = "yyyy-MM-dd HH:mm:ss";
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
            System.out.printf("%-10s | %-10s | %-10s | %-10d | %-10s \n", obj.getID(), obj.getAsset().getAssetID(), obj.getEmp().getEmpID(), obj.getQuantity(), simpleDateFormat.format(obj.gettDate()));
        }
    }

    public void showInfomation(ArrayList<AssetRequest> arrE) {
        if (arrE == null || arrE.isEmpty()) {
            System.out.println("List is empty");
        } else {
            String pattern = "yyyy-MM-dd HH:mm:ss";
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
            System.out.printf("%-10s   %-10s   %-10s   %-10s   %-10s \n", "REQUEST ID", "ASSET ID", "EMP ID", "QUANTITY", "REQUEST TIME");
            for (AssetRequest obj : arrE) {
                System.out.printf("%-10s | %-10s | %-10s | %-10d | %-10s \n", obj.getID(), obj.getAsset().getAssetID(), obj.getEmp().getEmpID(), obj.getQuantity(), simpleDateFormat.format(obj.gettDate()));
            }
        }
    }
}
