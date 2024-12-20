package co.ucentral.sistemas.citasmedicas.controladores;
import co.ucentral.sistemas.citasmedicas.dto.ConsultorDto;
import co.ucentral.sistemas.citasmedicas.entidades.Cita;
import co.ucentral.sistemas.citasmedicas.repositorios.RepositorioAfiliado;
import co.ucentral.sistemas.citasmedicas.repositorios.RepositorioRegistro;
import co.ucentral.sistemas.citasmedicas.servicios.ServiciosCita;
import co.ucentral.sistemas.citasmedicas.servicios.ServiciosConsultor;
import co.ucentral.sistemas.citasmedicas.servicios.ServiciosRegistro;
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
public class ControladoresConsultor {

    private final ServiciosCita serviciosCita;
    ServiciosConsultor serviciosConsultor;
    ServiciosRegistro serviciosRegistro;
    RepositorioRegistro repositorioRegistro;
    RepositorioAfiliado repositorioAfiliado;
    private static final String IDENTIFICACION = "identificacion";

    public ControladoresConsultor(ServiciosCita serviciosCita, ServiciosConsultor serviciosConsultor, ServiciosRegistro serviciosRegistro, RepositorioRegistro repositorioRegistro, RepositorioAfiliado repositorioAfiliado) {
        this.serviciosCita = serviciosCita;
        this.serviciosConsultor = serviciosConsultor;
        this.serviciosRegistro = serviciosRegistro;
        this.repositorioRegistro = repositorioRegistro;
        this.repositorioAfiliado = repositorioAfiliado;
    }

    @GetMapping("/inicioConsultor/{identificacion}")
    public String iniciioConsultor(@PathVariable int identificacion, Model model, RedirectAttributes redirectAttributes) {
        redirectAttributes.addAttribute(IDENTIFICACION, identificacion);
        return "inicioConsultor";
    }

    @GetMapping({"/GenerarInforme/{identificacion}"})
    public String generarInforme( Model model, @PathVariable int identificacion,  RedirectAttributes redirectAttributes) {
        List<Cita> citas = serviciosCita.buscarTodos();
        redirectAttributes.addAttribute(IDENTIFICACION, identificacion);
        model.addAttribute("citas", citas);
        return "informe";
    }

    @GetMapping({"/consultor/{identificacion}"})
    public String consultarTodos(Model model, @PathVariable int identificacion) {
        model.addAttribute(IDENTIFICACION, identificacion);
        List<ConsultorDto> listaconsultores = this.serviciosConsultor.buscarTodos();

        for (ConsultorDto consultorDto : listaconsultores) {
            ConsultorDto consultorConRegistro = serviciosConsultor.obtenerIdRegistro(consultorDto);
            if (consultorConRegistro != null) {
                consultorDto.setRegistro(consultorConRegistro.getRegistro());
            }else{
                consultorDto.setRegistro(null);
            }
        }
        model.addAttribute("listaconsultores",listaconsultores);
        return "consultor";
    }

    @GetMapping("/consultor/nuevo/{identificacion}")
    public String mostrarFormulario(Model model, @PathVariable int identificacion) {
        model.addAttribute(IDENTIFICACION, identificacion);
        ConsultorDto consultorDto = new ConsultorDto();
        model.addAttribute("elconsultor", consultorDto);
        return "form_consultor";
    }
    @PostMapping("/crearconsultor/{identificacion}")
    public String registrarConsultor(@ModelAttribute("elconsultor") ConsultorDto consultorDto, Model model, @PathVariable int identificacion) {
        model.addAttribute(IDENTIFICACION, identificacion);
        serviciosConsultor.crear(consultorDto);
        return "redirect:/consultor/{identificacion}";
    }

    @GetMapping({"/afiliaciones/{identificacion}"})
    public String mostrarSolicitudAfiliaciones(@PathVariable int identificacion, RedirectAttributes redirectAttributes) {
        redirectAttributes.addAttribute(IDENTIFICACION, identificacion);
        return "redirect:/misAfiliaciones/{identificacion}";
    }

    @GetMapping({"/listaAfiliaciones/{identificacion}"})
    public String listaAfiliaciones(@PathVariable int identificacion, RedirectAttributes redirectAttributes) {
        redirectAttributes.addAttribute(IDENTIFICACION, identificacion);
        return "redirect:/afiliacion/{identificacion}";
    }

    @GetMapping({"/listaAfiliados/{identificacion}"})
    public String listaAfiliados(@PathVariable int identificacion, RedirectAttributes redirectAttributes) {
        redirectAttributes.addAttribute(IDENTIFICACION, identificacion);
        return "redirect:/afiliado/{identificacion}";
    }

    @GetMapping({"/listaConsultores/{identificacion}"})
    public String listaConsultor(@PathVariable int identificacion, RedirectAttributes redirectAttributes) {
        redirectAttributes.addAttribute(IDENTIFICACION, identificacion);
        return "redirect:/consultor/{identificacion}";
    }

    @GetMapping({"/listaConsultorios/{identificacion}"})
    public String listaConsultorio(@PathVariable int identificacion, RedirectAttributes redirectAttributes) {
        redirectAttributes.addAttribute(IDENTIFICACION, identificacion);
        return "redirect:/consultorio/{identificacion}";
    }

    @GetMapping({"/listaEspecialidades/{identificacion}"})
    public String listaEspecialidad(@PathVariable int identificacion, RedirectAttributes redirectAttributes) {
        redirectAttributes.addAttribute(IDENTIFICACION, identificacion);
        return "redirect:/especialidad/{identificacion}";
    }

    @GetMapping({"/listaMedicos/{identificacion}"})
    public String listaMedico(@PathVariable int identificacion, RedirectAttributes redirectAttributes) {
        redirectAttributes.addAttribute(IDENTIFICACION, identificacion);
        return "redirect:/medico/{identificacion}";
    }

    @GetMapping({"/listaRoles/{identificacion}"})
    public String listaRol(@PathVariable int identificacion, RedirectAttributes redirectAttributes) {
        redirectAttributes.addAttribute(IDENTIFICACION, identificacion);
        return "redirect:/rol/{identificacion}";
    }

    @GetMapping({"/listaSedes/{identificacion}"})
    public String listaSede(@PathVariable int identificacion, RedirectAttributes redirectAttributes) {
        redirectAttributes.addAttribute(IDENTIFICACION, identificacion);
        return "redirect:/sede/{identificacion}";
    }

    @GetMapping({"/listaUsuarios/{identificacion}"})
    public String listausuario(@PathVariable int identificacion, RedirectAttributes redirectAttributes) {
        redirectAttributes.addAttribute(IDENTIFICACION, identificacion);
        return "redirect:/usuarios/{identificacion}";
    }
}