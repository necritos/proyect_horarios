package models;

import play.db.jpa.GenericModel;

import javax.persistence.*;

@Entity
@Table(name = "historial")
public class Historial extends GenericModel {

    @Id
    @GeneratedValue
    @Column(name = "his_iId")
    public Integer his_iId;

    @Column(name = "his_sdescripcion")
    public String his_sdescripcion;

    @Column(name = "his_iIdciclo")
    public String his_iIdciclo;

    @Column(name = "his_iIdgrupo")
    public Integer his_iIdgrupo;

    public Historial(String his_sdescripcion, String his_iIdciclo, Integer his_iIdgrupo) {
        this.his_sdescripcion = his_sdescripcion;
        this.his_iIdciclo = his_iIdciclo;
        this.his_iIdgrupo = his_iIdgrupo;
    }
}

