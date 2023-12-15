/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package testsolaire;

import dao.DbConnection;
import dao.GenericDao;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import solaire.entity.Coupure;
import solaire.entity.SourceSolaire;
import solaire.etat.EtatSolaire;
import utils.ObjectUtility;

/**
 *
 * @author Mamisoa
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws SQLException {
        // TODO code application logic her
        Connection con = null;
        try {
            con = DbConnection.connect();
//            SourceSolaire source = new SourceSolaire();
//            source.setIdSource("SRC00001");
//            source = source.findById(con);
//            EtatSolaire etat = new EtatSolaire();
//            Date date = Date.valueOf("2023-11-2");

//            List<Meteo> meteo = new Meteo().getMeteoDu(con, date);
//            int[] pointage = source.getSecteur(con).getPointageSecteur(con, date);
//            EtatSolaire etat2 = source.getEtatSolaire(meteo, 1, date, 60.0, pointage);
//            for ( Details details : etat2.getDetails()) { 
//                System.out.println(
//                        "reserve batterie = " + details.getReserveBatterie()
//                        + " puissance delivree = " + details.getPuissanceDelivree()
//                        + " puissance delivree batterie = " + details.getPuissanceDelivreeBatterie()
//                        + " besoin = " + details.getBesoin()
//                        + " heure = " + details.getHeure()
//                        + " etat = " + details.getEtat()
//                );
//            System.out.println("--------------------------------------------------------------------------------------------------------------------------------------------------------");
//            }
//            System.out.println("heure coupure = " + etat2.getBesoin().getHeureCoupure());

//            Time time = Time.valueOf("15:07:00");
//            EtatSolaire besoin= source.getEtatSolaireMoyenne(con, date, time);
//            GenericDao.save(con, besoin.getBesoin());
//            for ( Details details : besoin.getDetails()) {
//                System.out.println(
//                        "reserve batterie = " + details.getReserveBatterie()
//                        + " puissance delivree = " + details.getPuissanceDelivree()
//                        + " puissance delivree batterie = " + details.getPuissanceDelivreeBatterie()
//                        + " besoin = " + details.getBesoin()
//                        + " etat = " + details.getEtat()
//                        + " heure = " + details.getHeure()
//                );
//            System.out.println("----------------------------------------------------------------------------");
//            }
//            System.out.println("heure coupure = " + besoin.getHeureCoupure().toString());
//            System.out.println("besoin moyenne = " + besoin.getBesoin().getPuissanceMoyenne());
           
            String condition1 = "id_secteur = 'SEC001'";
//            String condition2 = "id_secteur = 'SEC002'";
            SourceSolaire src1 = (SourceSolaire)new SourceSolaire().findWhere(con, condition1).get(0);
//            SourceSolaire src2 = (SourceSolaire)new SourceSolaire().findWhere(con, condition2).get(0);
//
            List<Coupure> lstCoupure1 = new Coupure().findWhere(con, condition1);
//            List<Coupure> lstCoupure2 = new Coupure().findWhere(con, condition2);
//            
//            
//            EtatSolaire etat = src1.getEtatSolaireMoyenne(con, lstCoupure1.get(0).getDateJour(), lstCoupure1.get(0).getHeure());
            for (Coupure coupure : lstCoupure1) {
                EtatSolaire etat1 = src1.getEtatSolaireMoyenne(con, coupure.getDateJour(), coupure.getHeure());
//                System.out.println(
//                        "besoin moyenne = " + etat1.getBesoin().getPuissanceMoyenne()
//                        + " date = " + etat1.getBesoin().getDaty()
//                        + " heure = " + etat1.getBesoin().getHeureCoupure()
//                );
//                System.out.println(ObjectUtility.formatNumber(etat1.getBesoin().getPuissanceMoyenne(), 2, "."));
                GenericDao.save(con, etat1.getBesoin());
                System.out.println("Saved succesfully");
//                System.out.println("----------------------------------------------------------------------------------");
            } 
            System.out.println("SECTEUR 1 FINISHED");
//            for (Coupure coupure2 : lstCoupure2) {
//                EtatSolaire etat2 = src2.getEtatSolaireMoyenne(con, coupure2.getDateJour(), coupure2.getHeure());
////                System.out.println(etat2.getBesoin().getPuissanceMoyenne());
//                GenericDao.save(con, etat2.getBesoin());
//                System.out.println("Saved succesfully");
//            }
//            System.out.println("SECTEUR 2 FINISHED");
            

//            BesoinSecteur besSecteur = new BesoinSecteur();
//            Predict predict = new Predict(Date.valueOf("2023-12-04"));
//            predict.predict(con, 1);
//            for (EtatSolaire state : predict.getEtat()) {
//                System.out.println(state.getBesoin().getHeureCoupure());
//            }

//            Secteur sect = new Secteur();
//            sect.setIdSecteur("SEC001");
//            sect = sect.findById(con);
//            int[] pointage = sect.getPointageSecteur(con, date);
//            System.out.println(pointage[0] + " " + pointage[1]);
//            List<Salle> listSalle = GenericDao.findAll(con, new Salle());
//            System.out.println(listSalle.get(0).getNom());
//            List<Meteo> listMeteo = GenericDao.findAll(con, new Meteo());
//            System.out.println(listMeteo.get(0).getLuminosite());

//            List<BesoinSecteur> lstBesoin = GenericDao.findAll(con, new BesoinSecteur());
//            for (BesoinSecteur besoinSecteur : lstBesoin) {
//                System.out.println(
//                        ObjectUtility.formatNumber(besoinSecteur.getPuissanceMoyenne(), 2, ".")
//                        + " date = " + besoinSecteur.getDaty()
//                        + " heure coupure = " + besoinSecteur.getHeureCoupure()
//                        + " secteur = " + besoinSecteur.getIdSecteur()                        
//                );
//            }
//              System.out.println(DateTimeUtility.getDayOfWeek(Date.valueOf("2023-10-04")));
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            con.close();
        }
    }
    
}
