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
    public Spring his_sdescripcion;

    @Column(name = "his_iIdHorario")
    public Integer his_iIdHorario;
}

