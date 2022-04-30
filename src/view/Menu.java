package view;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * This is comment, do not delete 2021.11.30
 * and open the template in the editor.
 */
/**
 *
 * @author USER
 */
import java.util.ArrayList;

public class Menu extends ArrayList<String> implements I_Menu {

    public Menu() {
        super();
    }
    // must implement all abstract method of I_Menu interface

    @Override
    public void addItem(String s) {
        this.add(s);
    }

    @Override
    public void showMenu() {
        // do not delete this comment, your code here:
        int size = this.size();
        for(int i = 0; i<size; i++)
        {
            System.out.println(this.get(i));
        }
    }

    @Override
    public boolean confirmYesNo(String welcome) {
        boolean result= Utils.confirmYesNo(welcome);
        return result;
    }

    @Override
    public int getChoice() {
        return Utils.getInt("Input your choice:", 1, this.size());
    }

}
