/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Date;
import model.Asset;
import model.AssetBorrowed;
import model.AssetRequest;
import model.Employee;

/**
 *
 * @author nguye
 */
public class CreateData {

    public static void main(String[] agr) {
        try {

            //Employee
            ArrayList<Object> arr = new ArrayList<>();
            arr.add(new Employee("E160001", "Nguyen Hong Hiep", new Date(2000 - 1900, 6, 12), "EM", "male", "e10adc3949ba59abbe56e057f20f883e"));
            arr.add(new Employee("E160240", "Tran Dinh Khanhp", new Date(2002 - 1900, 7, 15), "EM", "male", "e10adc3949ba59abbe56e057f20f883e"));
            arr.add(new Employee("E140449", "Le Buu Nhan", new Date(2000 - 1900, 7, 10), "EM", "male", "e10adc3949ba59abbe56e057f20f883e"));
            arr.add(new Employee("E160798", "Truong Le Minh", new Date(2002 - 1900, 12, 03), "EM", "male", "e10adc3949ba59abbe56e057f20f883e"));
            arr.add(new Employee("E160052", "Hoa Doan", new Date(1990 - 1900, 6, 5), "MA", "male", "e10adc3949ba59abbe56e057f20f883e"));
            writePerObjectToFile("src/dbo/employee.dat", arr);

            //Asset
            arr = new ArrayList<>();
            arr.add(new Asset("A001", "Samsung projector", "White", 500, 3.2, 10, 5));
            arr.add(new Asset("A002", "Macbook pro 2016", "Silver", 1000, 2.2, 5, 2));
            writePerObjectToFile("src/dbo/asset.dat", arr);

            //Borrow
            arr = new ArrayList<>();
            Employee emp = new Employee("E140449", "Le Buu Nhan", new Date(2000 - 1900, 7, 10), "EM", "male", "e10adc3949ba59abbe56e057f20f883e");
            Asset asset = new Asset("A001", "Samsung projector", "White", 500, 3.2, 10, 4);
            AssetRequest req = new AssetRequest("R001", emp, asset, 1, new Date(2021 - 1900, 12, 23, 13, 17, 56), null);
            arr.add(req);
            emp = new Employee("E160001", "Nguyen Hong Hiep", new Date(2000 - 1900, 6, 12), "EM", "male", "e10adc3949ba59abbe56e057f20f883e");
            asset = new Asset("A002", "Macbook pro 2016", "Silver", 1000, 2.2, 5, 2);
            req = new AssetRequest("R002", emp, asset, 1, new Date(2021 - 1900, 12, 24, 12, 18, 56), null);
            arr.add(req);
            emp = new Employee("E160798", "Truong Le Minh", new Date(2002 - 1900, 12, 03), "EM", "male", "e10adc3949ba59abbe56e057f20f883e");
            asset = new Asset("A001", "Samsung projector", "White", 500, 3.2, 10, 4);
            req = new AssetRequest("R003", emp, asset, 1, new Date(2021 - 1900, 12, 23, 11, 19, 56), null);
            arr.add(req);
            emp = new Employee("E160240", "Tran Dinh Khanhp", new Date(2002 - 1900, 7, 15), "EM", "male", "e10adc3949ba59abbe56e057f20f883e");
            asset = new Asset("A002", "Macbook pro 2016", "Silver", 1000, 2.2, 5, 2);
            req = new AssetRequest("R007", emp, asset, 1, new Date(2021 - 1900, 12, 24, 10, 10, 56), null);
            arr.add(req);
            writePerObjectToFile("src/dbo/request.dat", arr);

            //Request
            arr = new ArrayList<>();
            emp = new Employee("E160001", "Nguyen Hong Hiep", new Date(2000 - 1900, 6, 12), "EM", "male", "e10adc3949ba59abbe56e057f20f883e");
            asset = new Asset("A001", "Samsung projector", "White", 500, 3.2, 10, 4);
            AssetBorrowed bor = new AssetBorrowed("B001", emp, asset, 1, new Date(2021 - 1900, 12, 23, 15, 13, 46), null);
            arr.add(bor);
            emp = new Employee("E160001", "Nguyen Hong Hiep", new Date(2000 - 1900, 6, 12), "EM", "male", "e10adc3949ba59abbe56e057f20f883e");
            asset = new Asset("A001", "Samsung projector", "White", 500, 3.2, 10, 4);
            bor = new AssetBorrowed("B002", emp, asset, 2, new Date(2021 - 1900, 12, 25, 16, 14, 56), null);
            arr.add(bor);
            emp = new Employee("E160798", "Truong Le Minh", new Date(2002 - 1900, 12, 03), "EM", "male", "e10adc3949ba59abbe56e057f20f883e");
            asset = new Asset("A002", "Macbook pro 2016", "Silver", 1000, 2.2, 5, 2);
            bor = new AssetBorrowed("B003", emp, asset, 3, new Date(2021 - 1900, 12, 15, 17, 15, 52), null);
            arr.add(bor);
            emp = new Employee("E160240", "Tran Dinh Khanhp", new Date(2002 - 1900, 7, 15), "EM", "male", "e10adc3949ba59abbe56e057f20f883e");
            asset = new Asset("A001", "Samsung projector", "White", 500, 3.2, 10, 4);
            bor = new AssetBorrowed("B007", emp, asset, 2, new Date(2021 - 1900, 12, 26, 12, 16, 53), null);
            arr.add(bor);
            writePerObjectToFile("src/dbo/borrow.dat", arr);
        } catch (Exception ex) {
            System.out.println(ex.toString());
        }
    }

    public static void writePerObjectToFile(String filepath, ArrayList<Object> serObj) {
        try {
            FileOutputStream fileOut = new FileOutputStream(filepath);
            try (ObjectOutputStream objectOut = new ObjectOutputStream(fileOut)) {
                //objectOut.writeObject(serObj);
                for (Object obj : serObj) {
                    objectOut.writeObject(obj);
                }
                objectOut.close();
            }
            //System.out.println("The Object  was succesfully written to a file");
            fileOut.close();
        } catch (IOException ex) {
        }
    }
}
