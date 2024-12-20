package co.ucentral.sistemas.citasmedicas.controladores;
import co.ucentral.sistemas.citasmedicas.dto.ConsultorioDto;
import co.ucentral.sistemas.citasmedicas.dto.EspecialidadDto;
import co.ucentral.sistemas.citasmedicas.dto.MedicoDto;
import co.ucentral.sistemas.citasmedicas.dto.SedeDto;
import co.ucentral.sistemas.citasmedicas.repositorios.RepositorioConsultorio;
import co.ucentral.sistemas.citasmedicas.repositorios.RepositorioMedico;
import co.ucentral.sistemas.citasmedicas.repositorios.RepositorioRegistro;
import co.ucentral.sistemas.citasmedicas.servicios.ServiciosConsultorio;
import co.ucentral.sistemas.citasmedicas.servicios.ServiciosEspecialidad;
import co.ucentral.sistemas.citasmedicas.servicios.ServiciosMedico;
import co.ucentral.sistemas.citasmedicas.servicios.ServiciosSede;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Log4j2
@Controller
public class ControladoresMedico {

    ServiciosMedico serviciosMedico;
    ServiciosEspecialidad serviciosEspecialidad;
    ServiciosSede serviciosSede;
    ServiciosConsultorio serviciosConsultorio;
    RepositorioRegistro repositorioRegistro;
    RepositorioConsultorio repositorioConsultorio;
    RepositorioMedico repositorioMedico;
    private static final String IDENTIFICACION = "identificacion";

    public ControladoresMedico(ServiciosMedico serviciosMedico, ServiciosEspecialidad serviciosEspecialidad, ServiciosSede serviciosSede, ServiciosConsultorio serviciosConsultorio, RepositorioRegistro repositorioRegistro, RepositorioConsultorio repositorioConsultorio, RepositorioMedico repositorioMedico) {
        this.serviciosMedico = serviciosMedico;
        this.serviciosEspecialidad = serviciosEspecialidad;
        this.serviciosSede = serviciosSede;
        this.serviciosConsultorio = serviciosConsultorio;
        this.repositorioRegistro = repositorioRegistro;
        this.repositorioConsultorio = repositorioConsultorio;
        this.repositorioMedico = repositorioMedico;
    }

    @GetMapping({  "/medico/{identificacion}"})
    public String consultarTodos(Model model, @PathVariable int identificacion) {
        model.addAttribute(IDENTIFICACION, identificacion);
        List<MedicoDto> listamedicos = this.serviciosMedico.buscarTodos();
        model.addAttribute("listamedicos", listamedicos);
        return "medico";
    }

    @GetMapping("/medico/nuevo/{identificacion}")
    public String mostrarFormulario(Model model, @PathVariable int identificacion) {
        model.addAttribute(IDENTIFICACION, identificacion);
        MedicoDto medicoDto = new MedicoDto();

        List<SedeDto> listasede = serviciosSede.buscarTodos();
        model.addAttribute("listasede", listasede);
        List<EspecialidadDto> listaespecialidad = serviciosEspecialidad.buscarTodos();
        model.addAttribute("listaespecialidad", listaespecialidad);
        List<ConsultorioDto> listaconsultorio = serviciosConsultorio.buscarTodos();
        model.addAttribute("listaconsultorio", listaconsultorio);

        model.addAttribute("elmedico", medicoDto);
        return "form_medico";
    }
    @PostMapping("/crearmedico/{identificacion}")
    public String registrarMedico(@ModelAttribute("elmedico") MedicoDto medicoDto, Model model, @PathVariable int identificacion) {
        model.addAttribute(IDENTIFICACION, identificacion);

        ConsultorioDto consultorio = medicoDto.getConsultorio();
        SedeDto sede = medicoDto.getSede();
        EspecialidadDto especialidad = medicoDto.getEspecialidad();

        if (consultorio != null && consultorio.getIdConsultorio() != null &&
                sede != null && sede.getIdSede() != null && especialidad != null && especialidad.getIdEspecialidad() != null) {

            ConsultorioDto consultorioExistente = serviciosConsultorio.buscarIdConsultorio(consultorio.getIdConsultorio());
            SedeDto sedeExistente = serviciosSede.buscarID(sede.getIdSede());
            EspecialidadDto especialidadExistente = serviciosEspecialidad.buscarID(especialidad.getIdEspecialidad());

            if (consultorioExistente != null && sedeExistente != null && especialidadExistente != null) {

                medicoDto.setConsultorio(consultorioExistente);
                medicoDto.setEspecialidad(especialidadExistente);
                medicoDto.setSede(sedeExistente);

                serviciosMedico.crear(medicoDto);
                return "redirect:/medico/{identificacion}";
            } else {
                return null;
            }
        } else {
            return null;
        }
    }

    @GetMapping("/inicioMedico/{identificacion}")
    public String inicioMedico(@PathVariable int identificacion, Model model, RedirectAttributes redirectAttributes) {
        redirectAttributes.addAttribute(IDENTIFICACION, identificacion);
        return "inicioMedico";
    }

    @GetMapping({"/AgendaMedico/{identificacion}"})
    public String agendaDeMedico(@PathVariable int identificacion, RedirectAttributes redirectAttributes) {
        redirectAttributes.addAttribute(IDENTIFICACION, identificacion);
        return "redirect:/MisCitasMedico/{identificacion}";
    }
}