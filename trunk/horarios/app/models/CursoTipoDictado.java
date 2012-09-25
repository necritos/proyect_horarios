package models;

import play.db.jpa.GenericModel;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "curso_tipo_dictado")
public class CursoTipoDictado extends GenericModel {

    @Id
    @GeneratedValue
    @Column(name = "ctd_iId")
    public Integer ctd_iId;

    @Column(name = "ctd_iHoras")
    public Integer ctd_iHoras;

    @OneToMany(mappedBy = "cursoTipoDictado")
    public List<GrupoTipoDictado> grupoTipoDictadoList;

    @JoinColumn(name = "cur_iId", referencedColumnName = "cur_iId")
    @ManyToOne(optional = false, cascade = CascadeType.ALL)
    public Curso curso;

    @JoinColumn(name = "tid_iId", referencedColumnName = "tid_iId")
    @ManyToOne(optional = false, cascade = CascadeType.ALL)
    public TipoDictado tipoDictado;

    //@OneToMany(mappedBy = "cursoTipoDictado")
    //public List<Preferencia> preferenciaList;


    public CursoTipoDictado(Integer ctd_iHoras, Curso curso, TipoDictado tipoDictado) {
        grupoTipoDictadoList = new ArrayList<GrupoTipoDictado>();
        this.ctd_iHoras = ctd_iHoras;
        this.curso = curso;
        this.tipoDictado = tipoDictado;
    }

    public List<GrupoTipoDictado> getGrupoTipoDictadoList() {
        grupoTipoDictadoList = GrupoTipoDictado.find("byCursoTipoDictado",this).fetch();
        return grupoTipoDictadoList;
    }
}
