package co.ucentral.sistemas.citasmedicas.controladores;
import co.ucentral.sistemas.citasmedicas.dto.AfiliacionDto;
import co.ucentral.sistemas.citasmedicas.entidades.Afiliado;
import co.ucentral.sistemas.citasmedicas.entidades.Consultor;
import co.ucentral.sistemas.citasmedicas.entidades.Medico;
import co.ucentral.sistemas.citasmedicas.repositorios.RepositorioAfiliado;
import co.ucentral.sistemas.citasmedicas.repositorios.RepositorioConsultor;
import co.ucentral.sistemas.citasmedicas.repositorios.RepositorioMedico;
import co.ucentral.sistemas.citasmedicas.servicios.ServiciosAfiliacion;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@Log4j2
@Controller
public class ControladoresAfiliacion {

    ServiciosAfiliacion serviciosAfiliacion;
    RepositorioAfiliado repositorioAfiliado;
    RepositorioMedico repositorioMedico;
    RepositorioConsultor repositorioConsultor;

    public ControladoresAfiliacion(ServiciosAfiliacion serviciosAfiliacion, RepositorioAfiliado repositorioAfiliado, RepositorioMedico repositorioMedico, RepositorioConsultor repositorioConsultor) {
        this.serviciosAfiliacion = serviciosAfiliacion;
        this.repositorioAfiliado = repositorioAfiliado;
        this.repositorioMedico = repositorioMedico;
        this.repositorioConsultor = repositorioConsultor;
    }

    @GetMapping("/afiliacion/{identificacion}")
    public String consultarTodos(Model model, @PathVariable int identificacion) {
        model.addAttribute("identificacion", identificacion);
        model.addAttribute("listafiliacion", serviciosAfiliacion.buscarTodos());
        return "afiliacion";
    }

    @GetMapping("/afiliacion/nueva")
    public String mostrarFormulario(Model model) {
        AfiliacionDto afiliacionDto = new AfiliacionDto();
        model.addAttribute("lafiliacion", afiliacionDto);
        return "form_afiliacion";
    }

    @PostMapping("/crearsolicitud")
    public String crearSolicitud(@ModelAttribute AfiliacionDto afiliacionDto, @RequestParam("idAfiliado") int idAfiliado) {

        Afiliado afiliado = repositorioAfiliado.findByIdentificacion(idAfiliado);
        Consultor consultor = repositorioConsultor.findByIdentificacion(idAfiliado);
        Medico medico = repositorioMedico.findByIdentificacion(idAfiliado);

        if (afiliado != null || consultor != null || medico != null) {
            return "redirect:/afiliacion/nueva?error";
        } else {
            serviciosAfiliacion.crear(afiliacionDto);
            return "redirect:/afiliacion/nueva?exito";
        }
    }

    @GetMapping("/misAfiliaciones/{identificacion}")
    public String mostrarAfiliacionesConsultor(@PathVariable int identificacion, Model model){
        List<AfiliacionDto> afiliaciones = serviciosAfiliacion.buscarConsultor(identificacion);
        model.addAttribute("afiliaciones", afiliaciones);
        return "misAfiliacionesConsultor";
    }
}
