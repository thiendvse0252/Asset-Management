/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import controller.AssetBorrowedController;
import controller.AssetManager;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import model.AssetBorrowed;
import model.Employee;

/**
 *
 * @author nguye
 */
public class AssetBorrowedView {

    public void showInfomation(AssetBorrowed obj) {
        if (obj != null) {
            String pattern = "yyyy-MM-dd HH:mm:ss";
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
            System.out.printf("%-10s | %-10s | %-10s | %-10d | %-10s \n", obj.getID(), obj.getAsset().getAssetID(), obj.getEmp().getEmpID(), obj.getQuantity(), simpleDateFormat.format(obj.gettDate()));
        }
    }

    public void showInfomation(ArrayList<AssetBorrowed> arrE) {
        if (arrE == null || arrE.isEmpty()) {
            System.out.println("List is empty");
        } else {
            String pattern = "yyyy-MM-dd HH:mm:ss";
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
            System.out.printf("%-10s   %-10s   %-10s   %-10s   %-10s \n", "REQUEST ID", "ASSET ID", "EMP ID", "QUANTITY", "REQUEST TIME");
            for (AssetBorrowed obj : arrE) {
                System.out.printf("%-10s | %-10s | %-10s | %-10d | %-10s \n", obj.getID(), obj.getAsset().getAssetID(), obj.getEmp().getEmpID(), obj.getQuantity(), simpleDateFormat.format(obj.gettDate()));
            }
        }

    }

    public void del(AssetManager assetMng, Employee user) throws Exception {
        try {
            boolean cont = true;
            do {
                int subChoice = choiceSubMenuReturnAsset();
                switch (subChoice) {
                    case 1:
                        returnAsset(assetMng, user);
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

    private void returnAsset(AssetManager assetMng, Employee user) throws Exception {
        try {
            AssetBorrowed assetBow = null;
            AssetBorrowedController assetBowCtrl = assetMng.getAssetBorrowedCtrl();
            ArrayList<AssetBorrowed> arrBor = assetBowCtrl.serachAssetBorrowedByUserName(user.getEmpID());
            showInfomation(arrBor);
            if (arrBor != null) {
                String idPattern = "^B\\d{3}$";
                String id = "";
                boolean checkId = true;
                do {
                    id = Utils.getString("Enter your Borrow Asset ID (Bxxx):", idPattern);
                    if ((assetBow = assetBowCtrl.getAssetBorrowed(id)) == null) {
                        System.out.print(id + " is none exist. Again please!");
                    } else {
                        checkId = false;
                    }
                } while (checkId);

                if (Utils.confirmYesNo("Are you sure to return Asset? (Y/N): ")) {
                    if (assetMng.returnAssetst(assetBow)) {
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

    private int choiceSubMenuReturnAsset() {
        I_Menu menu = new Menu();
        // ađd menu here
        System.out.println("Return Asset Menu");
        menu.addItem("1. Return Asset.");
        menu.addItem("2. Return Main menu.");
        menu.showMenu();
        int choice = menu.getChoice();
        return choice;
    }
}
