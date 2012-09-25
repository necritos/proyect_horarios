package models;

import java.sql.Time;
import play.db.jpa.GenericModel;

import javax.persistence.*;
import java.util.Date;
@Entity
@Table(name = "horario")
public class Horario extends GenericModel {

    @Id
    @GeneratedValue
    @Column(name = "hor_iId")
    public Integer hor_iId;

    @Column(name = "hor_tIni")
    public Integer hor_tIni;

    @Column(name = "hor_tFin")
    public Integer hor_tFin;

    @Column(name = "hor_vDia")
    public String hor_vDia;

    @JoinColumn(name = "gtd_iId", referencedColumnName = "gtd_iId")
    @ManyToOne(cascade = CascadeType.ALL)
    public GrupoTipoDictado grupoTipoDictado;

    public Horario(Integer hor_tIni, Integer hor_tFin, String hor_vDia, GrupoTipoDictado grupoTipoDictado) {
        this.hor_tIni = hor_tIni;
        this.hor_tFin = hor_tFin;
        this.hor_vDia = hor_vDia;
        this.grupoTipoDictado = grupoTipoDictado;
    }
}
