/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package solaire.entity;

import annotation.Column;
import annotation.PrimaryKey;
import annotation.Table;
import dao.BddObject;
import java.sql.Date;
import java.sql.Time;

/**
 *
 * @author Mamisoa
 */
@Table
public class Coupure extends BddObject{
    @PrimaryKey(prefix = "COUP", sequence = "seq_coupure")
    String id;
    @Column(name = "date_jour")
    Date dateJour;
    @Column(name = "id_secteur")
    String idSecteur;
    @Column
    Time heure;

    //GETTERS & SETTERS
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Date getDateJour() {
        return dateJour;
    }

    public void setDateJour(Date dateJour) {
        this.dateJour = dateJour;
    }

    public Time getHeure() {
        return heure; 
    }

    public void setHeure(Time heure) {
        this.heure = heure;
    }

    public String getIdSecteur() {
        return idSecteur;
    }

    public void setIdSecteur(String idSecteur) {
        this.idSecteur = idSecteur;
    }
    
    
}
