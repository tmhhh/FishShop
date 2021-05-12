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
import javax.persistence.Id;
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
@Table(name = "promocodes")
@NamedQueries({
    @NamedQuery(name = "Promocodes.findAll", query = "SELECT p FROM Promocodes p"),
    @NamedQuery(name = "Promocodes.findByCodeID", query = "SELECT p FROM Promocodes p WHERE p.codeID = :codeID"),
    @NamedQuery(name = "Promocodes.findByCode", query = "SELECT p FROM Promocodes p WHERE p.code = :code"),
    @NamedQuery(name = "Promocodes.findByCodeValue", query = "SELECT p FROM Promocodes p WHERE p.codeValue = :codeValue"),
    @NamedQuery(name = "Promocodes.findByCodeDate", query = "SELECT p FROM Promocodes p WHERE p.codeDate = :codeDate")})
public class Promocodes implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "codeID")
    private String codeID;
    @Column(name = "code")
    private String code;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "codeValue")
    private Double codeValue;
    @Column(name = "codeDate")
    @Temporal(TemporalType.DATE)
    private Date codeDate;

    public Promocodes() {
    }

    public Promocodes(String codeID) {
        this.codeID = codeID;
    }

    public String getCodeID() {
        return codeID;
    }

    public void setCodeID(String codeID) {
        this.codeID = codeID;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Double getCodeValue() {
        return codeValue;
    }

    public void setCodeValue(Double codeValue) {
        this.codeValue = codeValue;
    }

    public Date getCodeDate() {
        return codeDate;
    }

    public void setCodeDate(Date codeDate) {
        this.codeDate = codeDate;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codeID != null ? codeID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Promocodes)) {
            return false;
        }
        Promocodes other = (Promocodes) object;
        if ((this.codeID == null && other.codeID != null) || (this.codeID != null && !this.codeID.equals(other.codeID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.Promocodes[ codeID=" + codeID + " ]";
    }
    
}
