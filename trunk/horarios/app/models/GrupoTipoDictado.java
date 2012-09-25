package models;

import play.db.jpa.GenericModel;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "grupo_tipo_dictado")
public class GrupoTipoDictado extends GenericModel {

    @Id
    @GeneratedValue
    @Column(name = "gtd_iId")
    public Integer gtd_iId;

    @Column(name = "gtd_bAprobado")
    public Boolean gtd_bAprobado;

    //@OneToMany(mappedBy = "grupoTipoDictado", cascade = CascadeType.ALL)
    //public List<Horario> horarios;

    //@JoinColumn(name = "doc_iId", referencedColumnName = "doc_iId")
    //@ManyToOne
    //public Docente docente;

    @JoinColumn(name = "gru_iId", referencedColumnName = "gru_iId")
    @ManyToOne(cascade =  CascadeType.ALL)
    public Grupo grupo;

    @JoinColumn(name = "ctd_iId", referencedColumnName = "ctd_iId")
    @ManyToOne(cascade =  CascadeType.ALL)
    public CursoTipoDictado cursoTipoDictado;

    public GrupoTipoDictado(Boolean gtd_bAprobado, Grupo grupo, CursoTipoDictado cursoTipoDictado) {
        //horarios = new ArrayList<Horario>();
        this.gtd_bAprobado = gtd_bAprobado;
        this.grupo = grupo;
        this.cursoTipoDictado = cursoTipoDictado;
    }

    //public List<Horario> getHorarioList() {
        //horarios = Horario.find("byGrupoTipoDictado",this).fetch();
        //return horarios;
    //}
}