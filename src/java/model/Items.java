/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author tmh
 */
@Entity
@Table(name = "items")
@NamedQueries({
    @NamedQuery(name = "Items.findAll", query = "SELECT i FROM Items i"),
    @NamedQuery(name = "Items.findByItemID", query = "SELECT i FROM Items i WHERE i.itemID = :itemID"),
    @NamedQuery(name = "Items.findByItemName", query = "SELECT i FROM Items i WHERE i.itemName = :itemName"),
    @NamedQuery(name = "Items.findByItemPrice", query = "SELECT i FROM Items i WHERE i.itemPrice = :itemPrice"),
    @NamedQuery(name = "Items.findByItemImageData", query = "SELECT i FROM Items i WHERE i.itemImageData = :itemImageData"),
    @NamedQuery(name = "Items.findByItemImageName", query = "SELECT i FROM Items i WHERE i.itemImageName = :itemImageName"),
    @NamedQuery(name = "Items.findByQuantity", query = "SELECT i FROM Items i WHERE i.quantity = :quantity")})
public class Items implements Serializable {

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "items")
    private Collection<Itemsorder> itemsorderCollection;

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "itemID")
    private String itemID;
    @Basic(optional = false)
    @Column(name = "itemName")
    private String itemName;
    @Basic(optional = false)
    @Column(name = "itemPrice")
    private double itemPrice;
    @Basic(optional = false)
    @Column(name = "itemImageData")
    private String itemImageData;
    @Basic(optional = false)
    @Column(name = "itemImageName")
    private String itemImageName;
    @Column(name = "quantity")
    private Integer quantity;
    @OneToMany(mappedBy = "itemID")
    private Collection<Feedback> feedbackCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "itemID")
    private Collection<Feedbackreply> feedbackreplyCollection;

    public Items() {
    }

    public Items(String itemID) {
        this.itemID = itemID;
    }

    public Items(String itemID, String itemName, double itemPrice, String itemImageData, String itemImageName,int quantity) {
        this.itemID = itemID;
        this.itemName = itemName;
        this.itemPrice = itemPrice;
        this.itemImageData = itemImageData;
        this.itemImageName = itemImageName;
        this.quantity=quantity;
    }

    public String getItemID() {
        return itemID;
    }

    public void setItemID(String itemID) {
        this.itemID = itemID;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public double getItemPrice() {
        return itemPrice;
    }

    public void setItemPrice(double itemPrice) {
        this.itemPrice = itemPrice;
    }

    public String getItemImageData() {
        return itemImageData;
    }

    public void setItemImageData(String itemImageData) {
        this.itemImageData = itemImageData;
    }

    public String getItemImageName() {
        return itemImageName;
    }

    public void setItemImageName(String itemImageName) {
        this.itemImageName = itemImageName;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Collection<Feedback> getFeedbackCollection() {
        return feedbackCollection;
    }

    public void setFeedbackCollection(Collection<Feedback> feedbackCollection) {
        this.feedbackCollection = feedbackCollection;
    }

    public Collection<Feedbackreply> getFeedbackreplyCollection() {
        return feedbackreplyCollection;
    }

    public void setFeedbackreplyCollection(Collection<Feedbackreply> feedbackreplyCollection) {
        this.feedbackreplyCollection = feedbackreplyCollection;
    }

    public static Items lookUp(List<Items> listItems, String code) {
        for (Items item : listItems) {
            if (code.equals(item.getItemID())) {

                return item;
            }
        }
        return null;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (itemID != null ? itemID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Items)) {
            return false;
        }
        Items other = (Items) object;
        if ((this.itemID == null && other.itemID != null) || (this.itemID != null && !this.itemID.equals(other.itemID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.Items[ itemID=" + itemID + " ]";
    }

    @XmlTransient
    public Collection<Itemsorder> getItemsorderCollection() {
        return itemsorderCollection;
    }

    public void setItemsorderCollection(Collection<Itemsorder> itemsorderCollection) {
        this.itemsorderCollection = itemsorderCollection;
    }

}
