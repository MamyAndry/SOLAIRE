package solaire.entity;

import annotation.Column;
import annotation.PrimaryKey;
import annotation.Table;


@Table(name = "batiment")
public class Batiment{
    
    @PrimaryKey(sequence = "seq_batiment", prefix = "BAT")
    @Column(name = "id_batiment")
    String idBatiment;
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
    public String getIdBatiment(){
        return this.idBatiment;
    }
    public void setIdBatiment(String idBatiment){
        this.idBatiment = idBatiment;
    }
    public String getNom(){
        return this.nom;
    }
    public void setNom(String nom){
        this.nom = nom;
    }

    //CONSTRUCTORS

    public Batiment(){}
    public Batiment(Integer capaciteMax, String idSecteur, String idBatiment, String nom){
        setCapaciteMax(capaciteMax);
        setIdSecteur(idSecteur);
        setIdBatiment(idBatiment);
        setNom(nom);
    }

}
