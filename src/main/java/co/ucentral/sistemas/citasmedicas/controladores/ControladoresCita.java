package co.ucentral.sistemas.citasmedicas.controladores;

import co.ucentral.sistemas.citasmedicas.entidades.Afiliado;
import co.ucentral.sistemas.citasmedicas.entidades.Cita;
import co.ucentral.sistemas.citasmedicas.servicios.ServiciosAfiliado;
import co.ucentral.sistemas.citasmedicas.servicios.ServiciosCita;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Log4j2
@Controller
public class ControladoresCita {

    private final ServiciosCita serviciosCita;
    private final ServiciosAfiliado serviciosAfiliado;
    private final ModelMapper modelMapper;
    private static final String ERROR = "error";
    @Autowired
    public ControladoresCita(ServiciosCita serviciosCita, ServiciosAfiliado serviciosAfiliado, ModelMapper modelMapper) {
        this.serviciosCita = serviciosCita;
        this.serviciosAfiliado = serviciosAfiliado;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/Agendar/{identificacion}")
   public String listarCitas(Model model, @PathVariable int identificacion) {
       Afiliado afiliado = serviciosAfiliado.buscarID(identificacion);
        if (afiliado != null) {
            model.addAttribute("identificacion", identificacion);
           model.addAttribute("listaCitasT", serviciosCita.obtenerCitasDisponibles());
            model.addAttribute("afiliado", afiliado);
            return "agendarcitas";
       } else {
           // Manejar el caso en el que no se encuentre el afiliado con el ID dado
            return ERROR; // Por ejemplo, redirigir a una página de error
        }
   }

  @GetMapping("/Agendar/Confirmacion/{idCita}/{identificacion}")
    public String buscarCitaPorId(@PathVariable int idCita, @PathVariable int identificacion, Model model) {
       Cita cita = serviciosCita.buscarID(idCita);

       if (cita != null) {
           Afiliado afiliado = serviciosAfiliado.buscarID(identificacion);

           if (afiliado != null) {
               cita.setEstado("Programada");
               cita.setAfiliado(afiliado);
              serviciosCita.modificar(modelMapper.map(cita, Cita.class));
               model.addAttribute("cita", cita);
               model.addAttribute("identificacion", identificacion);
               model.addAttribute("afiliado", afiliado);
               return "confirmacion";
           } else {
               return ERROR;
           }
        } else {
           return ERROR;
       }
    }


    @GetMapping("/MisCitas/{identificacion}")
    public String mostrarCitasAgendadas(@PathVariable int identificacion, Model model){
        List<Cita> citas = serviciosCita.buscarPorAfiliado(identificacion);
        model.addAttribute("citas", citas);
        return "misCitas";
    }

    @GetMapping("/MisCitasMedico/{identificacion}")
    public String mostrarCitasAgendadasMedico(@PathVariable int identificacion, Model model){
        List<Cita> citas = serviciosCita.buscarPorMedico(identificacion);
        List<Cita> citasConAfiliado = citas.stream()
                .filter(cita -> cita.getAfiliado() != null)
                .toList();
        model.addAttribute("citas", citasConAfiliado);
        return "misCitasMedico";
    }


    @GetMapping("/CancelarCita/{idCita}")
    public String cancelarCita(@PathVariable int idCita, Model model){
        Cita cita = serviciosCita.buscarID(idCita);
        if (cita.getEstado().equals("Programada")) {
            cita.setEstado("Cancelada");
            serviciosCita.modificar(cita);
            return "redirect:/MisCitas/" + cita.getAfiliado().getIdentificacion();
        } else {
            // Si la cita no está en estado "Programada", agrega un mensaje de error
            model.addAttribute(ERROR, "No se puede cancelar una cita que no está programada.");
            return "redirect:/MisCitas/" + cita.getAfiliado().getIdentificacion();
        }
    }

    @PostMapping("/citas/{idCita}/observacion")
    public String agregarObservacion(@PathVariable int idCita, @RequestParam String observacion, RedirectAttributes redirectAttributes) {

        Cita cita = serviciosCita.buscarID(idCita);
            if (!cita.getEstado().equals("Finalizada")) {
                cita.setObservacion(observacion);
                cita.setEstado("Finalizada");
                serviciosCita.modificar(cita);
            } else {
                // Si la cita ya está finalizada, agrega un mensaje de error
                redirectAttributes.addFlashAttribute(ERROR, "No se puede agregar una observación a una cita que ya está finalizada.");
            }
        // Redirige al usuario a la página de citas
        return "redirect:/MisCitasMedico/" + cita.getMedico().getIdentificacion();
    }
}