package models;

import play.db.jpa.GenericModel;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "curso")
public class Curso extends GenericModel {

    @Id
    @GeneratedValue
    @Column(name = "cur_iId")
    public Integer cur_iId;

    @Column(name = "cur_vCodigo")
    public String cur_vCodigo;

    @Column(name = "cur_iNumCreditos")
    public Integer cur_iNumCreditos;

    @Column(name = "cur_vNombre")
    public String cur_vNombre;

    @Column(name = "cur_vCiclo")
    public String cur_vCiclo;

    //@Column(name = "esp_iId")
    //public Integer esp_iId;

//    @OneToMany(mappedBy = "curso")
//    public List<Prerequisitos> prerequisitosList;

    @OneToMany(mappedBy = "curso")
    public List<SemestreCurso> semestreCursoList;

//    @JoinColumn(name = "pre_iId", referencedColumnName = "pre_iId")
//    @ManyToOne(cascade = CascadeType.ALL)
//    public Prerequisitos prerequisitos;

    @JoinColumn(name = "pla_iId", referencedColumnName = "pla_iId")
    @ManyToOne(cascade = CascadeType.ALL)
    public PlanEstudios planEstudios;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "curso")
    public List<CursoTipoDictado> cursoTipoDictadoList;

    public Curso(String cur_vCodigo, Integer cur_iNumCreditos, String cur_vNombre, String cur_vCiclo, PlanEstudios planEstudios) {
//        prerequisitosList = new ArrayList<Prerequisitos>();
        semestreCursoList = new ArrayList<SemestreCurso>();
        cursoTipoDictadoList = new ArrayList<CursoTipoDictado>();
        this.cur_vCodigo = cur_vCodigo;
        this.cur_iNumCreditos = cur_iNumCreditos;
        this.cur_vNombre = cur_vNombre;
        this.cur_vCiclo = cur_vCiclo;
//        this.prerequisitos = prerequisitos;
        this.planEstudios = planEstudios;
    }

//    public List<Prerequisitos> getPrerequisitosList() {
//        prerequisitosList = Prerequisitos.find("byCurso",this).fetch();
//        return prerequisitosList;
//    }

    public List<SemestreCurso> getSemestreCursoList() {
        semestreCursoList = SemestreCurso.find("byCurso",this).fetch();
        return semestreCursoList;
    }

    public List<CursoTipoDictado> getCursoTipoDictadoList() {
        cursoTipoDictadoList = CursoTipoDictado.find("byCurso",this).fetch();
        return cursoTipoDictadoList;
    }
}
