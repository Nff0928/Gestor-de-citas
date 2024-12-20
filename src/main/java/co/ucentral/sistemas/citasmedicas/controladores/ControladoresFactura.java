package co.ucentral.sistemas.citasmedicas.controladores;
import co.ucentral.sistemas.citasmedicas.dto.FacturaDto;
import co.ucentral.sistemas.citasmedicas.servicios.ServiciosFactura;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Log4j2
@Controller
public class ControladoresFactura{


    ServiciosFactura serviciosFactura;

    @Autowired
    public ControladoresFactura(ServiciosFactura serviciosCita) {
        this.serviciosFactura = serviciosCita;
    }

    @GetMapping({  "/cntfactura"})
    public String consultarTodos(Model model){
        model.addAttribute("listafactura",this.serviciosFactura.buscarTodos());
        return "factura";
    }

    @GetMapping("/factura/nuevo")
    public String mostrarFormulario(Model model){
        FacturaDto facturaDto = new FacturaDto();
        model.addAttribute("lafactura", facturaDto);
        return "crear_factura";
    }
    @PostMapping("/factura")
    public String registrarFactura(@ModelAttribute("lafactura") FacturaDto facturaDto) {
        serviciosFactura.crear(facturaDto);
        return "redirect:/cntfactura";

    }
}