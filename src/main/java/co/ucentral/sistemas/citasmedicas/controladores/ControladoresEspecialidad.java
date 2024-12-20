package co.ucentral.sistemas.citasmedicas.controladores;
import co.ucentral.sistemas.citasmedicas.dto.EspecialidadDto;
import co.ucentral.sistemas.citasmedicas.servicios.ServiciosEspecialidad;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Log4j2
@Controller
public class ControladoresEspecialidad {

    ServiciosEspecialidad serviciosEspecialidad;
    private static final String IDENTIFICACION = "identificacion";

    public ControladoresEspecialidad(ServiciosEspecialidad serviciosEspecialidad) {
        this.serviciosEspecialidad = serviciosEspecialidad;
    }

    @GetMapping({  "/especialidad/{identificacion}"})
    public String consultarTodos(Model model, @PathVariable int identificacion) {
        model.addAttribute(IDENTIFICACION, identificacion);
        model.addAttribute("listaespecialidad",this.serviciosEspecialidad.buscarTodos());
        return "especialidad";
    }

    @GetMapping("/especialidad/nuevo/{identificacion}")
    public String mostrarFormulario(Model model, @PathVariable int identificacion) {
        model.addAttribute(IDENTIFICACION, identificacion);
        EspecialidadDto especialidadDto = new EspecialidadDto();
        model.addAttribute("laespecialidad", especialidadDto);
        return "form_especialidad";
    }
    @PostMapping("/crearespecialidad/{identificacion}")
    public String registrarEspecialidad(@ModelAttribute("laespecialidad") EspecialidadDto especialidadDto, Model model, @PathVariable int identificacion) {
        model.addAttribute(IDENTIFICACION, identificacion);
        serviciosEspecialidad.crear(especialidadDto);
        return "redirect:/especialidad/{identificacion}";
    }
}
