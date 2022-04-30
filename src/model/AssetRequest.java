/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.Date;

/**
 *
 * @author nguye
 */
public class AssetRequest extends AssetTranfer {

    public AssetRequest(String tID,  Employee emp, Asset asset, int quantity, Date tDate, Date rDate) {
        super(tID, emp, asset, quantity, tDate, rDate);
    }

}
