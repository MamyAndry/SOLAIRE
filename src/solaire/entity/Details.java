package solaire.entity;

import annotation.PrimaryKey;
import annotation.Column;
import annotation.Table;
import dao.BddObject;
import java.sql.Time;
import java.sql.Date;


@Table(name = "details")
public class Details{
    
    @PrimaryKey(autoIncrement = true)
    @Column(name = "id")
    Integer id;
    @Column(name = "secteur")
    String secteur;
    @Column(name = "heure")
    Time heure;
    @Column(name = "besoin")
    Double besoin;
    @Column(name = "puissance_delivree_batterie")
    Double puissanceDelivreeBatterie;
    @Column(name = "date_details")
    Date dateDetails;
    @Column(name = "reserve_batterie")
    Double reserveBatterie;
    @Column(name = "puissance_delivree")
    Double puissanceDelivree;
    @Column
    Integer etat;

    //SETTERS AND GETTERS
        
    public String getSecteur(){
        return this.secteur;
    }
    public void setSecteur(String secteur){
        this.secteur = secteur;
    }
    public Time getHeure(){
        return this.heure;
    }
    public void setHeure(Time heure){
        this.heure = heure;
    }
    public Double getBesoin(){
        return this.besoin;
    }
    public void setBesoin(Double besoin){
        this.besoin = besoin;
    }
    public Double getPuissanceDelivreeBatterie(){
        return this.puissanceDelivreeBatterie;
    }
    public void setPuissanceDelivreeBatterie(Double puissanceDelivreeBatterie){
        this.puissanceDelivreeBatterie = puissanceDelivreeBatterie;
    }
    public Date getDateDetails(){
        return this.dateDetails;
    }
    public void setDateDetails(Date dateDetails){
        this.dateDetails = dateDetails;
    }
    public Double getReserveBatterie(){
        return this.reserveBatterie;
    }
    public void setReserveBatterie(Double reserveBatterie){
        this.reserveBatterie = reserveBatterie;
    }
    public Integer getId(){
        return this.id;
    }
    public void setId(Integer id){
        this.id = id;
    }
    public Double getPuissanceDelivree(){
        return this.puissanceDelivree;
    }
    public void setPuissanceDelivree(Double puissanceDelivree){
        this.puissanceDelivree = puissanceDelivree;
    }

    public Integer getEtat() {
        return etat;
    }

    public void setEtat(Integer etat) {
        this.etat = etat;
    }
    

    //CONSTRUCTORS

    public Details()throws Exception{}
    public Details(String secteur, Time heure, Double besoin, Double puissanceDelivreeBatterie, Date dateDetails, Double reserveBatterie, Integer id, Double puissanceDelivree) throws Exception{
        setSecteur(secteur);
        setHeure(heure);
        setBesoin(besoin);
        setPuissanceDelivreeBatterie(puissanceDelivreeBatterie);
        setDateDetails(dateDetails);
        setReserveBatterie(reserveBatterie);
        setId(id);
        setPuissanceDelivree(puissanceDelivree);
    }
    public Details(String secteur, Time heure, Integer etat, Double besoin, Double puissanceDelivreeBatterie, Date dateDetails, Double reserveBatterie, Double puissanceDelivree)  throws Exception{
        setSecteur(secteur);
        setHeure(heure);
        setBesoin(besoin);
        setPuissanceDelivreeBatterie(puissanceDelivreeBatterie);
        setDateDetails(dateDetails);
        setReserveBatterie(reserveBatterie);
        setPuissanceDelivree(puissanceDelivree);
        setEtat(etat);
    }

}
