/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author tmh
 */
@Embeddable
public class ItemsorderPK implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Column(name = "orderID")
    private int orderID;
    @Basic(optional = false)
    @NotNull
    @Column(name = "userID")
    private int userID;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "itemID")
    private String itemID;

    public ItemsorderPK() {
    }

    public ItemsorderPK(int orderID, int userID, String itemID) {
        this.orderID = orderID;
        this.userID = userID;
        this.itemID = itemID;
    }

    public int getOrderID() {
        return orderID;
    }

    public void setOrderID(int orderID) {
        this.orderID = orderID;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public String getItemID() {
        return itemID;
    }

    public void setItemID(String itemID) {
        this.itemID = itemID;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) orderID;
        hash += (int) userID;
        hash += (itemID != null ? itemID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ItemsorderPK)) {
            return false;
        }
        ItemsorderPK other = (ItemsorderPK) object;
        if (this.orderID != other.orderID) {
            return false;
        }
        if (this.userID != other.userID) {
            return false;
        }
        if ((this.itemID == null && other.itemID != null) || (this.itemID != null && !this.itemID.equals(other.itemID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.ItemsorderPK[ orderID=" + orderID + ", userID=" + userID + ", itemID=" + itemID + " ]";
    }
    
}
