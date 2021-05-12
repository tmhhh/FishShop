/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Administrator
 */
public class Cart {

    private List<cartItem> listItems = new ArrayList<>();
    private double promoValue = 0;

    public double getPromoValue() {
        return promoValue;
    }

    public void setPromoValue(double promoValue) {
        this.promoValue = promoValue;
    }

    public Cart() {
//       listItems = new ArrayList<>();
    }

    public static cartItem lookUp(List<Items> listItems, String code) {
        for (Items item : listItems) {
            if (code.equals(item.getItemID())) {
                cartItem iCart = new cartItem();
                iCart.setItem(item);
                return iCart;
            }
        }
        return null;
    }
    
    public static cartItem lookUpItem(List<cartItem> listItems, String code) {
        for (cartItem item : listItems) {
            if (code.equals(item.getItem().getItemID())) {

                return item;
            }
        }
        return null;
    }

    public void setList(List<cartItem> listItems) {

        this.listItems = listItems;
    }

    public List<cartItem> getList() {
        return this.listItems;
    }

    public void addProduct(cartItem item) {
//       if(listItems.contains(item)){
//           item.SetQuantity(item.GetQuantity() + 1);
//       }
//       else
        this.listItems.add(item);
    }

    public void removeProduct(cartItem item) {
        this.listItems.remove(item);
    }

    public void updateQuantity(cartItem item, int quantity) {
        item.setQuantity(quantity);
    }

    public double getTotalCost() {
        double totalCost = 0;
        for (cartItem item : listItems) {
            if (item.isChecked()) {
                totalCost += item.getItem().getItemPrice() * item.getQuantity();
            }
        }
        totalCost = totalCost * (100 - promoValue) / 100;
        return totalCost;

    }
}
