/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author nguye
 */
public class AssetTranfer implements Serializable {

    protected String tID;
    protected Employee emp;
    protected Asset asset;
    protected int quantity;
    protected Date tDate, rDate;

    public AssetTranfer() {
    }

    public AssetTranfer(String tID, Employee emp, Asset asset, int quantity, Date tDate, Date rDate) {
        this.tID = tID;
        this.emp = emp;
        this.asset = asset;
        this.quantity = quantity;
        this.tDate = tDate;
        this.rDate = rDate;
    }

    public String getID() {
        return tID;
    }

    public void settID(String tID) {
        this.tID = tID;
    }

    public Employee getEmp() {
        return emp;
    }

    public void setEmp(Employee emp) {
        this.emp = emp;
    }

    public Asset getAsset() {
        return asset;
    }

    public void setAsset(Asset asset) {
        this.asset = asset;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Date gettDate() {
        return tDate;
    }

    public void settDate(Date tDate) {
        this.tDate = tDate;
    }

    public Date getrDate() {
        return rDate;
    }

    public void setrDate(Date rDate) {
        this.rDate = rDate;
    }

}
