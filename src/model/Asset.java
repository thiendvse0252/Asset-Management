/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;

/**
 *
 * @author nguye
 */
public class Asset implements Serializable{

    private String assetID, assetName, color;
    private double price,weight;
    private int quantity, curQuantity;

    public Asset() {
    }

    public Asset(String assetID) {
        this.assetID = assetID;
    }

    public Asset(String assetID, String assetName, String color, double price, double weight, int quantity, int curQuantity) {
        this.assetID = assetID;
        this.assetName = assetName;
        this.color = color;
        this.price = price;
        this.weight = weight;
        this.quantity = quantity;
        this.curQuantity = curQuantity;
    }

    public String getAssetID() {
        return assetID;
    }

    public void setAssetID(String assetID) {
        this.assetID = assetID;
    }

    public String getAssetName() {
        return assetName;
    }

    public void setAssetName(String assetName) {
        this.assetName = assetName;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getCurQuantity() {
        return curQuantity;
    }

    public void setCurQuantity(int curQuantity) {
        this.curQuantity = curQuantity;
    }
    
    
}
