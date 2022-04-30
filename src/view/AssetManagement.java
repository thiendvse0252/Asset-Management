/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import controller.AssetController;
import controller.AssetManager;
import controller.EmployeeController;
import java.util.ArrayList;
import java.util.Date;
import model.Asset;
import model.AssetRequest;
import model.Employee;

/**
 *
 * @author nguye
 */
public class AssetManagement {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        try {
            AssetManagement assetMgn = new AssetManagement();
            Employee user = null;
            int choice;
            boolean cont = false;
            do {
                choice = assetMgn.showMainMenu();
                switch (choice) {
                    case 1:
                        user = assetMgn.login();
                        if (user == null) {
                            System.out.println("Login fail! Check username or password, please!");
                        } else {
                            assetMgn.doUserRole(user);
                        }
                        break;
                    case 2:
                        I_Menu menu = new Menu();
                        cont = menu.confirmYesNo("Do you want to quit?(Y/N): ");
                        if (cont) {
                        }
                        break;
                    default:
                        System.out.println("Invalid option!");
                }
            } while (!cont);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private int showMainMenu() {
        I_Menu menu = new Menu();
        // Menu chính
        menu.addItem("WELCOME TO ASSET MANAGEMENT");
        menu.addItem("1. Login");
        menu.addItem("2. Quit");
        menu.showMenu();
        return menu.getChoice();
    }

    private void doUserRole(Employee user) throws Exception {
        try {
            if (user != null) {
                switch (user.getRole()) {
                    case "MA":
                        doUserRoleManager(user);
                        break;
                    case "EM":
                        doUserRoleEmployee(user);
                        break;
                    default:
                        break;
                }
            }
        } catch (Exception ex) {
            throw ex;
        }
    }

    private void doUserRoleManager(Employee user) throws Exception {
        try {
            AssetManager assetManager = new AssetManager();
            int choice;
            do {
                choice = this.showMenuRoleUserManager(user.getEmpName());
                switch (choice) {
                    case 1:
                        String key = Utils.getString("Enter name to search: ");
                        AssetController assetCtrl = assetManager.getAssetController();
                        ArrayList<Asset> assetList = assetCtrl.serachAssetByName(key);
                        if (assetList == null) {
                            System.out.println("Not found!");
                        } else {
                            AssetView assetView = new AssetView();
                            assetView.showInfomation(assetList);
                        }
                        break;
                    case 2:
                        AssetView assetView = new AssetView();
                        assetCtrl = assetManager.getAssetController();
                        assetView.add(assetCtrl);
                        break;
                    case 3:
                        assetView = new AssetView();
                        assetCtrl = assetManager.getAssetController();
                        assetView.update(assetCtrl);
                        break;
                    case 4:
                        approveRequest(assetManager);
                        break;
                    case 5:
                        AssetBorrowedView borrowedView = new AssetBorrowedView();
                        borrowedView.showInfomation(assetManager.getAssetBorrowedCtrl().getAllAssetBorrowed());
                        break;
                    case 6:
                        break;
                    default:
                        System.out.println("Invalid option!");
                }
            } while (choice != 6);
        } catch (Exception ex) {
            throw ex;
        }
    }

    private int showMenuRoleUserManager(String empName) {
        //Menu về Manager
        I_Menu menu = new Menu();
        menu.addItem("WELCOME MANAGER " + empName.toUpperCase());
        menu.addItem("1. Search Asset");
        menu.addItem("2. Create Asset");
        menu.addItem("3. Update Asset's Information");
        menu.addItem("4. Approve Request");
        menu.addItem("5. Show Borrow Asset");
        menu.addItem("6. Quit");
        menu.showMenu();
        return menu.getChoice();
    }

    private void approveRequest(AssetManager assetManager) throws Exception {
        AssetRequestView requestView = new AssetRequestView();

        requestView.showInfomation(assetManager.getAssetRequestCtrl().getAllAssetRequest());
        boolean cont = true;
        do {
            int subChoice = choiceSubMenuApproveRequest();
            switch (subChoice) {
                case 1:
                    String idPattern = "^R\\d{3}$";
                    String id = Utils.getString("Enter your request ID (Rxxx):", idPattern);
                    AssetRequest assetReq = assetManager.getAssetRequestCtrl().getAssetRequest(id);
                    if (assetReq == null) {
                        System.out.print(id + " isn't exist!");
                    } else {
                        if (Utils.confirmYesNo("Are you sure to approve request? (Y/N)")) {
                            Date rDate = new Date(System.currentTimeMillis());
                            assetReq.setrDate(rDate);
                            assetManager.approveRequest(assetReq);
                        }
                    }
                    break;

                default:
                    cont = false;
                    break;
            }
        } while (cont);

    }

    private int choiceSubMenuApproveRequest() {
        I_Menu menu = new Menu();
        // ađd menu here
        System.out.println("Approve Request Asset Menu");
        menu.addItem("1. Approve Request Asset.");
        menu.addItem("2. Return Main menu.");
        menu.showMenu();
        int choice = menu.getChoice();
        return choice;
    }

    private void doUserRoleEmployee(Employee user) throws Exception {
        try {
            AssetManager assetManager = new AssetManager();
            int choice;
            do {
                choice = this.showMenuRoleUser(user.getEmpName());
                switch (choice) {
                    case 1:
                        String key = Utils.getString("Enter name to search: ");
                        AssetController assetCtrl = assetManager.getAssetController();
                        ArrayList<Asset> assetList = assetCtrl.serachAssetByName(key);
                        if (assetList == null) {
                            System.out.println("Not found!");
                        } else {
                            AssetView assetView = new AssetView();
                            assetView.showInfomation(assetList);
                        }
                        break;
                    case 2:
                        AssetRequestView assetReqView = new AssetRequestView();
                        assetReqView.add(assetManager, user);
                        break;
                    case 3:
                        assetReqView = new AssetRequestView();
                        assetReqView.del(assetManager, user);
                        break;
                    case 4:
                        AssetBorrowedView assetBorView = new AssetBorrowedView();
                        assetBorView.del(assetManager, user);
                        break;
                    case 5:
                        break;
                    default:
                        System.out.println("Invalid option!");
                }
            } while (choice != 5);
        } catch (Exception ex) {
            throw ex;
        }
    }

    private int showMenuRoleUser(String empName) {
        //Menu về Customer
        I_Menu empMenu = new Menu();
        empMenu.addItem("WELCOME EMPLOYEE " + empName.toUpperCase());
        empMenu.addItem("1. Search Asset");
        empMenu.addItem("2. Borrow Asset");
        empMenu.addItem("3. Cancel Request");
        empMenu.addItem("4. Return Asset");
        empMenu.addItem("5. Quit");
        empMenu.showMenu();
        return empMenu.getChoice();
    }

    private Employee login() throws Exception {
        try {
            Employee user = null;
            String userName = Utils.getString("Enter username (user ID): ");
            String pwd = Utils.getPassword("Enter password): ");
            EmployeeController empCtrl = new EmployeeController();
            user = empCtrl.login(userName, pwd);
            return user;
        } catch (Exception ex) {
            throw ex;
        }
    }
}
