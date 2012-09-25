package models;

import play.db.jpa.GenericModel;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "plan_estudios")
public class PlanEstudios extends GenericModel {

    @Id
    @GeneratedValue
    @Column(name = "pla_iId")
    public Integer pla_iId;

    @Column(name = "pla_iAnio")
    public Integer pla_iAnio;

    //@JoinColumn(name = "esp_iId", referencedColumnName = "esp_iId")
    //@ManyToOne
    //public Especialidad espiId;

    @OneToMany(mappedBy = "planEstudios", cascade=CascadeType.REMOVE)
    public List<Curso> cursoList;

    public PlanEstudios(Integer pla_iAnio) {
        cursoList = new ArrayList<Curso>();
        this.pla_iAnio = pla_iAnio;
    }

    public List<Curso> getCursoList() {
        cursoList = Curso.find("byPlanEstudios",this).fetch();
        return cursoList;
    }
}
