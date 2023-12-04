/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package solaire.etat;

import dao.GenericDao;
import java.sql.Date;
import java.sql.Connection;
import java.util.List;
import solaire.entity.BesoinSecteur;
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
        List<BesoinSecteur> lst = new BesoinSecteur().getBesoinSecteurMoyenne(con, this.getDatePrediction());
        List<SourceSolaire> list = GenericDao.findWhere(con, "1 = 1 ORDER BY id_secteur",new SourceSolaire());
        System.out.println(lst.size());
        EtatSolaire[] state = new EtatSolaire[lst.size()];
        int j = 0;
        for (int i = 0; i < lst.size(); i++) {
            if(lst.get(i).getIdSecteur().equals(list.get(i).getIdSecteur())){
                System.out.println("YESSSS");
                int[] pointage = new int[2];
                pointage[0] = lst.get(i).getNombrePersonneMatin();
                pointage[1] = lst.get(i).getNombrePersonneApresMidi();
                System.out.println(lst.get(i).getPuissanceMoyenne());
                state[j] = list.get(i).getEtatSolaire(con, pas, this.getDatePrediction(), lst.get(i).getPuissanceMoyenne(), pointage);
                j++;
            }
        }
        this.setEtat(state);
    }
}
