package solaire.entity;

import annotation.Column;
import annotation.PrimaryKey;
import annotation.Table;
import dao.BddObject;


@Table(name = "salle")
public class Salle extends BddObject{
    
    @PrimaryKey(name = "id_salle", sequence = "seq_batiment", prefix = "SAL")
    String idSalle;
    @Column(name = "capacite_max")
    Integer capaciteMax;
    @Column(name = "id_secteur")
    String idSecteur;
    @Column(name = "nom")
    String nom;

//SETTERS AND GETTERS

    public Integer getCapaciteMax(){
        return this.capaciteMax;
    }
    public void setCapaciteMax(Integer capaciteMax){
        this.capaciteMax = capaciteMax;
    }
    public String getIdSecteur(){
        return this.idSecteur;
    }
    public void setIdSecteur(String idSecteur){
        this.idSecteur = idSecteur;
    }
    public String getIdSalle(){
        return this.idSalle;
    }
    public void setIdSalle(String idBatiment){
        this.idSalle = idBatiment;
    }
    public String getNom(){
        return this.nom;
    }
    public void setNom(String nom){
        this.nom = nom;
    }

    //CONSTRUCTORS

    public Salle() throws Exception{}
    public Salle(Integer capaciteMax, String idSecteur, String idSalle, String nom) throws Exception{
        setCapaciteMax(capaciteMax);
        setIdSecteur(idSecteur);
        setIdSalle(idSalle);
        setNom(nom);
    }

}
