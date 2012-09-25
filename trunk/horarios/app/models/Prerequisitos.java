package models;

import play.db.jpa.GenericModel;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "prerequisitos")
public class Prerequisitos extends GenericModel {

    @Id
    @GeneratedValue
    @Column(name = "pre_iId")
    public Integer pre_iId;

//    @JoinColumn(name = "cur_iId", referencedColumnName = "cur_iId")
//    @ManyToOne(cascade = CascadeType.ALL)
    public Curso curso;

//    @OneToMany(mappedBy = "prerequisitos")
//    public List<Curso> cursoList;
//
//    public Prerequisitos(Curso curso) {
//        cursoList = new ArrayList<Curso>();
//        this.curso = curso;
//    }
//
//    public List<Curso> getCursoList() {
//        cursoList = Curso.find("byPrerequisitos",this).fetch();
//        return cursoList;
//    }
}
