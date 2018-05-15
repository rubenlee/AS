/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package JPA;

import java.io.Serializable;
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
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author ruben
 */
@Entity
@Table(name = "wallet")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Wallet.findAll", query = "SELECT w FROM Wallet w")
    , @NamedQuery(name = "Wallet.findById", query = "SELECT w FROM Wallet w WHERE w.id = :id")
    , @NamedQuery(name = "Wallet.findByCuantity", query = "SELECT w FROM Wallet w WHERE w.cuantity = :cuantity")})
public class Wallet implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "cuantity")
    private int cuantity;
    @JoinColumn(name = "iduser", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Users iduser;

    public Wallet() {
    }

    public Wallet(Integer id) {
        this.id = id;
    }

    public Wallet(Integer id, int cuantity) {
        this.id = id;
        this.cuantity = cuantity;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getCuantity() {
        return cuantity;
    }

    public void setCuantity(int cuantity) {
        this.cuantity = cuantity;
    }

    public Users getIduser() {
        return iduser;
    }

    public void setIduser(Users iduser) {
        this.iduser = iduser;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Wallet)) {
            return false;
        }
        Wallet other = (Wallet) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "JPA.Wallet[ id=" + id + " ]";
    }
    
}
