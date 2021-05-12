/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author tmh
 */
@Entity
@Table(name = "feedbackreply")
@NamedQueries({
    @NamedQuery(name = "Feedbackreply.findAll", query = "SELECT f FROM Feedbackreply f"),
    @NamedQuery(name = "Feedbackreply.findByFeedbackReplyID", query = "SELECT f FROM Feedbackreply f WHERE f.feedbackReplyID = :feedbackReplyID"),
    @NamedQuery(name = "Feedbackreply.findByFeedbackReplyIDContent", query = "SELECT f FROM Feedbackreply f WHERE f.feedbackReplyIDContent = :feedbackReplyIDContent"),
    @NamedQuery(name = "Feedbackreply.findByDateCreated", query = "SELECT f FROM Feedbackreply f WHERE f.dateCreated = :dateCreated")})
public class Feedbackreply implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "feedbackReplyID")
    private Integer feedbackReplyID;
    @Column(name = "feedbackReplyIDContent")
    private String feedbackReplyIDContent;
    @Column(name = "date_created")
    @Temporal(TemporalType.DATE)
    private Date dateCreated;
    @JoinColumn(name = "feedbackID", referencedColumnName = "feedbackID")
    @ManyToOne(optional = false)
    private Feedback feedbackID;
    @JoinColumn(name = "itemID", referencedColumnName = "itemID")
    @ManyToOne(optional = false)
    private Items itemID;
    @JoinColumn(name = "userReplyID", referencedColumnName = "userID")
    @ManyToOne(optional = false)
    private Users userReplyID;

    public Feedbackreply() {
    }

    public Feedbackreply(String feedbackReplyIDContent, Date dateCreated, Items itemID, Feedback feedbackID, Users userReplyID) {
        this.feedbackReplyIDContent = feedbackReplyIDContent;
        this.dateCreated = dateCreated;
        this.itemID = itemID;
        this.feedbackID = feedbackID;
        this.userReplyID = userReplyID;
    }

    public Feedbackreply(Integer feedbackReplyID) {
        this.feedbackReplyID = feedbackReplyID;
    }

    public Integer getFeedbackReplyID() {
        return feedbackReplyID;
    }

    public void setFeedbackReplyID(Integer feedbackReplyID) {
        this.feedbackReplyID = feedbackReplyID;
    }

    public String getFeedbackReplyIDContent() {
        return feedbackReplyIDContent;
    }

    public void setFeedbackReplyIDContent(String feedbackReplyIDContent) {
        this.feedbackReplyIDContent = feedbackReplyIDContent;
    }

    public Date getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }

    public Feedback getFeedbackID() {
        return feedbackID;
    }

    public void setFeedbackID(Feedback feedbackID) {
        this.feedbackID = feedbackID;
    }

    public Items getItemID() {
        return itemID;
    }

    public void setItemID(Items itemID) {
        this.itemID = itemID;
    }

    public Users getUserReplyID() {
        return userReplyID;
    }

    public void setUserReplyID(Users userReplyID) {
        this.userReplyID = userReplyID;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (feedbackReplyID != null ? feedbackReplyID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Feedbackreply)) {
            return false;
        }
        Feedbackreply other = (Feedbackreply) object;
        if ((this.feedbackReplyID == null && other.feedbackReplyID != null) || (this.feedbackReplyID != null && !this.feedbackReplyID.equals(other.feedbackReplyID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.Feedbackreply[ feedbackReplyID=" + feedbackReplyID + " ]";
    }

}
