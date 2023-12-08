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
import java.util.List;


@Table(name = "secteur")
public class Secteur extends BddObject{
    @PrimaryKey(name = "id_secteur", sequence = "seq_secteur", prefix = "SECT")
    String idSecteur;
    @Column(name = "nom")
    String nom;

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

    //CONSTRUCTORS

    public Secteur() throws Exception{}
    public Secteur(String idSecteur, String nom) throws Exception{
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
     
    public int[] getPointageSecteur(Connection con, Date date) throws Exception{
        int[] res = new int[2];
        String query = "SELECT * FROM v_pointage_secteur WHERE daty = '" + date.toString() + "' AND id_secteur LIKE '" + this.getIdSecteur() + "'";
        Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery(query);
        int i = 0;
        while(rs.next()){
            res[i] = rs.getInt("nombre_personne");
            i++;
        }
        return res;
    }
    

}
