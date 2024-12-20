package co.ucentral.sistemas.citasmedicas.servicios;
import co.ucentral.sistemas.citasmedicas.dto.FacturaDto;
import co.ucentral.sistemas.citasmedicas.entidades.Factura;
import co.ucentral.sistemas.citasmedicas.operaciones.OperacionesFactura;
import co.ucentral.sistemas.citasmedicas.repositorios.RepositorioFactura;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ServiciosFactura implements OperacionesFactura {
    ModelMapper modelMapper;
    RepositorioFactura repositorioFactura;

    public ServiciosFactura(ModelMapper modelMapper, RepositorioFactura repositorioFactura) {
        this.modelMapper = modelMapper;
        this.repositorioFactura = repositorioFactura;
    }

    @Override
    public FacturaDto crear(FacturaDto facturaDto) {
        if (facturaDto != null){
            Factura factura =   repositorioFactura.save(modelMapper.map(facturaDto, Factura.class));
            return modelMapper.map(factura, FacturaDto.class);
        }

        else
            return null;
    }

    @Override
    public FacturaDto modificar(FacturaDto facturaDto) {
        if (this.repositorioFactura.existsById(facturaDto.getIdFactura()))
            return this.crear(facturaDto);
        else
            return null;
    }

    @Override
    public void borrar(FacturaDto facturaDto) {this.repositorioFactura.delete(modelMapper.map(facturaDto, Factura.class));}

    @Override
    public void borrar(Integer pkEntidad) {this.repositorioFactura.deleteById(pkEntidad);}

    @Override
    public List<FacturaDto> buscarTodos() {
        TypeToken<List<FacturaDto>> typeToken = new TypeToken<>() {
        };
        return modelMapper.map(this.repositorioFactura.findAll(), typeToken.getType());
    }

    @Override
    public FacturaDto buscarID(Integer pkEntidad) {
        return modelMapper.map(this.buscarID(pkEntidad), FacturaDto.class);
    }
}