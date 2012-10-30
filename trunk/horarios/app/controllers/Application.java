package controllers;

import org.apache.commons.beanutils.converters.IntegerArrayConverter;
import org.apache.commons.beanutils.converters.StringArrayConverter;
import play.mvc.*;

import java.util.*;

import models.*;

import play.libs.*;
import play.cache.*;
import play.data.validation.*;

public class Application extends Controller {

    public static boolean log = false;
    public static String cicloA;
    public static Integer grupoA;

    public static void login() {
	if(!log){
            String randomID = Codec.UUID();
            render(randomID);
        } else {
            crearHorario();
        }
    }
    
    public static void logout() {
	log = false;
        login();
    }
    
    public static void loginpost(@Required(message="Usuario es requerido") String name,
                             @Required(message="Contraseña es requerida") String pass,
                            @Required(message="Por favor ingrese el código") String code,
                            String randomID){
	User user = User.find("byUse_usernameAndUse_password", name, pass).first();
        if (user != null) {
            log = true;
            validation.equals(code, Cache.get(randomID)).message("Código Inválido");
            if(validation.hasErrors()) {
                render("Application/login.html",randomID);
            } else {
                crearHorario();
            }
        } else {
            String error = "login";
            render("Application/login.html",randomID,error);
        }
    }

    public static void index() {
        if (!log) {
            login();
        } else {
            crearHorario();
        }
    }

    public static void crearHorario() {
        List<String> ciclos = new ArrayList<String>();
        List<SemestreCurso> lsc = SemestreCurso.findAll();
        System.out.println(lsc.size());
        for (SemestreCurso sc : lsc) {
            if (sc.curso != null) //System.out.println(sc.curso.cur_vNombre);
            {
                if (!ciclos.contains(sc.curso.cur_vCiclo)) {
                    System.out.println(sc.curso.cur_vCiclo);
                    ciclos.add(sc.curso.cur_vCiclo);
                }
            }
        }

        render(ciclos);
    }

    public static void selectGrupoHorario(String ciclo) {
        List<Curso> listaCursos = Curso.find("byCur_vCiclo", ciclo).fetch();
        List<SemestreCurso> listaSemestreCurso = new ArrayList<SemestreCurso>();
        for (Curso ccurso : listaCursos) {
            if (SemestreCurso.find("byCurso", ccurso).fetch().size() > 0) {
                listaSemestreCurso.add((SemestreCurso) SemestreCurso.find("byCurso", ccurso).first());
            }
        }
        int r = 0;
        List<Integer> grupos = new ArrayList<Integer>();
        for (SemestreCurso sc : listaSemestreCurso) {
            if (r < sc.grupoList.size()) {
                r = sc.grupoList.size();
                for (int i = grupos.size() + 1; i <= r; i++) {
                    grupos.add(i);
                }
            }
        }
        render(grupos);
    }

    public static void renderGrupoHorario(String ciclo, Integer grupo) {
        cicloA = ciclo;
        grupoA = grupo;
        System.out.println("llega:" + ciclo + " " + grupo);
        List<Horario> horarios = Horario.find("order by hor_tIni asc").fetch();

        String hl = "[";
        String hm = "[";
        String hmi = "[";
        String hj = "[";
        String hv = "[";
        String hs = "[";
        for (Horario h : horarios) {
            if (h.grupoTipoDictado.grupo.semestreCurso.curso.cur_vCiclo.equals(ciclo)
                    && h.grupoTipoDictado.grupo.gru_iNumGrupo == grupo) {
                if (h.hor_vDia.equals("lunes")) {
                    hl = hl + "['" + h.grupoTipoDictado.grupo.semestreCurso.curso.cur_vNombre + "-"
                            + h.grupoTipoDictado.cursoTipoDictado.tipoDictado.tid_vNombre + "',"
                            + h.hor_tIni + "," + h.hor_tFin + ",'#BBD188'],";
                }
                if (h.hor_vDia.equals("martes")) {
                    hm = hm + "['" + h.grupoTipoDictado.grupo.semestreCurso.curso.cur_vNombre + "-"
                            + h.grupoTipoDictado.cursoTipoDictado.tipoDictado.tid_vNombre + "',"
                            + h.hor_tIni + "," + h.hor_tFin + ",'#BBD188'],";
                }
                if (h.hor_vDia.equals("miercoles")) {
                    hmi = hmi + "['" + h.grupoTipoDictado.grupo.semestreCurso.curso.cur_vNombre + "-"
                            + h.grupoTipoDictado.cursoTipoDictado.tipoDictado.tid_vNombre + "',"
                            + h.hor_tIni + "," + h.hor_tFin + ",'#BBD188'],";
                }
                if (h.hor_vDia.equals("jueves")) {
                    hj = hj + "['" + h.grupoTipoDictado.grupo.semestreCurso.curso.cur_vNombre + "-"
                            + h.grupoTipoDictado.cursoTipoDictado.tipoDictado.tid_vNombre + "',"
                            + h.hor_tIni + "," + h.hor_tFin + ",'#BBD188'],";
                }
                if (h.hor_vDia.equals("viernes")) {
                    hv = hv + "['" + h.grupoTipoDictado.grupo.semestreCurso.curso.cur_vNombre + "-"
                            + h.grupoTipoDictado.cursoTipoDictado.tipoDictado.tid_vNombre + "',"
                            + h.hor_tIni + "," + h.hor_tFin + ",'#BBD188'],";
                }
                if (h.hor_vDia.equals("sabado")) {
                    hs = hs + "['" + h.grupoTipoDictado.grupo.semestreCurso.curso.cur_vNombre + "-"
                            + h.grupoTipoDictado.cursoTipoDictado.tipoDictado.tid_vNombre + "',"
                            + h.hor_tIni + "," + h.hor_tFin + ",'#BBD188'],";
                }
            }
        }
        hl = hl + "]";
        hm = hm + "]";
        hmi = hmi + "]";
        hj = hj + "]";
        hv = hv + "]";
        hs = hs + "]";
        //System.out.println(hl);
        render(hl, hm, hmi, hj, hv, hs);
        //render(horarioLunes,horarioMartes,horarioMiercoles,horarioJueves,horarioViernes,horarioSabado);
    }

    public static void verHorario() {
        render();
    }

    public static void asignarHorario(Integer idGrupoTipoDictado, String dia, Integer hora) {
        GrupoTipoDictado grupoTD = GrupoTipoDictado.find("byGtd_iId", idGrupoTipoDictado).first();
        Integer horaInicio = hora;
        Integer numHoras = grupoTD.cursoTipoDictado.ctd_iHoras;
        Integer horaFin = hora + numHoras;

        if (Horario.find("byGrupoTipoDictado", grupoTD).fetch().size() > 0) {
            Horario h = Horario.find("byGrupoTipoDictado", grupoTD).first();
            h.hor_tIni = horaInicio;
            h.hor_tFin = horaFin;
            h.hor_vDia = dia;
            h.save();
        } else {
            Horario nuevo = new Horario(horaInicio, horaFin, dia, grupoTD);
            nuevo.save();
        }
        render();
    }

    public static void verificarCruce(Integer idGrupoTipoDictado, String dia, Integer hora) {
        GrupoTipoDictado grupoTD = GrupoTipoDictado.find("byGtd_iId", idGrupoTipoDictado).first();
        Integer horaInicio = hora;
        Integer numHoras = grupoTD.cursoTipoDictado.ctd_iHoras;
        Integer horaFin = hora + numHoras;
        List<Horario> horarios = Horario.find("byHor_vDia", dia).fetch();
        List<Horario> horarioCruce = new ArrayList<Horario>();
        if (horarios.size() > 0) {
            for (Horario h : horarios) {
                if (h.grupoTipoDictado.gtd_iId != grupoTD.gtd_iId) {
                    if(h.grupoTipoDictado.grupo.gru_iNumGrupo==grupoTD.grupo.gru_iNumGrupo){
                        if (h.grupoTipoDictado.cursoTipoDictado.curso.cur_vCiclo.equals(grupoTD.cursoTipoDictado.curso.cur_vCiclo)) {
                            if ((h.hor_tIni <= horaInicio && horaInicio < h.hor_tFin) || (h.hor_tIni <= horaFin && horaFin <= h.hor_tFin)) {
                                System.out.println("curso " + h.grupoTipoDictado.cursoTipoDictado.curso.cur_vNombre);
                                System.out.println("Hora Inicio: " + h.hor_tIni);
                                horarioCruce.add(h);
                            }
                        }
                    }
                }
            }
        }
        String cruce="1";
        if (horarioCruce.isEmpty()) {
            System.out.println("nooooo");
            asignarHorario(idGrupoTipoDictado, dia, hora);
        } else {
            cruce="0";
            System.out.println("cruceeeeeeeee");
            render(horarioCruce,cruce);
            
        }
        
    }

    public static void crearGrupo(Integer gru_iMaxAlumnos, Integer idSemestreCurso) {
        System.out.print("max_alum:" + gru_iMaxAlumnos + " - " + "idSemestreCurso" + idSemestreCurso);
        SemestreCurso semestreCurso = SemestreCurso.find("byScu_iId", idSemestreCurso).first();
        Grupo nuevoG = new Grupo(semestreCurso.getGrupoList().size() + 1,
                gru_iMaxAlumnos,
                semestreCurso);
        nuevoG.save();
        semestreCurso.getGrupoList().add(nuevoG);
        System.out.println("total de CTD:" + nuevoG.semestreCurso.curso.cursoTipoDictadoList.size());
        for (int i = 0; i < nuevoG.semestreCurso.curso.cursoTipoDictadoList.size(); i++) {
            GrupoTipoDictado nuevoGTD = new GrupoTipoDictado(false,
                    nuevoG,
                    nuevoG.semestreCurso.curso.cursoTipoDictadoList.get(i));
            nuevoGTD.save();
        }
        render();
    }

    public static void eliminarGrupo(Integer id_SemestreCurso, Integer idGrupo) {
        SemestreCurso curso = SemestreCurso.find("byScu_iId", id_SemestreCurso).first();
        List<Grupo> listaGrupos = curso.getGrupoList();
        for (Grupo grupo : listaGrupos) {
            if (grupo.equals(Grupo.find("byGru_iId", idGrupo).first())) {
                grupo.delete();
                break;
            }
        }
    }

    public static void modificarGrupo(Integer id_SemestreCurso, Integer id_Grupo, Integer NumNuevoAlumnos) {
        SemestreCurso curso = SemestreCurso.find("byScu_iId", id_SemestreCurso).first();
        List<Grupo> listaGrupos = curso.getGrupoList();
        for (Grupo grupo : listaGrupos) {
            if (grupo.equals(Grupo.find("byGru_iId", id_Grupo).first())) {
                grupo.gru_iMaxAlumnos = NumNuevoAlumnos;
                grupo.save();
                break;
            }
        }
    }

    public static void traerCursoNoAperturado(String ciclo) {
        System.out.print("entra:" + ciclo);
        List<Curso> listaCurso = Curso.find("byCur_vCiclo", ciclo).fetch();
        System.out.print("entra:" + listaCurso.size());
        List<SemestreCurso> listaSemestreCurso = SemestreCurso.all().fetch();
        List<Curso> listaCursosNoAperturados = new LinkedList<Curso>();
        for (Curso a : listaCurso) {
            boolean tt = true;
            for (SemestreCurso sc : listaSemestreCurso) {
                if (sc.curso == a) {
                    tt = false;
                    break;
                }
            }
            if (tt) {
                listaCursosNoAperturados.add(a);
            }
        }
        render(listaCursosNoAperturados);
    }

    public static void aperturarCurso(Integer idCurso, Integer numAlum) {
        List<SemestreAcademico> listaSemestreAcademico = SemestreAcademico.all().fetch();
        Curso cc = Curso.findById(idCurso);
        SemestreCurso sc = new SemestreCurso(listaSemestreAcademico.get(0), cc);
        sc.scu_numAlum = numAlum;
        sc.save();
    }

    public static void selectGrupo(Integer id_semestre_curso) {
        System.out.println("id:" + id_semestre_curso);
        List<Grupo> listaGrupo = new LinkedList<Grupo>();
        listaGrupo = Grupo.find("byScu_iId", id_semestre_curso).fetch();
        System.out.println(listaGrupo.size());
        render(listaGrupo);
    }

    public static void selectGrupoTipoDictado(Integer id_grupo) {
        System.out.println("id grupo:" + id_grupo);
        Grupo g = Grupo.find("byGru_iId", id_grupo).first();
        System.out.println(g.gru_iNumGrupo + " " + g.semestreCurso.curso.cur_vNombre);
        List<GrupoTipoDictado> listaGrupoTipoDictado = new LinkedList<GrupoTipoDictado>();
        listaGrupoTipoDictado = GrupoTipoDictado.find("byGrupo", g).fetch();
        System.out.println(listaGrupoTipoDictado.size());
        render(listaGrupoTipoDictado);
    }

    public static void infoHorarioTipoDictado(Integer idGrupoTipoDictado) {
        GrupoTipoDictado grupoTD = GrupoTipoDictado.find("byGtd_iId", idGrupoTipoDictado).first();
        System.out.println(grupoTD.grupo.semestreCurso.curso.cur_vNombre);
        Horario h = null;
        if (Horario.find("byGrupoTipoDictado", grupoTD).fetch().size() > 0) {
            h = Horario.find("byGrupoTipoDictado", grupoTD).first();
        }
        render(h);
    }

    public static void selectCurso(String ciclo, String idSelect) {
        String idSS = idSelect;
        List<Curso> listaCursos = Curso.find("byCur_vCiclo", ciclo).fetch();
        List<SemestreCurso> listaSemestreCurso = new ArrayList<SemestreCurso>();
        for (Curso ccurso : listaCursos) {
            if (SemestreCurso.find("byCurso", ccurso).fetch().size() > 0) {
                listaSemestreCurso.add((SemestreCurso) SemestreCurso.find("byCurso", ccurso).first());

            }
        }
        render(listaSemestreCurso, idSS);
    }

    public static void crearGrupoCursos(String ciclo) {

        List<Curso> listaCursos = Curso.find("byCur_vCiclo", ciclo).fetch();
        List<SemestreCurso> listaSemestreCurso = new ArrayList<SemestreCurso>();
        for (Curso ccurso : listaCursos) {
            if (SemestreCurso.find("byCurso", ccurso).fetch().size() > 0) {
                listaSemestreCurso.add((SemestreCurso) SemestreCurso.find("byCurso", ccurso).first());
            }
        }
        render(listaSemestreCurso);
    }

    public static void consultarCursos(String vCiclo) {
        //String idSS= idSelect;

        //String vCiclo1="II";
        System.out.println("fsdf" + vCiclo);
        String lista = "[";
        List<Curso> listaCursos = Curso.find("byCur_vCiclo", vCiclo).fetch();
        List<SemestreCurso> listaSemestreCurso = new ArrayList<SemestreCurso>();
        for (Curso ccurso : listaCursos) {
            if (SemestreCurso.find("byCurso", ccurso).fetch().size() > 0) {
                listaSemestreCurso.add((SemestreCurso) SemestreCurso.find("byCurso", ccurso).first());
            }
        }
        for (SemestreCurso CursosAbiertos : listaSemestreCurso) {
            // a = SemestreCurso.find("byCurso", CursosAbiertos.curso).first();
            lista = lista + "[";
            if (CursosAbiertos.curso.cur_vCiclo.equals(vCiclo)) {
                lista = lista + "'" + CursosAbiertos.curso.cur_vNombre + "'," + "'Num Creditos:   " + CursosAbiertos.curso.cur_iNumCreditos + "',";
                List<Grupo> listaGrupo = CursosAbiertos.getGrupoList();
                lista = lista + "[";
                for (Grupo grupo : listaGrupo) {
                    lista = lista + "[' " + grupo.gru_iNumGrupo + "',";
                    List<GrupoTipoDictado> grupoTipoDic = grupo.getGrupoTipoDictadoList();
                    for (GrupoTipoDictado tipoDictado : grupoTipoDic) {
                        TipoDictado tipo = tipoDictado.cursoTipoDictado.tipoDictado;
                        lista = lista + "['" + tipo.tid_vNombre + "',";
                        Horario hora = Horario.find("byGrupoTipoDictado", tipoDictado).first();
                        if (hora == null) {
                            lista = lista + "'Dia No Asignado','Hora No Asignada','Hora No Asignada";
                        } else {
                            if (hora.hor_vDia != null) {
                                lista = lista + "'" + hora.hor_vDia + "',";
                            }
                            if (hora.hor_tIni != null) {
                                lista = lista + "'" + hora.hor_tIni + ":00',";
                            }
                            if (hora.hor_tFin != null) {
                                lista = lista + "'" + hora.hor_tFin + ":00";
                            }
                        }
                        lista = lista + "'],";
                    }
                    lista = lista + "],";
                }
                lista = lista + "],";
            }
            lista = lista + "],";
        }

        lista = lista + "]";
        System.out.println(lista);
        render(lista);
        //  render();
    }

    public static void consultarNumeroAlumnos(Integer idSemCurso) {
        SemestreCurso curso = SemestreCurso.find("byScu_iId", idSemCurso).first();
        Integer grupos=curso.grupoList.size();
        Integer numero = curso.scu_numAlum;
        render(numero,grupos);
    }

    public static void eliminarCurso(Integer idCurso) {
        SemestreCurso sc = SemestreCurso.find("byScu_iId", idCurso).first();
        /*if(sc.getGrupoList()!=null)
        for(Grupo g:sc.grupoList){
        
        for(GrupoTipoDictado gtd:g.grupoTipoDictadoList){
        gtd.cursoTipoDictado=null;
        gtd.save();
        }
        for(GrupoTipoDictado gtd:g.grupoTipoDictadoList){
        gtd.delete();
        }
        //g.semestreCurso=null;
        g.delete();
        }*/
        System.out.print(sc.curso.cur_vNombre);
        //sc.delete();

        // Cache.delete("product_"+id);
        //SemestreCurso curso=SemestreCurso.find("byScu_iId", idCurso).first();
        //System.out.print(curso.curso.cur_vNombre);


    }

    public static void mostrarPlanEstudios() {
        PlanEstudios planE = PlanEstudios.findById(1);
        List<Curso> cursos = Curso.find("byPlanEstudios", planE).fetch();
        cursos = Curso.find("order by cur_vCodigo asc").fetch();
        String plan = "[[";
        String ciclo = "I";
        System.out.println("metodo");
        for (int i = 0; i < cursos.size();) {
            System.out.println("ciclo");
            if (cursos.get(i).cur_vCiclo.equals(ciclo)) {
                System.out.println("curso");
                plan = plan + "[" + cursos.get(i).cur_vCodigo + ",'" + cursos.get(i).cur_vNombre + "'," + cursos.get(i).cur_iNumCreditos + "],";
                i++;
            } else {
                System.out.println("otro curso");
                ciclo = cursos.get(i).cur_vCiclo;
                plan = plan + "],[";
            }
        }
        plan = plan + "]]";
        System.out.println(plan);
        render(plan);
        //render();
    }
    
    public static void captcha(String id){
        Images.Captcha captcha = Images.captcha();
        String code = captcha.getText();
        Cache.set(id, code, "10mn");
System.out.println("codigo: "+code);
System.out.println("acaba de guardarse: "+Cache.get(id));
        renderBinary(captcha);
    }
}
