package solaire.entity;

import annotation.PrimaryKey;
import annotation.Column;
import annotation.Table;
import java.sql.Date;
import java.sql.Time;


@Table(name = "pointage")
public class Pointage{
        @PrimaryKey(autoIncrement = true)
	@Column(name = "id")
	Integer id;
	@Column(name = "heure_fin")
	Time heureFin;
	@Column(name = "id_batiment")
	String idBatiment;
	@Column(name = "daty")
	Date daty;
	@Column(name = "heure_debut")
	Time heureDebut;
	@Column(name = "nombre_personne")
	Integer nombrePersonne;

    //SETTERS AND GETTERS
        
	public Time getHeureFin(){
            return this.heureFin;
	}
	public void setHeureFin(Time heureFin){
            this.heureFin = heureFin;
	}
	public String getIdBatiment(){
            return this.idBatiment;
	}
	public void setIdBatiment(String idBatiment){
            this.idBatiment = idBatiment;
	}
	public Integer getId(){
            return this.id;
	}
	public void setId(Integer id){
            this.id = id;
	}
	public Date getDaty(){
            return this.daty;
	}
	public void setDaty(Date daty){
            this.daty = daty;
	}
	public Time getHeureDebut(){
            return this.heureDebut;
	}
	public void setHeureDebut(Time heureDebut){
            this.heureDebut = heureDebut;
	}
	public Integer getNombrePersonne(){
            return this.nombrePersonne;
	}
	public void setNombrePersonne(Integer nombrePersonne){
            this.nombrePersonne = nombrePersonne;
	}

    //CONSTRUCTORS

	public Pointage(){}
	public Pointage(Time heureFin, String idBatiment, Integer id, Date daty, Time heureDebut, Integer nombrePersonne){
		setHeureFin(heureFin);
		setIdBatiment(idBatiment);
		setId(id);
		setDaty(daty);
		setHeureDebut(heureDebut);
		setNombrePersonne(nombrePersonne);
	}

}
