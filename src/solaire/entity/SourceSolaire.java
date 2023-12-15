package solaire.entity;

import annotation.PrimaryKey;
import annotation.Column;
import annotation.Table;
import dao.BddObject;
import dao.DbConnection;
import java.sql.Connection;
import java.sql.Date;
import java.sql.Time;
import java.time.LocalTime;
import java.util.List;
import solaire.etat.EtatSolaire;
import solaire.utils.DateTimeUtility;


@Table(name = "source_solaire")
public class SourceSolaire extends BddObject{
    
    @PrimaryKey(name = "id_source", sequence = "seq_source", prefix = "SRC")
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
    public SourceSolaire() throws Exception{}
    public SourceSolaire(String idSecteur, Double reserveMaxBatterie, String idSource, Double puissanceMax) throws Exception{
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
            if(DateTimeUtility.isTimeBeetween(time, meteo.getHeureDebut(), meteo.getHeureFin())){
                res = (getPuissanceMax() *(meteo.getLuminosite() / 10)) * duration;
            }
        }
        return res;
    }
    
    public boolean checkPourcentageBatterie( double reserveBatterie){
        return reserveBatterie < this.getLimiteBatterie();
    }
    
    public double getPuissanceDelivreeBatterieMomentT(List<Meteo> lstMeteo, Time time, int pas, Double besoin) throws Exception{
        Double genere = getPuissanceDelivreeMomentT(lstMeteo, time, pas);
        double res = 0.0;
        if(genere < besoin)
            res = besoin - genere;
        return res;
    }
    public double getPuissanceDelivreeBatterieMomentT(double genere, double besoin) throws Exception{
        double res = 0.0;
        if(genere < besoin)
            res = besoin - genere;
        return res;
    }
    public double recharger(double genere, double besoin, double reserveBatterie) throws Exception{
        double res = reserveBatterie;
        double puissanceDelivree = this.getPuissanceDelivreeBatterieMomentT(genere, besoin);
        if(puissanceDelivree == 0){
           double diff = genere - besoin;
           res = res + diff;
           if(res > this.getReserveMaxBatterie())
               res = this.getReserveMaxBatterie();
        }
        return res;
    }
    
    
    
    public EtatSolaire getEtatSolaire(List<Meteo> meteo, int pas, Date date, Double besoinMoyenne, List<Integer> pointage) throws Exception{
        double duration = (60 / pas) / 60.0;
        double reserveBatterie = this.getReserveMaxBatterie();
        Details[] details = new Details[(meteo.size() * pas) - (pas)];
        Double besoin = besoinMoyenne * pointage.get(0) * duration; 
        Time end = Time.valueOf("18:00:00");
        Time time = Time.valueOf("08:00:00");
        Time coupure = end;
        int etat = 1;
        double genere = this.getPuissanceDelivreeMomentT(meteo, time, pas);
        double puissanceDelivreeBatterie = this.getPuissanceDelivreeBatterieMomentT(genere, besoin);
        details[0] = new Details(this.getIdSecteur(), time, etat, besoin, puissanceDelivreeBatterie, date, reserveBatterie, genere);
        reserveBatterie -= puissanceDelivreeBatterie;
        for(int i = 1; i < details.length; i++){
            time = DateTimeUtility.addMinutes(time, (60 / pas));
            genere = this.getPuissanceDelivreeMomentT(meteo, time, pas);
            if(time.after(Time.valueOf("11:59:00")) && time.before(Time.valueOf("14:00:00")))
                time = Time.valueOf("14:00:00");
            if(time.after(Time.valueOf("12:00:00"))){
                besoin = besoinMoyenne * pointage.get(1) * duration;
            }
            puissanceDelivreeBatterie = this.getPuissanceDelivreeBatterieMomentT(genere, besoin);
            if(this.checkPourcentageBatterie(reserveBatterie)){
                puissanceDelivreeBatterie = 0.0;
                etat = 0;
            }
            reserveBatterie = this.recharger(genere, besoin, reserveBatterie);
            details[i] = new Details(this.getIdSecteur(), time, etat, besoin, puissanceDelivreeBatterie, date, reserveBatterie, genere);
            reserveBatterie -= puissanceDelivreeBatterie;
            
            if(details[i].getEtat() == 0 && details[i-1].getEtat() == 1){
                coupure = details[i].getHeure();
            }   
        }       
        BesoinSecteur  besoinSecteur = new BesoinSecteur(pointage.get(0), pointage.get(1), this.getIdSecteur(), besoinMoyenne, date, coupure);
        return new EtatSolaire(details, besoinSecteur);
    } 
    
    public int checkTime(Time time){
        LocalTime local = time.toLocalTime();
        if(local.getMinute() != 0)
            return 60 / local.getMinute();
        return 1;
    }
//    public EtatSolaire getEtatSolaireMoyenne(Connection con, Date date, Time time) throws Exception{
//        boolean state = false;
//        try {
//            if(con == null){
//                con = DbConnection.connect();
//                state = true;
//            }
//            Time temp = Time.valueOf("08:00:00");
//            EtatSolaire etat = null;
//            List<Meteo> meteo = new Meteo().getMeteoDu(con, date);
//            int[] pointage = this.getSecteur(con).getPointageSecteur(con, date);
//            double needs = 1.0;
//            while(temp.compareTo(time) != 0){
//                needs += 0.00001;
//                etat = this.getEtatSolaire(meteo, 60, date, needs, pointage);
//                temp = etat.getBesoin().getHeureCoupure();
//            }
//            return etat;
//        } finally {
//            if(state == true)con.close();
//        }
//    }    
        public EtatSolaire getEtatSolaireMoyenneDichotomy(Connection con, Date date, Time time) throws Exception{
        boolean state = false;
        try {
            if(con == null){
                con = DbConnection.connect();
                state = true;
            }
            Time temp = Time.valueOf("08:00:00");
            EtatSolaire etat = null;
            List<Meteo> meteo = new Meteo().getMeteoDu(con, date);
            List<Integer> pointage = this.getSecteur(con).getPointageSecteur(con, date);
            double needs = 60;
            while(temp.compareTo(time) != 0){
                etat = this.getEtatSolaire(meteo, 60, date, needs, pointage);
                temp = etat.getBesoin().getHeureCoupure();
                if (temp.after(time)) {
                    needs += (needs/2);
                }else{
                    needs -= (needs/2);
                }
            }
            return etat;
        } finally {
            if(state == true)con.close();
        }
    }    
    public EtatSolaire getEtatSolaireMoyenne(Connection con, Date date, Time time) throws Exception{
        boolean state = false;
        try {
            if(con == null){
                con = DbConnection.connect();
                state = true;
            }
            Time temp = Time.valueOf("08:00:00");
            EtatSolaire etat = null;
            List<Meteo> meteo = new Meteo().getMeteoDu(con, date);
            List<Integer> pointage = this.getSecteur(con).getPointageSecteur(con, date);
            double low = 0.0;
            double high = this.getPuissanceMax();
            double mid = 0.0;
            while( high - low >= 1e-6 ){
                mid = (high + low) / 2;
                etat = this.getEtatSolaire(meteo, 60, date, mid, pointage);
                temp = etat.getBesoin().getHeureCoupure();
                if(time.before(temp)){
                    low = mid;
                }else{
                    high = mid;
                }
                if (time.compareTo(temp) == 0) {
                    return etat;
                }
            }
            return etat;
        } finally {
            if(state == true)con.close();
        }
    }
}
