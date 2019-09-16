# RESTservice
Servicio REST que permita manejar (CRUD) (Teams/Players) o (Courses/Students)
<ul>
<li> Debe implementarse con 4 capas: Controllers, Services, Models/Repositories.</li>  
<li> Debe tener al menos un servicio/implementación configurado en un archivo Config.js para manejar los beans.</li>  
<li> La relación uno a muchos debe quedar definida en los models correspondientes.</li>
<li> Debe contener validaciones e implementar la validación de rut de jugador o alumno con validaciones de spring.</li>  
<li> Debe poder buscar por los jugadores de un equipo ó por los alumnos de un curso.</li>
<li> Las queries deben ser siguiendo el formato JPA (findBy.. etc).</li>
<li> Por último debe registrar el log, las llamadas a los métodos create, update y delete de los controladores.  Utilizando AOP.</li>
</ul>
