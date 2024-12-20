package co.ucentral.sistemas.citasmedicas;
import co.ucentral.sistemas.citasmedicas.entidades.*;
import co.ucentral.sistemas.citasmedicas.repositorios.*;
import lombok.extern.log4j.Log4j2;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

@SpringBootApplication
@Log4j2
public class    CitasmedicasApplication implements CommandLineRunner {
	private static final Logger logger = Logger.getLogger(CitasmedicasApplication.class.getName());
	public static void main(String[] args) {
		SpringApplication.run(CitasmedicasApplication.class, args);
		logger.info("Cargado exitosamente");
	}

	RepositorioRol repositorioRol;
	RepositorioConsultor repositorioConsultor;
	RepositorioMedico repositorioMedico;
	RepositorioAfiliado repositorioAfiliado;
	RepositorioRegistro repositorioRegistro;
	RepositorioSede repositorioSede;
	RepositorioEspecialidad repositorioEspecialidad;
	RepositorioConsultorio repositorioConsultorio;
	RepositorioCita repositorioCita;
	RepositorioAfiliacion repositorioAfiliacion;

	public CitasmedicasApplication(RepositorioRol repositorioRol, RepositorioConsultor repositorioConsultor, RepositorioMedico repositorioMedico, RepositorioAfiliado repositorioAfiliado, RepositorioRegistro repositorioRegistro, RepositorioSede repositorioSede, RepositorioEspecialidad repositorioEspecialidad, RepositorioConsultorio repositorioConsultorio, RepositorioCita repositorioCita, RepositorioAfiliacion repositorioAfiliacion) {
		this.repositorioRol = repositorioRol;
		this.repositorioConsultor = repositorioConsultor;
		this.repositorioMedico = repositorioMedico;
		this.repositorioAfiliado = repositorioAfiliado;
		this.repositorioRegistro = repositorioRegistro;
		this.repositorioSede = repositorioSede;
		this.repositorioEspecialidad = repositorioEspecialidad;
		this.repositorioConsultorio = repositorioConsultorio;
		this.repositorioCita = repositorioCita;
		this.repositorioAfiliacion = repositorioAfiliacion;
	}

	@Override
	public void run(String... args) throws Exception {

		Rol rol1 = new Rol("Consultor");
		repositorioRol.save(rol1);
		Rol rol2 = new Rol("Medico");
		repositorioRol.save(rol2);
		Rol rol3 = new Rol("Afiliado");
		repositorioRol.save(rol3);

		Sede sede1 = new Sede("Centro oriente","Carrera 10 #123-45, Bogotá, Cundinamarca",23);
		repositorioSede.save(sede1);
		Sede sede2 = new Sede("Zona sur occidental","Calle 20 #456, Bogotá, Cundinamarca",41);
		repositorioSede.save(sede2);
		Sede sede3 = new Sede("Zona nororiental","Avenida 30 #789, Bogotá, Cundinamarca",20);
		repositorioSede.save(sede3);
		Sede sede4 = new Sede("Zona noroccidental","Carrera 40 #101, Bogotá, Cundinamarca",20);
		repositorioSede.save(sede4);
		Sede sede5 = new Sede("Zona sur","Calle 50 #202, Bogotá, Cundinamarca",20);
		repositorioSede.save(sede5);
		Sede sede6 = new Sede("Zona norte","Avenida 60 #303, Bogotá, Cundinamarca",21);
		repositorioSede.save(sede6);

		Especialidad especialidad1 = new Especialidad("Medicina general");
		repositorioEspecialidad.save(especialidad1);
		Especialidad especialidad2 = new Especialidad("Odontologia");
		repositorioEspecialidad.save(especialidad2);
		Especialidad especialidad3 = new Especialidad("Terapia fisica");
		repositorioEspecialidad.save(especialidad3);
		Especialidad especialidad4 = new Especialidad("Medicina interna");
		repositorioEspecialidad.save(especialidad4);
		Especialidad especialidad5 = new Especialidad("Radiologia");
		repositorioEspecialidad.save(especialidad5);
		Especialidad especialidad6 = new Especialidad("Pedriatia");
		repositorioEspecialidad.save(especialidad6);

		Consultorio consultorio1 = new Consultorio(1,"Medicina general",sede1);
		repositorioConsultorio.save(consultorio1);
		Consultorio consultorio2 = new Consultorio(2,"Odontologia",sede2);
		repositorioConsultorio.save(consultorio2);
		Consultorio consultorio3 = new Consultorio(3,"Terapia fisica",sede3);
		repositorioConsultorio.save(consultorio3);
		Consultorio consultorio4 = new Consultorio(4,"Medicina interna",sede4);
		repositorioConsultorio.save(consultorio4);
		Consultorio consultorio5 = new Consultorio(5,"Radiologia",sede5);
		repositorioConsultorio.save(consultorio5);
		Consultorio consultorio6 = new Consultorio(5,"Pediatria",sede6);
		repositorioConsultorio.save(consultorio6);

		Consultor consultor1 = new Consultor(1001346667,"Jennifer Lopez Vanegas",true);
		repositorioConsultor.save(consultor1);
		Consultor consultor2 = new Consultor(1010101010,"Camilo Andres Garcia",true);
		repositorioConsultor.save(consultor2);

		Medico medico1 = new Medico(79976478,"Luis Alberto Montenegro Avila",true,especialidad1,consultorio1,sede1);
		repositorioMedico.save(medico1);
		Medico medico2 = new Medico(11234134,"Luisa Castellanos",true,especialidad2,consultorio2,sede2);
		repositorioMedico.save(medico2);
        Medico medico3 = new Medico(795443845,"Adolfo Martinez",true,especialidad3,consultorio3,sede3);
		repositorioMedico.save(medico3);

		final String Traslado = "Traslado";
		Afiliado afiliado1 = new Afiliado(1071789472,"Santiago Aguirre",true,Traslado);
        repositorioAfiliado.save(afiliado1);
        Afiliado afiliado2 = new Afiliado(135253949,"Vanesa Guayara",true,Traslado);
        repositorioAfiliado.save(afiliado2);
        Afiliado afiliado3 = new Afiliado(54678954,"Luz Angela Lopez Aparicio",true,Traslado);
		repositorioAfiliado.save(afiliado3);
		Afiliado afiliado4 = new Afiliado(1000000000,"Miguel Angel Sandoval Alvarado",true,Traslado);
		repositorioAfiliado.save(afiliado4);

        final String CC = "Cédula de Ciudadanía";
        final String CE = "Cédula de Extranjería";
        final String TI = "Tarjeta de Identidad";

        Date date1 = Date.valueOf(Date.valueOf(LocalDate.of(2005,5,12)).toLocalDate());
        Date date2 = Date.valueOf(Date.valueOf(LocalDate.of(1980,3,24)).toLocalDate());
        Date date3 = Date.valueOf(Date.valueOf(LocalDate.of(1999,12,8)).toLocalDate());
        Date date4 = Date.valueOf(Date.valueOf(LocalDate.of(2010,1,1)).toLocalDate());
		Date date5 = Date.valueOf(Date.valueOf(LocalDate.of(2004,9,5)).toLocalDate());
		Date date6 = Date.valueOf(Date.valueOf(LocalDate.of(2002,4,28)).toLocalDate());
		Date date7 = Date.valueOf(Date.valueOf(LocalDate.of(1980,6,20)).toLocalDate());

		final String M = "Masculino";
		final String F = "Femenino";

		Registro registro1 = new Registro(CC,1071789472,date5,"Santiago Aguirre","3182778444","Calle 2A",M,"saguirreg@ucentral.edu.co","333");
		repositorioRegistro.save(registro1);
		Registro registro2 = new Registro(CC,135253949,date6,"Vanesa Guayara","322523949","Calle 55 # 35 96",F,"Aldia@ucentral.edu.co","222");
		repositorioRegistro.save(registro2);
        //Consultor
        Registro registro3 = new Registro(CC,1001346667,date1,"Jennifer Lopez Vanegas","3024445556","Calle 55",F,"jlopezv6@ucentral.edu.co","123");
        repositorioRegistro.save(registro3);
        //Afiliados
        Registro registro4 = new Registro(CE,54678954,date2,"Luz Angela Lopez Aparicio","3081234589","Calle 88",F,"luz123@gmail.com","123");
        repositorioRegistro.save(registro4);
        Registro registro5 = new Registro(TI,1000000000,date4,"Miguel Angel Sandoval Alvarado","3081234589","Calle 88",M,"miguelito@gmail.com","miguelito*");
        repositorioRegistro.save(registro5);
        //Medicos
        Registro registro6 = new Registro(CC,79976478, date3,"Luis Alberto Montenegro Avila","3154257892","Barrio Bosa",M,"luisalver@gmail.com","Luis50.*");
        repositorioRegistro.save(registro6);
		Registro registro7 = new Registro(CC,1010101010, date7,"Camilo Andres Garcia","3104257892","Barrio Bosa",M,"camilo@gmail.com","camilo");
		repositorioRegistro.save(registro7);

		// Definir el formato de fecha y hora
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

		// Crear una lista para almacenar las citas
		List<Cita> citas = new ArrayList<>();

		// Definir la hora de inicio de las citas
		LocalDateTime horaInicio = LocalDateTime.parse("2024-05-31 08:00:00", formatter);

		final String proceso = "Proceso";
		// Crear 10 citas con 20 minutos de diferencia entre cada una
		for (int i = 0; i < 20; i++) {
			// Agregar el desplazamiento de tiempo (20 minutos * i) a la hora de inicio
			LocalDateTime fechaCita = horaInicio.plusMinutes(20 * i);

			// Crear la cita y agregarla a la lista
			if (i % 2 == 0) {
				Cita cita = new Cita(i + 1, fechaCita, especialidad1, proceso,medico1,null);
				repositorioCita.save(cita);
			} else if (i % 3 == 0) {
				Cita cita = new Cita(i + 1, fechaCita, especialidad2, proceso,medico2,null);
				repositorioCita.save(cita);
			}else{
				Cita cita = new Cita(i + 1, fechaCita,especialidad3, proceso,medico3,null);
				repositorioCita.save(cita);
			}

		}

		// Imprimir las citas
		for (Cita cita : citas) {
			System.out.println(cita);
		}

		Afiliacion afiliacion1 = new Afiliacion("Pensionado",CC,78945612,"Alvaro Castañeda","3114785622","alvaro@gmail.com","Calle 102","Nuevo",1010101010);
		repositorioAfiliacion.save(afiliacion1);
		Afiliacion afiliacion2 = new Afiliacion("Pensionado",CC,54789641,"Camila Acuña Soler","3133336202","xami@gmail.com","Calle 12","Nuevo",1001346667);
		repositorioAfiliacion.save(afiliacion2);

	}
}

