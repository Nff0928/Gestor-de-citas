package co.ucentral.sistemas.citasmedicas.servicios;

import co.ucentral.sistemas.citasmedicas.dto.*;
import co.ucentral.sistemas.citasmedicas.entidades.*;
import co.ucentral.sistemas.citasmedicas.operaciones.OperacionesMedico;
import co.ucentral.sistemas.citasmedicas.repositorios.*;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ServiciosMedico implements OperacionesMedico {
    ModelMapper modelMapper;
    RepositorioMedico repositorioMedico;
    RepositorioRegistro repositorioRegistro;
    RepositorioEspecialidad repositorioEspecialidad;
    RepositorioSede repositorioSede;
    RepositorioConsultorio repositorioConsultorio;
    RepositorioRol repositorioRol;
    RepositorioAfiliado repositorioAfiliado;
    RepositorioConsultor repositorioConsultor;

    public ServiciosMedico(ModelMapper modelMapper, RepositorioMedico repositorioMedico, RepositorioRegistro repositorioRegistro, RepositorioEspecialidad repositorioEspecialidad, RepositorioSede repositorioSede, RepositorioConsultorio repositorioConsultorio, RepositorioRol repositorioRol, RepositorioAfiliado repositorioAfiliado, RepositorioConsultor repositorioConsultor) {
        this.modelMapper = modelMapper;
        this.repositorioMedico = repositorioMedico;
        this.repositorioRegistro = repositorioRegistro;
        this.repositorioEspecialidad = repositorioEspecialidad;
        this.repositorioSede = repositorioSede;
        this.repositorioConsultorio = repositorioConsultorio;
        this.repositorioRol = repositorioRol;
        this.repositorioAfiliado = repositorioAfiliado;
        this.repositorioConsultor = repositorioConsultor;
    }

    @Override
    public MedicoDto crear(MedicoDto medicoDto) {

        if (medicoDto == null) {
            return null;
        }

        Consultor consultor = repositorioConsultor.findByIdentificacion(medicoDto.getIdentificacion());
        Afiliado afiliado = repositorioAfiliado.findByIdentificacion(medicoDto.getIdentificacion());

        if (consultor != null || afiliado != null ) {
            return null;
        }

        if (repositorioMedico.existsByIdentificacion(medicoDto.getIdentificacion())) {
            return null;
        }

        Consultorio consultorio = repositorioConsultorio.findByIdConsultorio(medicoDto.getConsultorio().getIdConsultorio());
        Especialidad especialidad = repositorioEspecialidad.findById(medicoDto.getEspecialidad().getIdEspecialidad()).orElse(null);
        Sede sede = repositorioSede.findById(medicoDto.getSede().getIdSede()).orElse(null);

        if(consultorio !=null && especialidad !=null && sede !=null){
            if (repositorioMedico.existsById(medicoDto.getIdentificacion())) {
                return null;
            } else {
                Medico medico = repositorioMedico.save(modelMapper.map(medicoDto, Medico.class));
                return modelMapper.map(medico, MedicoDto.class);
            }
        }else{
            return null;
        }
    }
    @Override
    public MedicoDto modificar(MedicoDto medicoDto) {
        if (this.repositorioMedico.existsById(medicoDto.getIdentificacion()))
            return this.crear(medicoDto);
        else
            return null;
    }
    @Override
    public void borrar(MedicoDto medicoDto) {
        this.repositorioMedico.delete(modelMapper.map(medicoDto, Medico.class));
    }
    @Override
    public void borrar(Integer pkEntidad) {this.repositorioMedico.deleteById(pkEntidad);}
    @Override
    public List<MedicoDto> buscarTodos() {
        List<Medico> medicos = (List<Medico>) repositorioMedico.findAll();
        List<MedicoDto> medicosDto = new ArrayList<>();

        for (Medico medico : medicos) {
            MedicoDto medicoDto = new MedicoDto();
            medicoDto.setIdentificacion(medico.getIdentificacion());
            medicoDto.setNombre(medico.getNombre());
            medicoDto.setEstado(medico.getEstado());

            Consultorio consultorio = repositorioConsultorio.findByIdConsultorio(medico.getConsultorio().getIdConsultorio());
            ConsultorioDto consultorioDto = new ConsultorioDto();
            consultorioDto.setIdConsultorio(consultorio.getIdConsultorio());

            medicoDto.setConsultorio(consultorioDto);

            Especialidad especialidad = repositorioEspecialidad.findById(medico.getEspecialidad().getIdEspecialidad()).orElse(null);
            EspecialidadDto especialidadDto = new EspecialidadDto();
            assert especialidad != null;
            especialidadDto.setIdEspecialidad(especialidad.getIdEspecialidad());
            especialidadDto.setNombre(especialidad.getNombre());

            medicoDto.setEspecialidad(especialidadDto);

            Sede sede = repositorioSede.findById(medico.getSede().getIdSede()).orElse(null);
            SedeDto sedeDto = new SedeDto();
            assert sede != null;
            sedeDto.setIdSede(sede.getIdSede());
            sedeDto.setNombre(sede.getNombre());

            medicoDto.setSede(sedeDto);

            Rol rol = repositorioRol.findById(2).orElse(null);
            if (rol == null) {
                return medicosDto;
            }
            RolDto rolDto = new RolDto();
            rolDto.setIdRol(rol.getIdRol());
            medicoDto.setRol(rolDto);

            MedicoDto medicoConRegistro = obtenerIdRegistro(medicoDto);
            if (medicoConRegistro != null) {
                medicoDto.setRegistro(medicoConRegistro.getRegistro());
            }else{
                medicoDto.setRegistro(null);
            }

            medicosDto.add(medicoDto);
        }

        return medicosDto;
    }
    @Override
    public MedicoDto buscarID(Integer pkEntidad) {
        Medico medico = this.repositorioMedico.findById(pkEntidad).orElse(null);
        if (medico != null) {
            return modelMapper.map(medico, MedicoDto.class);
        } else {
            return null;
        }
    }

    public MedicoDto obtenerIdRegistro(MedicoDto medicoDto) {
        Registro registro = repositorioRegistro.findByIdUsuario(medicoDto.getIdentificacion());

        if (registro != null) {
            RegistroDto registroDto = modelMapper.map(registro, RegistroDto.class);
            medicoDto.setRegistro(registroDto);

            return medicoDto;
        } else {
            return null;
        }
    }
}
