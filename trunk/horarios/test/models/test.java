/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import java.util.Date;
import org.junit.Before;
import org.junit.Test;
import play.test.Fixtures;
import play.test.UnitTest;

/**
 *
 * @author Velasquez
 */
public class test extends UnitTest{
    /*@Before
     
    public void setup() {
        Fixtures.deleteDatabase();
    }*/

    @Test
    public void create(){
        PlanEstudios p=new PlanEstudios(2009).save();
        TipoDictado a= new TipoDictado("Teoria","1");
        TipoDictado b= new TipoDictado("Practica","2");
        TipoDictado c= new TipoDictado("Laboratorio","3");
        Date d=new Date(2012, 3, 19);
        Date d1=new Date(2012,7,18);
        SemestreAcademico w=new SemestreAcademico(d,d1).save();
        Curso c1 = new Curso("2020101",4,"Introduccion a la Teoria General de Sistemas","I",p);
        Curso c2 = new Curso("2020102",4,"Introduccion a la Computacion e Ingenieria de Software","I",p);
        Curso c3 = new Curso("2020103",4,"Calculo I","I",p);
        Curso c4 = new Curso("2020104",4,"Matematica Basica I","I",p);
        Curso c5 = new Curso("2020105",2,"Comunicacion y Dinamica de Grupo","I",p);
        Curso c6 = new Curso("2020106",3,"Idioma Extranjero I","I",p);
        Curso c7 = new Curso("2020201",4,"Programacion I","II",p);
        Curso c8 = new Curso("2020202",3,"Matematicas Discretas","II",p);
        Curso c9 = new Curso("2020203",4,"Calculo II","II",p);
        Curso c10 = new Curso("2020204",4,"Matematica Basica II","II",p);
        Curso c11 = new Curso("2020205",4,"Fisica I","II",p);
        Curso c12 = new Curso("2020206",3,"Idioma Extranjero II","II",p);
        CursoTipoDictado q1=new CursoTipoDictado(2,c1,a);
        CursoTipoDictado q2=new CursoTipoDictado(2,c1,b);
        CursoTipoDictado q3=new CursoTipoDictado(3,c2,a).save();
        CursoTipoDictado q4=new CursoTipoDictado(2,c2,c).save();
        CursoTipoDictado q5=new CursoTipoDictado(3,c3,a).save();
        CursoTipoDictado q6=new CursoTipoDictado(2,c3,b).save();
        CursoTipoDictado q7=new CursoTipoDictado(3,c4,a).save();
        CursoTipoDictado q8=new CursoTipoDictado(2,c4,b).save();
        CursoTipoDictado q9=new CursoTipoDictado(1,c5,a).save();
        CursoTipoDictado q10=new CursoTipoDictado(2,c5,b).save();
        CursoTipoDictado q11=new CursoTipoDictado(6,c6,b);
        CursoTipoDictado q12=new CursoTipoDictado(3,c7,a);
        CursoTipoDictado q13=new CursoTipoDictado(2,c7,c);
        CursoTipoDictado q14=new CursoTipoDictado(2,c8,a).save();
        CursoTipoDictado q15=new CursoTipoDictado(2,c8,b).save();
        CursoTipoDictado q16=new CursoTipoDictado(3,c9,a).save();
        CursoTipoDictado q17=new CursoTipoDictado(2,c9,b).save();
        CursoTipoDictado q18=new CursoTipoDictado(3,c10,a).save();
        CursoTipoDictado q19=new CursoTipoDictado(2,c10,b).save();
        CursoTipoDictado q20=new CursoTipoDictado(3,c11,a).save();
        CursoTipoDictado q21=new CursoTipoDictado(2,c11,b).save();
        CursoTipoDictado q22=new CursoTipoDictado(6,c12,b).save();
        SemestreCurso s1=new SemestreCurso(w, c1);
        SemestreCurso s2=new SemestreCurso(w, c2).save();
        SemestreCurso s3=new SemestreCurso(w, c3).save();
        SemestreCurso s4=new SemestreCurso(w, c4).save();
        SemestreCurso s5=new SemestreCurso(w, c5).save();
        SemestreCurso s6=new SemestreCurso(w, c6);
        SemestreCurso s7=new SemestreCurso(w, c7);
        SemestreCurso s8=new SemestreCurso(w, c8).save();
        SemestreCurso s9=new SemestreCurso(w, c9).save();
        SemestreCurso s10=new SemestreCurso(w, c10).save();
        SemestreCurso s11=new SemestreCurso(w, c11).save();
        SemestreCurso s12=new SemestreCurso(w, c12).save();
        Grupo g1=new Grupo(1, 25, s1);
//        Grupo g2=new Grupo(1, 35, s2);
//        Grupo g3=new Grupo(1, 25, s3);
//        Grupo g4=new Grupo(1, 40, s4);
//        Grupo g5=new Grupo(1, 35, s5);
        Grupo g6=new Grupo(1, 25, s6);
        Grupo g7=new Grupo(2, 25, s6);
        Grupo g8=new Grupo(1, 40, s7);
//        Grupo g9=new Grupo(1, 25, s8);
//        Grupo g10=new Grupo(1, 35, s9);
//        Grupo g11=new Grupo(1, 25, s10);
//        Grupo g12=new Grupo(1, 35, s11);
//        Grupo g13=new Grupo(1, 25, s12);
//        Grupo g14=new Grupo(2, 25, s12);
        GrupoTipoDictado gd1=new GrupoTipoDictado(Boolean.FALSE, g1, q1).save();
        GrupoTipoDictado gd2=new GrupoTipoDictado(Boolean.FALSE, g1, q2).save();
//        GrupoTipoDictado gd3=new GrupoTipoDictado(Boolean.FALSE, g2, q3).save();
//        GrupoTipoDictado gd4=new GrupoTipoDictado(Boolean.FALSE, g2, q4).save();
//        GrupoTipoDictado gd5=new GrupoTipoDictado(Boolean.FALSE, g3, q5).save();
//        GrupoTipoDictado gd6=new GrupoTipoDictado(Boolean.FALSE, g3, q6).save();
//        GrupoTipoDictado gd7=new GrupoTipoDictado(Boolean.FALSE, g4, q7).save();
//        GrupoTipoDictado gd8=new GrupoTipoDictado(Boolean.FALSE, g4, q8).save();
//        GrupoTipoDictado gd9=new GrupoTipoDictado(Boolean.FALSE, g5, q9).save();
//        GrupoTipoDictado gd10=new GrupoTipoDictado(Boolean.FALSE, g5, q10).save();
        GrupoTipoDictado gd11=new GrupoTipoDictado(Boolean.FALSE, g6, q11).save();
        GrupoTipoDictado gd12=new GrupoTipoDictado(Boolean.FALSE, g7, q11).save();
        GrupoTipoDictado gd13=new GrupoTipoDictado(Boolean.FALSE, g8, q12).save();
        GrupoTipoDictado gd14=new GrupoTipoDictado(Boolean.FALSE, g8, q13).save();
//        GrupoTipoDictado gd15=new GrupoTipoDictado(Boolean.FALSE, g9, q14).save();
//        GrupoTipoDictado gd16=new GrupoTipoDictado(Boolean.FALSE, g9, q15).save();
//        GrupoTipoDictado gd17=new GrupoTipoDictado(Boolean.FALSE, g10, q16).save();
//        GrupoTipoDictado gd18=new GrupoTipoDictado(Boolean.FALSE, g10, q17).save();
//        GrupoTipoDictado gd19=new GrupoTipoDictado(Boolean.FALSE, g11, q18).save();
//        GrupoTipoDictado gd20=new GrupoTipoDictado(Boolean.FALSE, g11, q19).save();
//        GrupoTipoDictado gd21=new GrupoTipoDictado(Boolean.FALSE, g12, q20).save();
//        GrupoTipoDictado gd22=new GrupoTipoDictado(Boolean.FALSE, g12, q21).save();
//        GrupoTipoDictado gd23=new GrupoTipoDictado(Boolean.FALSE, g13, q22).save();
//        GrupoTipoDictado gd24=new GrupoTipoDictado(Boolean.FALSE, g14, q22).save();
        
        assertEquals(1,PlanEstudios.count());
        assertEquals(12,Curso.count());
        assertEquals(6,GrupoTipoDictado.count());
        assertEquals(4,Grupo.count());
        assertEquals(1,SemestreAcademico.count());
        assertEquals(12,SemestreCurso.count());
        assertEquals(22,CursoTipoDictado.count());
        assertEquals(3,TipoDictado.count());

    }
}
