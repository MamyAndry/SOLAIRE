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
import java.sql.Time;
import java.util.List;
import solaire.utils.DateTimeUtility;


@Table(name = "secteur")
public class Secteur extends BddObject<Secteur>{
    @PrimaryKey(sequence = "seq_secteur", prefix = "SECT")
    @Column(name = "id_secteur")
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

    public Secteur(){}
    public Secteur(String idSecteur, String nom){
        setIdSecteur(idSecteur);
        setNom(nom);
    }
    //METHODS
    public Double getMaxCapaciteSecteur(Connection con) throws Exception{
        Double res = 0.0;
        String condition = "id_secteur = '" + this.getIdSecteur() + "'";
        List<Batiment> listBatiment = GenericDao.findWhere(con, condition, new Batiment());
        for (Batiment batiment : listBatiment) {
            res += batiment.getCapaciteMax();
        }
        return res; 
    }
    public List<Batiment> getBatimentsFromSecteur(Connection con) throws Exception{
        Batiment bat = new Batiment();
        bat.setIdSecteur(this.getIdSecteur());
        return GenericDao.findWhere(con, bat);
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
