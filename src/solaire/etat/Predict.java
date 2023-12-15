/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package solaire.etat;

import java.sql.Date;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import solaire.entity.BesoinSecteur;
import solaire.entity.Meteo;
import solaire.entity.SourceSolaire;

/**
 *
 * @author Mamisoa
 */
public class Predict {
    Date datePrediction;
    EtatSolaire[] etat;
    //GETTERS & SETTERS

    public Date getDatePrediction() {
        return datePrediction;
    }

    public void setDatePrediction(Date datePrediction) {
        this.datePrediction = datePrediction;
    }

    public EtatSolaire[] getEtat() {
        return etat;
    }

    public void setEtat(EtatSolaire[] etat) {
        this.etat = etat;
    }
    
    
    //CONSRTUCTORS
    public Predict(){}
    public Predict(Date date){
        this.setDatePrediction(date);
    }
    
    //METHODS
    public void predict(Connection con, int pas) throws Exception{
        List<BesoinSecteur> lst = new BesoinSecteur().getBesoinSecteurMoyenneParSecteur(con, this.getDatePrediction());
//        List<BesoinSecteur> lst = new BesoinSecteur().getBesoinSecteurMoyenneParSecteur2(con, this.getDatePrediction());
        SourceSolaire source = new SourceSolaire();
        List<SourceSolaire> list = source.findWhere(con, "1 = 1 ORDER BY id_secteur");
        EtatSolaire[] state = new EtatSolaire[lst.size()];
        int j = 0;
        List<Meteo> meteo = new Meteo().getMeteoDu(con, this.getDatePrediction());
        for (int i = 0; i < lst.size(); i++) {
            if(lst.get(i).getIdSecteur().equals(list.get(i).getIdSecteur())){
                List<Integer> pointage = new ArrayList<>();
                pointage.add(lst.get(i).getNombrePersonneMatin());
                pointage.add(lst.get(i).getNombrePersonneApresMidi());
                state[j] = list.get(i).getEtatSolaire(meteo, pas, this.getDatePrediction(), lst.get(i).getPuissanceMoyenne(), pointage);
                j++;
            }
        }
        this.setEtat(state);
    }
}
