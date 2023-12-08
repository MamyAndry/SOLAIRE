package solaire.entity;

import annotation.PrimaryKey;
import annotation.Column;
import annotation.Table;
import dao.BddObject;
import java.sql.Date;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import solaire.utils.DateTimeUtility;


@Table(name = "besoin_secteur")
public class BesoinSecteur{
    
    @PrimaryKey(name = "id_besoin", autoIncrement = true)
    Integer idBesoin;
    @Column(name = "nombre_personne_matin")
    Integer nombrePersonneMatin;
    @Column(name = "nombre_personne_apres_midi")
    Integer nombrePersonneApresMidi;
    @Column(name = "id_secteur")
    String idSecteur;
    @Column(name = "puissance_moyenne")
    Double puissanceMoyenne;
    @Column(name = "daty")
    Date daty;

    //SETTERS AND GETTERS
        
    public Integer getNombrePersonneMatin(){
        return this.nombrePersonneMatin;
    }
    public void setNombrePersonneMatin(Integer nombrePersonneMatin){
        this.nombrePersonneMatin = nombrePersonneMatin;
    }
    public Integer getNombrePersonneApresMidi(){
        return this.nombrePersonneApresMidi;
    }
    public void setNombrePersonneApresMidi(Integer nombrePersonneApresMidi){
        this.nombrePersonneApresMidi = nombrePersonneApresMidi;
    }
    public String getIdSecteur(){
        return this.idSecteur;
    }
    public void setIdSecteur(String idSecteur){
        this.idSecteur = idSecteur;
    }
    public Double getPuissanceMoyenne(){
        return this.puissanceMoyenne;
    }
    public void setPuissanceMoyenne(Double puissanceMoyenne){
        this.puissanceMoyenne = puissanceMoyenne;
    }
    public Date getDaty(){
        return this.daty;
    }
    public void setDaty(Date daty){
        this.daty = daty;
    }
    public Integer getIdBesoin(){
        return this.idBesoin;
    }
    public void setIdBesoin(Integer idBesoin){
        this.idBesoin = idBesoin;
    }

//CONSTRUCTORS

    public BesoinSecteur() throws Exception{}
    public BesoinSecteur(Integer nombrePersonneMatin, Integer nombrePersonneApresMidi, String idSecteur, Double puissanceMoyenne, Date daty, Integer idBesoin) throws Exception{
        setNombrePersonneMatin(nombrePersonneMatin);
        setNombrePersonneApresMidi(nombrePersonneApresMidi);
        setIdSecteur(idSecteur);
        setPuissanceMoyenne(puissanceMoyenne);
        setDaty(daty);
        setIdBesoin(idBesoin);
    }
    public BesoinSecteur(Integer nombrePersonneMatin, Integer nombrePersonneApresMidi, String idSecteur, Double puissanceMoyenne, Date daty) throws Exception{
        setNombrePersonneMatin(nombrePersonneMatin);
        setNombrePersonneApresMidi(nombrePersonneApresMidi);
        setIdSecteur(idSecteur);
        setPuissanceMoyenne(puissanceMoyenne);
        setDaty(daty);
    }
    
//METHODS
    public HashMap<String, Double> getBesoinMoyenne(Connection con) throws Exception{
        HashMap<String, Double> res = new HashMap<>();
        String query = "SELECT id_secteur, AVG(puissance_moyenne) besoin FROM besoin_secteur GROUP BY id_secteur ORDER BY id_secteur";
        Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery(query);
        while(rs.next()){
            res.put(rs.getString(1), rs.getDouble(2));
        }
        return res;
    }
    public List<BesoinSecteur> getBesoinSecteurMoyenneParSecteur(Connection con, Date date) throws Exception {
        List<BesoinSecteur> res = new ArrayList<>();
        HashMap<String, Double> needs = this.getBesoinMoyenne(con);
        int dow = DateTimeUtility.getDayNumberOld(date) - 1;
        String query = "SELECT AVG(nombre_personne_matin) nombre_matin, AVG(nombre_personne_apres_midi) nombre_apres_midi, id_secteur "
            + "FROM Besoin_secteur WHERE EXTRACT(DOW FROM daty) = " 
            + dow + " GROUP BY id_secteur ORDER BY id_secteur";
        Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery(query);
        
        while(rs.next()){
            BesoinSecteur temp = new BesoinSecteur();
            temp.setNombrePersonneMatin(rs.getInt(1));
            temp.setNombrePersonneApresMidi(rs.getInt(2));
            temp.setIdSecteur(rs.getString(3));
            temp.setPuissanceMoyenne(needs.get(temp.getIdSecteur()));
            res.add(temp);
        }
        return res;
    }
}
