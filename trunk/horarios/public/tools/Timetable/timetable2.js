

function agregarHora(){
	for (var i = 8; i<=22 ; i++) {
		$('#tabla_horario').append("<tr class='h"+i+"'><td>"+i+":00:00"+"</td></tr>");
	};	
}
