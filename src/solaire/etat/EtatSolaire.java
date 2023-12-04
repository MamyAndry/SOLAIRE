/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package solaire.etat;

import java.sql.Connection;
import dao.DbConnection;
import solaire.entity.BesoinSecteur;
import solaire.entity.Details;
import java.sql.Time;
import java.sql.Date;
import java.time.LocalTime;
import solaire.entity.SourceSolaire;

/**
 *
 * @author Mamisoa
 */
public class EtatSolaire {
    Details[] details;
    BesoinSecteur besoin;
    Time heureCoupure;
    SourceSolaire source;

    //GETTERS & SETTERS
    public Details[] getDetails() {
        return details;
    }

    public void setDetails(Details[] details) {
        this.details = details;
    }

    public BesoinSecteur getBesoin() {
        return besoin;
    }

    public void setBesoin(BesoinSecteur besoin) {
        this.besoin = besoin;
    }

    public Time getHeureCoupure() {
        return heureCoupure;
    }

    public void setHeureCoupure(Time coupure) {
        this.heureCoupure = coupure;
    }

    public SourceSolaire getSource() {
        return source;
    }

    public void setSource(SourceSolaire source) {
        this.source = source;
    }
    
    //CONSTRUCTORS
    public EtatSolaire(){}
    public EtatSolaire(SourceSolaire source) {
        this.setSource(source);
    }
    public EtatSolaire(Details[] details, BesoinSecteur besoin) {
        this.setDetails(details);
        this.setBesoin(besoin);
    }
    public EtatSolaire(Details[] details, BesoinSecteur besoin, Time coupure) {
        this.setDetails(details);
        this.setBesoin(besoin);
        this.setHeureCoupure(coupure);
    }
    
    //METHODS
    public int checkTime(Time time){
        LocalTime local = time.toLocalTime();
        if(local.getMinute() != 0)
            return 60 / local.getMinute();
        return 1;
    }
    
    public EtatSolaire getEtatSolaireMoyenne(Connection con, Date date, Time time) throws Exception{
        boolean state = false;
        try {
            if(con == null){
                con = DbConnection.connect();
                state = true;
            }
            Double needs = 1.0;
            Time temp = Time.valueOf("08:00:00");
            EtatSolaire etat = null;
            int[] pointage = this.getSource().getSecteur(con).getPointageSecteur(con, date);
            int pas = checkTime(time);
            while(temp.compareTo(time) != 0 || temp.after(time)){
                needs += 0.1;
                etat = this.getSource().getEtatSolaire(con, pas, date, needs, pointage);
                temp = etat.getHeureCoupure();
            }
            return etat;
        } finally {
            if(state == true)con.close();
        }
    }
       
}