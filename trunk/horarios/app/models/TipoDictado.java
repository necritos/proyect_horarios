package models;

import play.db.jpa.GenericModel;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "tipo_dictado")
public class TipoDictado extends GenericModel {

    @Id
    @GeneratedValue
    @Column(name = "tid_iId")
    public Integer tid_iId;

    @Column(name = "tid_vNombre")
    public String tid_vNombre;

    @Column(name = "tid_vCodigo")
    public String tid_vCodigo;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "tipoDictado")
    public List<CursoTipoDictado> cursoTipoDictadoList;

    public TipoDictado(String tid_vNombre, String tid_vCodigo) {
        cursoTipoDictadoList = new ArrayList<CursoTipoDictado>();
        this.tid_vNombre = tid_vNombre;
        this.tid_vCodigo = tid_vCodigo;
    }

    public List<CursoTipoDictado> getCursoTipoDictadoList() {
        cursoTipoDictadoList = CursoTipoDictado.find("byCursoTipoDictado",this).fetch();
        return cursoTipoDictadoList;
    }
}
