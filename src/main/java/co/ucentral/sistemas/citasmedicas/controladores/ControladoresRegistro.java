package co.ucentral.sistemas.citasmedicas.controladores;
import co.ucentral.sistemas.citasmedicas.dto.RegistroDto;
import co.ucentral.sistemas.citasmedicas.entidades.Afiliado;
import co.ucentral.sistemas.citasmedicas.entidades.Consultor;
import co.ucentral.sistemas.citasmedicas.entidades.Medico;
import co.ucentral.sistemas.citasmedicas.entidades.Registro;
import co.ucentral.sistemas.citasmedicas.repositorios.*;
import co.ucentral.sistemas.citasmedicas.servicios.ServiciosRegistro;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Log4j2
@Controller
public class ControladoresRegistro {

    ServiciosRegistro serviciosRegistro;
    RepositorioMedico repositorioMedico;
    RepositorioConsultor repositorioConsultor;
    RepositorioAfiliado repositorioAfiliado;
    RepositorioRegistro repositorioRegistro;
    private static final String IDENTIFICACION = "identificacion";

    public ControladoresRegistro(ServiciosRegistro serviciosRegistro, RepositorioMedico repositorioMedico, RepositorioConsultor repositorioConsultor, RepositorioAfiliado repositorioAfiliado, RepositorioRegistro repositorioRegistro) {
        this.serviciosRegistro = serviciosRegistro;
        this.repositorioMedico = repositorioMedico;
        this.repositorioConsultor = repositorioConsultor;
        this.repositorioAfiliado = repositorioAfiliado;
        this.repositorioRegistro = repositorioRegistro;
    }

    @GetMapping({"/usuarios/{identificacion}"})
    public String consultarTodos(Model model, @PathVariable int identificacion) {
        model.addAttribute(IDENTIFICACION, identificacion);
        model.addAttribute("listaregistro", this.serviciosRegistro.buscarTodos());
        return "usuarios";
    }

    @GetMapping("/registro/nuevo")
    public String mostrarFormulario(Model model) {
        RegistroDto registroDto = new RegistroDto();
        model.addAttribute("elregistro", registroDto);
        return "form_registro";
    }

    @PostMapping("/crearegistro")
    public String registrarRegistro(@ModelAttribute("elregistro") RegistroDto registroDto) {
        registroDto.setFechaRegistro(LocalDateTime.now());
        Consultor consultor = repositorioConsultor.findByIdentificacionAndNombre(registroDto.getIdUsuario(), registroDto.getNombre());
        Medico medico = repositorioMedico.findByIdentificacionAndNombre(registroDto.getIdUsuario(), registroDto.getNombre());
        Afiliado afiliado = repositorioAfiliado.findByIdentificacionAndNombre(registroDto.getIdUsuario(), registroDto.getNombre());

        if (consultor != null || medico != null || afiliado != null) {
            if (repositorioRegistro.existsByIdUsuario(registroDto.getIdUsuario())) {
                return "redirect:/registro/nuevo?errorRegistro";
            } else {
                serviciosRegistro.crear(registroDto);
                return "redirect:/registro/nuevo?exito";
            }
        } else {
            return "redirect:/registro/nuevo?error";
        }
    }

    @GetMapping("/iniciosesion")
    public String mostrarFormularioInicioSesion(Model model) {
        model.addAttribute("elregistro", new RegistroDto());
        return "iniciar_sesion";
    }

    @PostMapping("/sesion")
    public String iniciarSesion(@ModelAttribute("elregistro") RegistroDto registroDto, @RequestParam("idUsuario") int idUsuario, @RequestParam("contrasenia") String contrasenia, RedirectAttributes redirectAttributes) {

        Registro registro = repositorioRegistro.findByIdUsuario(idUsuario);

        if (registro == null) {
            return "redirect:/iniciosesion?error";
        }

        if (serviciosRegistro.validarCredenciales(idUsuario, contrasenia)) {
            Medico medico = repositorioMedico.findByIdentificacion(idUsuario);
            Consultor consultor = repositorioConsultor.findByIdentificacion(idUsuario);
            Afiliado afiliado = repositorioAfiliado.findByIdentificacion(idUsuario);

                if (medico != null) {
                   redirectAttributes.addAttribute(IDENTIFICACION, idUsuario);
                    return "redirect:/inicioMedico/{identificacion}";
                } else if (afiliado != null) {
                    redirectAttributes.addAttribute(IDENTIFICACION, idUsuario);
                    return "redirect:/inicioAfiliado/{identificacion}";
                } else if (consultor != null) {
                    redirectAttributes.addAttribute(IDENTIFICACION, idUsuario);
                    return "redirect:/inicioConsultor/{identificacion}";
                } else {
                    return "redirect:/iniciosesion?error";
                }
        } else {
            return "redirect:/iniciosesion?errorUsuario";
        }
    }
}
