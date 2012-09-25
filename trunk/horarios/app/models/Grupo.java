package models;

import play.db.jpa.GenericModel;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "grupo")
public class Grupo extends GenericModel {

    @Id
    @GeneratedValue
    @Column(name = "gru_iId")
    public Integer gru_iId;

    @Column(name = "gru_iNumGrupo")
    public Integer gru_iNumGrupo;

    @Column(name = "gru_iMaxAlumnos")
    public Integer gru_iMaxAlumnos;

    @JoinColumn(name = "scu_iId", referencedColumnName = "scu_iId")
    @ManyToOne(cascade = CascadeType.ALL)
    public SemestreCurso semestreCurso;

    @OneToMany(mappedBy = "grupo")
    public List<GrupoTipoDictado> grupoTipoDictadoList;

    public Grupo(Integer gru_iNumGrupo, Integer gru_iMaxAlumnos, SemestreCurso semestreCurso) {
        grupoTipoDictadoList = new ArrayList<GrupoTipoDictado>();
        this.gru_iNumGrupo = gru_iNumGrupo;
        this.gru_iMaxAlumnos = gru_iMaxAlumnos;
        this.semestreCurso = semestreCurso;
    }

    public List<GrupoTipoDictado> getGrupoTipoDictadoList() {
        grupoTipoDictadoList = GrupoTipoDictado.find("byGrupo",this).fetch();
        return grupoTipoDictadoList;
    }
}
