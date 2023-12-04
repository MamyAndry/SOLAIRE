package solaire.entity;

import annotation.PrimaryKey;
import annotation.Column;
import annotation.Table;
import dao.GenericDao;
import java.sql.Connection;
import java.sql.Date;
import java.sql.Time;
import java.util.List;
import solaire.etat.EtatSolaire;
import solaire.utils.DateTimeUtility;


@Table(name = "source_solaire")
public class SourceSolaire{
    
    @PrimaryKey(sequence = "seq_source", prefix = "SRC")
    @Column(name = "id_source")
    String idSource;
    @Column(name = "id_secteur")
    String idSecteur;
    @Column(name = "reserve_max_batterie")
    Double reserveMaxBatterie;
    @Column(name = "puissance_max")
    Double puissanceMax;

    //SETTERS AND GETTERS
        
    public String getIdSecteur(){
        return this.idSecteur;
    }
    public void setIdSecteur(String idSecteur){
        this.idSecteur = idSecteur;
    }
    public Double getReserveMaxBatterie(){
        return this.reserveMaxBatterie;
    }
    public void setReserveMaxBatterie(Double reserveMaxBatterie){
        this.reserveMaxBatterie = reserveMaxBatterie;
    }
    public String getIdSource(){
        return this.idSource;
    }
    public void setIdSource(String idSource){
        this.idSource = idSource;
    }
    public Double getPuissanceMax(){
        return this.puissanceMax;
    }
    public void setPuissanceMax(Double puissanceMax){
        this.puissanceMax = puissanceMax;
    }

    //CONSTRUCTORS
    public SourceSolaire(){}
    public SourceSolaire(String idSecteur, Double reserveMaxBatterie, String idSource, Double puissanceMax){
        setIdSecteur(idSecteur);
        setReserveMaxBatterie(reserveMaxBatterie);
        setIdSource(idSource);
        setPuissanceMax(puissanceMax);
    }

    //METHODS
    public Secteur getSecteur(Connection con) throws Exception{
        Secteur temp = new Secteur().findById(con, this.getIdSecteur());
        return temp;
    }
    public Double getLimiteBatterie(){
        return this.getReserveMaxBatterie()/2;
    }
    public Double getPuissanceDelivreeMomentT(List<Meteo> lstMeteo, Time time, int pas){
        Double res = 0.0;
        double duration = ((60 / pas) / 60.0);
        for (Meteo meteo : lstMeteo) {
            if(DateTimeUtility.isTimeBeetween(time, meteo.getHeureDebut(), meteo.getHeureFin()))
                res = (((getPuissanceMax()* duration) * meteo.getLuminosite()) / 10);
        }
        return res;
    }
    
    public boolean checkPourcentageBatterie( double reserveBatterie){
        return reserveBatterie < this.getLimiteBatterie();
    }
    
    public Double getPuissanceDelivreeBatterieMomentT(List<Meteo> lstMeteo, Time time, int pas, Double besoin) throws Exception{
        Double genere = getPuissanceDelivreeMomentT(lstMeteo, time, pas);
        Double res = 0.0;
        if(genere < besoin)
            res = besoin - genere;
        return res;
    }
    
    
    
    public EtatSolaire getEtatSolaire(Connection con, int pas, Date date, Double besoinMoyenne, int[] pointage) throws Exception{
        List<Meteo> meteo = new Meteo().getMeteoDu(con, date);
        
        double duration = ((60 / pas) / 60.0);
        
        double reserveBatterie = this.getReserveMaxBatterie();
        
        if(meteo.isEmpty())throw new Exception("Pas de donnee meteo pour cette date");
        
        Details[] details = new Details[(meteo.size() * pas)];
        
        if(pas > 1) details = new Details[(meteo.size() * pas) - ( pas-1)];
        
        Double besoin = besoinMoyenne * pointage[0] * duration;
        
        Time end = Time.valueOf("17:00:00");
        Time time = Time.valueOf("08:00:00");
        Time coupure = end;
        String etat = "LUMIERE";
        
        Double puissanceDelivreeBatterie = this.getPuissanceDelivreeBatterieMomentT(meteo, time, pas, besoin);
        
        details[0] = new Details(this.getIdSecteur(), time, etat, besoin, puissanceDelivreeBatterie, date, reserveBatterie - puissanceDelivreeBatterie, this.getPuissanceDelivreeMomentT(meteo, time, pas));
        
        for(int i = 1; i < details.length; i++){
            time = DateTimeUtility.addMinutes(time, (60 / pas));
            if(time.after(end)) break;
            if(time.after(Time.valueOf("12:00:00"))){
                besoin = besoinMoyenne * pointage[1] * duration;
            }
            if(this.checkPourcentageBatterie(reserveBatterie)){
                puissanceDelivreeBatterie = 0.0;
                etat = "COUPURE";
            }
            reserveBatterie -= puissanceDelivreeBatterie;
            details[i] = new Details(this.getIdSecteur(), time, etat, besoin, puissanceDelivreeBatterie, date, reserveBatterie, this.getPuissanceDelivreeMomentT(meteo, time, pas));

            if(details[i].getEtat().equals("COUPURE") && details[i-1].getEtat().equals("LUMIERE")){
                coupure = details[i].getHeure();
            }   
        }
        
        BesoinSecteur  besoinSecteur = new BesoinSecteur(pointage[0], pointage[1], this.getIdSecteur(), besoinMoyenne, date);
        
        return new EtatSolaire(details, besoinSecteur, coupure);
    }   
}
