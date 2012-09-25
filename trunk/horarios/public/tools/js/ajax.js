        cc=false;
        eli=false;

        function esconder(){
            $('#crearg').hide();
            $('#eliminarg').hide();
        }
        function crearG(){
            if(cc){
                $('#crearg').hide();
               cc=false;
            }else{
                $('#crearg').show();
                $('#eliminarg').hide();
                cc=true;
                eli=false;
            }

        }
        function eliminarG(){
             if(eli){
                $('#eliminarg').hide();
                eli=false;
            }else{
                $('#eliminarg').show();
                $('#crearg').hide();
                eli=true;
                cc=false;
            }
        }
        ////dibujar horarios
        function limpiarTabla(){
            $('#tabla_horario').html("");
        }
        function agregarHora(){
            for (var i = 8; i<=22 ; i++) {
                $('#tabla_horario').append("<tr id='h"+i+"'>"+
                    "<td>"+i+":00:00"+"</td>"+
                    "</tr>");
            };

        }
        function agregarHorariosDia(lista){
            
            r=0;
            for (var i = 8; i<=22 ; i++){
                idH="#h"+i;
                if(r < lista.length){
                    if(i==lista[r][1]){
                        $(idH).append("<td style=' border: 1px solid #999;  vertical-align:middle; text-align: center; background:"+lista[r][3]+"' rowspan='"+(lista[r][2]-lista[r][1])+"'>"+lista[r][0]+"</td>");    
                        i=i+(lista[r][2]-lista[r][1])-1;
                        r++;
                    }else{
                        $(idH).append("<td></td>");        
                    }
                }else{
                    $('#h'+i).append("<td></td>");
                }
                
            } 
        }
        //traer listas ajax
        function traerCiclos(id_data,idSelect,idCiclo){
            $.ajax({
                type:'GET',
                url:'@{Application.selectCurso()}',
                data:{'ciclo':$('#'+idCiclo).val(),'idSelect':idSelect},
                success: function(data) {
                    $('#'+id_data).html(data);
                }
            });
        }
        function traerGrupos(id_data,idSelect){          
            $.ajax({
                type:'GET',
                url:'@{Application.selectGrupo()}',
                data:{'id_semestre_curso':$('#'+idSelect).val()},
                success: function(data) {
                    $('#'+id_data).html(data);
                }
            });
        }
        function crearGrupo(){
            //alert('gru_iMaxAlumnos:'+$('#gru_max_alum').val()+" "+'idSemestreCurso:'+$('#curso_val').val());
            $.ajax({
                type:'POST',
                url:'@{Application.crearGrupo()}',
                data:{'gru_iMaxAlumnos':$('#gru_max_alum').val(),'idSemestreCurso':$('#curso_val').val()},
                success: function(data) {
                    $('#crear_grupo').modal('hide');
                    $('#mensaje').html(data);
                   
                }
            });
        }
        function crearHorario(idGTH,dia,hora){
            //alert('gru_iMaxAlumnos:'+$('#gru_max_alum').val()+" "+'idSemestreCurso:'+$('#curso_val').val());
            $.ajax({
                type:'POST',
                url:'@{Application.asignarHorario()}',
                data:{'idGrupoTipoDictado':idGTH,'dia':$('#'+dia).val(),'hora':$('#'+hora).val()},
                success: function(data) {
                   // $('#crear_horario').modal('hide');
                    $('#mensaje_creacion').html(data);
                   
                }
            });
        }
        function traerIngoGrupo(){
            //alert('gru_iMaxAlumnos:'+$('#gru_max_alum').val()+" "+'idSemestreCurso:'+$('#curso_val').val());
            $.ajax({
                type:'GET',
                url:'@{Application.selectGrupoTipoDictado()}',
                data:{'id_grupo':$('#val_grupo').val()},
                success: function(data) {              
                   $('#info_grupo').html(data);
                }
            });
        }
        function traerInfoHorario(idGTD,id_info){
             $.ajax({
                type:'GET',
                url:'@{Application.infoHorarioTipoDictado()}',
                data:{'idGrupoTipoDictado':idGTD},
                success: function(data) {              
                   $('#'+id_info).html(data);
                }
            });
        }
        ////////////////vista mostrar horario
        function traerGrupoHorario(){
            $.ajax({
                type:'GET',
                url:'@{Application.selectGrupoHorario()}',
                data:{'ciclo':$('#vista_ciclo').val()},
                success: function(data) {              
                   $('#grupo_horario').html(data);
                }
            });
        }
        function traerInfoDibujo(){
            $.ajax({
                type:'GET',
                url:'@{Application.renderGrupoHorario()}',
                data:{'ciclo':$('#vista_ciclo').val(),'grupo':$('#vista_grupo').val()},
                success: function(data) {              
                   $('#horario_data_dibujo').html(data);
                }
            });    
        }