/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import controller.AssetController;
import java.util.ArrayList;
import model.Asset;

/**
 *
 * @author nguye
 */
public class AssetView {

    private void addAsset(AssetController assetCon) throws Exception {
        try {
            String idPattern = "^A\\d{3}$";
            String id = "";
            boolean checkId = true;
            do {
                id = Utils.getString("Enter your Asset ID (Axxx):", idPattern);
                if (assetCon.getAsset(id) != null) {
                    System.out.print(id + " is exist. Again please!");
                } else {
                    checkId = false;
                }
            } while (checkId);
            String name = Utils.getString("Enter your Asset name:");
            String color = Utils.getString("Enter your Asset color:");
            double price = Utils.getDouble("Enter your Asset price:");
            double weight = Utils.getDouble("Enter your Asset weight:");
            int quantity = Utils.getInt("Enter your Asset quantity:", 0, 100000);

            Asset asset = new Asset(id, name, color, price, weight, quantity, quantity);

            if (assetCon.addAsset(asset)) {
                System.out.println("Add asset success");
            } else {
                System.out.println("Add asset fail!!!!!");
            }
        } catch (Exception ex) {
            throw ex;
        }
    }

    private boolean updateAsset(Asset obj) {
        boolean result = false;

        System.out.println("----Update a Asset. The field is none update, press Enter----");
        String name = Utils.updateString("Enter your Asset name:", obj.getAssetName());
        String color = Utils.updateString("Enter your Asset color:", obj.getColor());
        double price = Utils.updateDouble("Enter your Asset price:", obj.getPrice());
        double weight = Utils.updateDouble("Enter your Asset weight:", obj.getWeight());
        int quantity = Utils.updateInt("Enter your Asset quantity:", obj.getQuantity() - obj.getCurQuantity(), 100000, obj.getQuantity());

        obj.setAssetName(name);
        obj.setColor(color);
        obj.setPrice(price);
        obj.setWeight(weight);
        obj.setCurQuantity(quantity - (obj.getQuantity() - obj.getCurQuantity()));
        obj.setQuantity(quantity);
        System.out.println("Update asset success");
        return result;
    }

    public void showInfomation(Asset obj) {
        if (obj != null) {
            System.out.printf("%-10s | %-20s | %-10s | %-10.2f | %-10.2f | %-10d | %-10d\n", obj.getAssetID(), obj.getAssetName(), obj.getColor(), obj.getPrice(), obj.getWeight(), obj.getQuantity(), obj.getCurQuantity());
        }
    }

    public void showInfomation(ArrayList<Asset> arrE) {
        if (arrE == null || arrE.isEmpty()) {
            System.out.println("List is empty");
        } else {
            System.out.printf("%-10s | %-20s | %-10s | %-10s | %-10s | %-10s |%10s\n", "ASSET ID", "NAME", "COLOR", "PRICE", "WEIGHT", "QUANTITY", "AVAILABLE");
            for (Asset obj : arrE) {
                System.out.printf("%-10s | %-20s | %-10s | %-10.2f | %-10.2f | %-10d | %-10d\n", obj.getAssetID(), obj.getAssetName(), obj.getColor(), obj.getPrice(), obj.getWeight(), obj.getQuantity(), obj.getCurQuantity());
            }
        }
    }

    private int choiceSubMenuAddAsset() {
        I_Menu menu = new Menu();
        // ađd menu here
        System.out.println("Add Asset Menu");
        menu.addItem("1. Add Asset.");
        menu.addItem("2. Return Main menu.");
        menu.showMenu();
        int choice = menu.getChoice();
        return choice;
    }

    public void add(AssetController assetCon) throws Exception {
        try {
            boolean cont = true;
            do {
                int subChoice = choiceSubMenuAddAsset();
                switch (subChoice) {
                    case 1:
                        addAsset(assetCon);
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

    private int choiceSubMenuUpdateAsset() {
        I_Menu menu = new Menu();
        // ađd menu here
        System.out.println("Update Asset Menu");
        menu.addItem("1. Update Asset.");
        menu.addItem("2. Return Main menu.");
        menu.showMenu();
        int choice = menu.getChoice();
        return choice;
    }

    public void update(AssetController assetCon) throws Exception {
        try {
            boolean cont = true;
            do {
                int subChoice = choiceSubMenuUpdateAsset();
                switch (subChoice) {
                    case 1:
                        String idPattern = "^A\\d{3}$";
                        String id = "";
                        boolean checkId = true;
                        Asset asset = null;
                        do {
                            id = Utils.getString("Enter your asset ID (Axxx):", idPattern);
                            if ((asset = assetCon.getAsset(id)) == null) {
                                checkId = Utils.confirmYesNo(id + " is none exist. Do you want to continue? (Y/N)");
                            } else {
                                checkId = false;
                            }
                        } while (checkId);
                        if (!checkId) {
                            updateAsset(asset);
                            assetCon.writeData();
                        }
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
}
