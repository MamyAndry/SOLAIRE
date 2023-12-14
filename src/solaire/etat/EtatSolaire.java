/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package solaire.etat;

import solaire.entity.BesoinSecteur;
import solaire.entity.Details;
import java.sql.Time;

/**
 *
 * @author Mamisoa
 */
public class EtatSolaire {
    Details[] details;
    BesoinSecteur besoin;
//    Time heureCoupure;
//    SourceSolaire source;

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

//    public Time getHeureCoupure() {
//        return heureCoupure;
//    }
//
//    public void setHeureCoupure(Time coupure) {
//        this.heureCoupure = coupure;
//    }

//    public SourceSolaire getSource() {
//        return source;
//    }
//
//    public void setSource(SourceSolaire source) {
//        this.source = source;
//    }
//    
    //CONSTRUCTORS
    public EtatSolaire(){}
//    public EtatSolaire(SourceSolaire source) {
//        this.setSource(source);
//    }
    public EtatSolaire(Details[] details, BesoinSecteur besoin) {
        this.setDetails(details);
        this.setBesoin(besoin);
    }
//    public EtatSolaire(Details[] details, BesoinSecteur besoin) {
//        this.setDetails(details);
//        this.setBesoin(besoin);
//        this.setHeureCoupure(coupure);
//    }
    
    //METHODS

       
}