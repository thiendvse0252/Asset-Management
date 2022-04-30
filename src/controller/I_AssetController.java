/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.util.ArrayList;

/**
 *
 * @author nguye
 */
public interface I_AssetController<E> {

    public ArrayList<E> getAllAsset() throws Exception;

    public boolean addAsset(E asset) throws Exception;

    public boolean delAsset(E asset) throws Exception;

    public E getAsset(String id) throws Exception;

    public ArrayList<E> serachAssetByName(String key) throws Exception;

    public boolean loadData() throws Exception;

    public boolean writeData() throws Exception;
}
