/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author tmh
 */
@Entity
@Table(name = "users")
@NamedQueries({
    @NamedQuery(name = "Users.findAll", query = "SELECT u FROM Users u"),
    @NamedQuery(name = "Users.findByUserID", query = "SELECT u FROM Users u WHERE u.userID = :userID"),
    @NamedQuery(name = "Users.findByUserName", query = "SELECT u FROM Users u WHERE u.userName = :userName"),
    @NamedQuery(name = "Users.findByUserNameID", query = "SELECT u FROM Users u WHERE u.userNameID = :userNameID"),
    @NamedQuery(name = "Users.findByUserPassword", query = "SELECT u FROM Users u WHERE u.userPassword = :userPassword"),
    @NamedQuery(name = "Users.findByDateCreated", query = "SELECT u FROM Users u WHERE u.dateCreated = :dateCreated"),
    @NamedQuery(name = "Users.findByUserEmail", query = "SELECT u FROM Users u WHERE u.userEmail = :userEmail"),
    @NamedQuery(name = "Users.findByUserAddress", query = "SELECT u FROM Users u WHERE u.userAddress = :userAddress"),
    @NamedQuery(name = "Users.findByUserPhone", query = "SELECT u FROM Users u WHERE u.userPhone = :userPhone"),
    @NamedQuery(name = "Users.findByUserAvatar", query = "SELECT u FROM Users u WHERE u.userAvatar = :userAvatar")})
public class Users implements Serializable {

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "users")
    private Collection<Itemsorder> itemsorderCollection;

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "userID")
    private Integer userID;
    @Column(name = "userName")
    private String userName;
    @Basic(optional = false)
    @Column(name = "userNameID")
    private String userNameID;
    @Basic(optional = false)
    @Column(name = "userPassword")
    private String userPassword;
    @Column(name = "date_created")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateCreated;
    @Column(name = "userEmail")
    private String userEmail;
    @Column(name = "userAddress")
    private String userAddress;
    @Column(name = "userPhone")
    private String userPhone;
    @Column(name = "userAvatar")
    private String userAvatar;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "userID")
    private Collection<Feedback> feedbackCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "userReplyID")
    private Collection<Feedbackreply> feedbackreplyCollection;

    public Users() {
    }

    public Users(Integer userID) {
        this.userID = userID;
    }

    public Users(Integer userID, String userNameID, String userPassword) {
        this.userID = userID;
        this.userNameID = userNameID;
        this.userPassword = userPassword;
    }

    public Users(Integer userID, String userName, String userNameID, String userPassword, String userEmail, String userAddress, String userPhone, String userAvatar) {
        this.userID = userID;
        this.userName = userName;
        this.userNameID = userNameID;
        this.userPassword = userPassword;
        this.userEmail = userEmail;
        this.userAddress = userAddress;
        this.userPhone = userPhone;
        this.userAvatar = userAvatar;
    }

    public Integer getUserID() {
        return userID;
    }

    public void setUserID(Integer userID) {
        this.userID = userID;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserNameID() {
        return userNameID;
    }

    public void setUserNameID(String userNameID) {
        this.userNameID = userNameID;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public Date getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getUserAddress() {
        return userAddress;
    }

    public void setUserAddress(String userAddress) {
        this.userAddress = userAddress;
    }

    public String getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone;
    }

    public String getUserAvatar() {
        return userAvatar;
    }

    public void setUserAvatar(String userAvatar) {
        this.userAvatar = userAvatar;
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

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (userID != null ? userID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Users)) {
            return false;
        }
        Users other = (Users) object;
        if ((this.userID == null && other.userID != null) || (this.userID != null && !this.userID.equals(other.userID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.Users[ userID=" + userID + " ]";
    }

    @XmlTransient
    public Collection<Itemsorder> getItemsorderCollection() {
        return itemsorderCollection;
    }

    public void setItemsorderCollection(Collection<Itemsorder> itemsorderCollection) {
        this.itemsorderCollection = itemsorderCollection;
    }

}
