package solaire.entity;

import annotation.PrimaryKey;
import annotation.Column;
import annotation.Table;
import dao.BddObject;
import dao.GenericDao;
import java.sql.Statement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import solaire.utils.DateTimeUtility;


@Table(name = "secteur")
public class Secteur extends BddObject{
    @PrimaryKey(name = "id_secteur", sequence = "seq_secteur", prefix = "SEC", length = 6)
    String idSecteur;
    @Column(name = "nom")
    String nom;
//    @Column(name = "id_source")
//    String source;
    
    //SETTERS AND GETTERS
        
    public String getIdSecteur(){
        return this.idSecteur;
    }
    public void setIdSecteur(String idSecteur){
        this.idSecteur = idSecteur;
    }
    public String getNom(){
        return this.nom;
    }
    public void setNom(String nom){
        this.nom = nom;
    }

//    public String getSource() {
//        return source;
//    }
//
//    public void setSource(String source) {
//        this.source = source;
//    }

    //CONSTRUCTORS

    public Secteur(){}
    public Secteur(String idSecteur, String nom){
        setIdSecteur(idSecteur);
        setNom(nom);
    }
    //METHODS
    public Double getMaxCapaciteSecteur(Connection con) throws Exception{
        Double res = 0.0;
        String condition = "id_secteur = '" + this.getIdSecteur() + "'";
        List<Salle> listBatiment = GenericDao.findWhere(con, condition, new Salle());
        for (Salle batiment : listBatiment) {
            res += batiment.getCapaciteMax();
        }
        return res; 
    }
    public List<Salle> getSalleFromSecteur(Connection con) throws Exception{
        Salle salle = new Salle();
        salle.setIdSecteur(this.getIdSecteur());
        return salle.findWhere(con);
    }
     
    public List<Integer> getPointageSecteur(Connection con, Date date) throws Exception{
        List<Integer> res = new ArrayList<>();
        String query = "SELECT * FROM v_pointage_secteur WHERE daty = '" + date.toString() + "' AND id_secteur LIKE '" + this.getIdSecteur() + "'";
        Statement stmt = con.createStatement();
//        System.out.println(query);
        ResultSet rs = stmt.executeQuery(query);
        while(rs.next()){
            res.add(rs.getInt("nombre_personne"));
        }
        return res;
    }
    
    public List<BesoinSecteur> getPointageMoyenParSecteur(Connection con, Date date) throws Exception {
        List<BesoinSecteur> res = new ArrayList<>();
//        HashMap<String, Double> needs = this.getBesoinMoyenne(con, date);
        int dow = DateTimeUtility.getDayNumberOld(date) - 1;
        String query = "SELECT heure_debut, heure_fin,AVG(nombre_personne) nombre, id_secteur "
            + " FROM v_pointage_secteur WHERE EXTRACT(DOW FROM daty) = " 
            + dow + " AND daty <> '" + date.toString() + "'"
            + " GROUP BY id_secteur, heure_debut, heure_fin ORDER BY id_secteur";
//        System.out.println(query);
        Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery(query);
        
        while(rs.next()){
//            BesoinSecteur temp = new BesoinSecteur();
//            temp.setNombrePersonneMatin(rs.getInt(1));
//            temp.setNombrePersonneApresMidi(rs.getInt(2));
//            temp.setIdSecteur(rs.getString(3));
////            temp.setPuissanceMoyenne(needs.get(temp.getIdSecteur()));
//            res.add(temp);
        }
        return res;
    }
}
