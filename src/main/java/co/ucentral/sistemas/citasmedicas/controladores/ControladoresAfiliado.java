package co.ucentral.sistemas.citasmedicas.controladores;
import co.ucentral.sistemas.citasmedicas.dto.AfiliadoDto;
import co.ucentral.sistemas.citasmedicas.servicios.ServiciosAfiliado;
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
public class ControladoresAfiliado {

    ServiciosAfiliado serviciosAfiliado;
    private static final String IDENTIFICACION = "identificacion";
    public ControladoresAfiliado(ServiciosAfiliado serviciosAfiliado) {
        this.serviciosAfiliado = serviciosAfiliado;
    }

    @GetMapping("/inicioAfiliado/{identificacion}")
    public String inicioAfiliado(@PathVariable int identificacion, Model model,RedirectAttributes redirectAttributes) {
        redirectAttributes.addAttribute(IDENTIFICACION, identificacion);
        return "inicioAfiliado";
    }
    @GetMapping({"/cliente/{identificacion}"})
    public String cliente(@PathVariable int identificacion, RedirectAttributes redirectAttributes) {
        redirectAttributes.addAttribute(IDENTIFICACION, identificacion);
        return "redirect:/Agendar/{identificacion}";
    }

    @GetMapping({"/CitasAgendadas/{identificacion}"})
    public String citasAgendadas(@PathVariable int identificacion, RedirectAttributes redirectAttributes) {
        redirectAttributes.addAttribute(IDENTIFICACION, identificacion);
        return "redirect:/MisCitas/{identificacion}";
    }

    @GetMapping({"/afiliado/{identificacion}"})
    public String consultarTodos(Model model, @PathVariable int identificacion) {
        model.addAttribute(IDENTIFICACION, identificacion);
        List<AfiliadoDto> listaafiliados =this.serviciosAfiliado.buscarTodos();

        for (AfiliadoDto afiliadoDto : listaafiliados) {
            AfiliadoDto afiliadoConRegistro = serviciosAfiliado.obtenerIdRegistro(afiliadoDto);
            AfiliadoDto afiliadoConAfiliacion = serviciosAfiliado.obtenerIdAfiliacion(afiliadoDto);
            if (afiliadoConRegistro != null) {
                afiliadoDto.setRegistro(afiliadoConRegistro.getRegistro());
            }
            if(afiliadoConAfiliacion != null){
                afiliadoDto.setAfiliacion(afiliadoConAfiliacion.getAfiliacion());
            }
        }
        model.addAttribute("listaafiliados",listaafiliados);
        return "afiliado";
    }
    @GetMapping("/afiliado/nuevo/{identificacion}")
    public String mostrarFormulario(Model model, @PathVariable int identificacion) {
        model.addAttribute(IDENTIFICACION, identificacion);
        AfiliadoDto afiliadoDto = new AfiliadoDto();
        model.addAttribute("elafiliado", afiliadoDto);
        return "form_afiliado";
    }

    @PostMapping("/crearafiliado/{identificacion}")
    public String registrarAfiliado(@ModelAttribute("elafiliado") AfiliadoDto afiliadoDto, Model model, @PathVariable int identificacion) {
        model.addAttribute(IDENTIFICACION, identificacion);
        serviciosAfiliado.crear(afiliadoDto);
        return "redirect:/afiliado/{identificacion}";
    }
}