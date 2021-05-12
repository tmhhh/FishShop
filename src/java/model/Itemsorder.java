/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author tmh
 */
@Entity
@Table(name = "itemsorder")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Itemsorder.findAll", query = "SELECT i FROM Itemsorder i"),
    @NamedQuery(name = "Itemsorder.findByOrderID", query = "SELECT i FROM Itemsorder i WHERE i.itemsorderPK.orderID = :orderID"),
    @NamedQuery(name = "Itemsorder.findByQuantity", query = "SELECT i FROM Itemsorder i WHERE i.quantity = :quantity"),
    @NamedQuery(name = "Itemsorder.findByDateOrder", query = "SELECT i FROM Itemsorder i WHERE i.dateOrder = :dateOrder"),
    @NamedQuery(name = "Itemsorder.findByUserID", query = "SELECT i FROM Itemsorder i WHERE i.itemsorderPK.userID = :userID"),
    @NamedQuery(name = "Itemsorder.findByItemID", query = "SELECT i FROM Itemsorder i WHERE i.itemsorderPK.itemID = :itemID")})
public class Itemsorder implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected ItemsorderPK itemsorderPK;
    @Column(name = "quantity")
    private Integer quantity;
    @Column(name = "dateOrder")
    @Temporal(TemporalType.DATE)
    private Date dateOrder;
    @JoinColumn(name = "itemID", referencedColumnName = "itemID", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Items items;
    @JoinColumn(name = "userID", referencedColumnName = "userID", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Users users;

    public Itemsorder() {
    }

    public Itemsorder(ItemsorderPK itemsorderPK, int quantity, Date dateOrder, Items item, Users users) {
        this.itemsorderPK = itemsorderPK;
        this.quantity = quantity;
        this.dateOrder = dateOrder;
        this.users = users;
        this.items = item;
    }

    public Itemsorder(ItemsorderPK itemsorderPK) {
        this.itemsorderPK = itemsorderPK;
    }

    public Itemsorder(int orderID, int userID, String itemID) {
        this.itemsorderPK = new ItemsorderPK(orderID, userID, itemID);
    }

    public ItemsorderPK getItemsorderPK() {
        return itemsorderPK;
    }

    public void setItemsorderPK(ItemsorderPK itemsorderPK) {
        this.itemsorderPK = itemsorderPK;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Date getDateOrder() {
        return dateOrder;
    }

    public void setDateOrder(Date dateOrder) {
        this.dateOrder = dateOrder;
    }

    public Items getItems() {
        return items;
    }

    public void setItems(Items items) {
        this.items = items;
    }

    public Users getUsers() {
        return users;
    }

    public void setUsers(Users users) {
        this.users = users;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (itemsorderPK != null ? itemsorderPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Itemsorder)) {
            return false;
        }
        Itemsorder other = (Itemsorder) object;
        if ((this.itemsorderPK == null && other.itemsorderPK != null) || (this.itemsorderPK != null && !this.itemsorderPK.equals(other.itemsorderPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.Itemsorder[ itemsorderPK=" + itemsorderPK + " ]";
    }

}
