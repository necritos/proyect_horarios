package models;

import play.db.jpa.GenericModel;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "semestre_curso")
public class SemestreCurso extends GenericModel{

    @Id
    @GeneratedValue
    @Column(name = "scu_iId")
    public Integer scu_iId;

    @OneToMany(mappedBy = "semestreCurso")
    public List<Grupo> grupoList;

    //@JoinColumn(name = "dpt_iId", referencedColumnName = "dpt_iId")
    //@ManyToOne
    //public PdtAcademico pdtAcademico;
    
    /*alumnos aptos*/    
    @Column(name = "scu_numAlum")
    public Integer scu_numAlum;

    @JoinColumn(name = "sem_iId", referencedColumnName = "sem_iId")
    @ManyToOne(cascade = CascadeType.ALL)
    public SemestreAcademico semestreAcademico;

    @JoinColumn(name = "cur_iId", referencedColumnName = "cur_iId")
    @ManyToOne(cascade = CascadeType.ALL)
    public Curso curso;

    public SemestreCurso(SemestreAcademico semestreAcademico, Curso curso) {
        grupoList = new ArrayList<Grupo>();
        this.semestreAcademico = semestreAcademico;
        this.curso = curso;
    }

    public List<Grupo> getGrupoList() {
        grupoList = Grupo.find("bySemestreCurso",this).fetch();
        return grupoList;
    }
}
