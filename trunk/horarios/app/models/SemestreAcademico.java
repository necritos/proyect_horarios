package models;

import play.db.jpa.GenericModel;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "semestre_academico")
public class SemestreAcademico extends GenericModel {

    @Id
    @GeneratedValue
    @Column(name = "sem_iId")
    public Integer semiId;

    @Column(name = "sem_dFechaIni")
    @Temporal(TemporalType.DATE)
    public Date semdFechaIni;

    @Column(name = "sem_dFechaFin")
    @Temporal(TemporalType.DATE)
    public Date semdFechaFin;

    @OneToMany(mappedBy = "semestreAcademico")
    public List<SemestreCurso> semestreCursoList;

    //@OneToMany(mappedBy = "semiId")
    //public List<Disponibilidad> disponibilidadList;


    public SemestreAcademico(Date semdFechaIni, Date semdFechaFin) {
        semestreCursoList = new ArrayList<SemestreCurso>();
        this.semdFechaIni = semdFechaIni;
        this.semdFechaFin = semdFechaFin;
    }

    public List<SemestreCurso> getSemestreCursoList() {
        semestreCursoList = SemestreCurso.find("bySemestreAcademico",this).fetch();
        return semestreCursoList;
    }
}
