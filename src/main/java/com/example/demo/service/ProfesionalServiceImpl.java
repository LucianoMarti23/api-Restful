package com.example.demo.service;
import com.example.demo.dto.ProfesionalDTO;
import com.example.demo.model.profesionales;
import com.example.demo.repository.I_ProfesionalesRepository;
import com.example.demo.serviceInterface.I_ProfesionalService;
import com.example.demo.utils.MapUtils;
import jakarta.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@RequiredArgsConstructor
@Service
@Transactional
public class ProfesionalServiceImpl implements I_ProfesionalService {

  private final I_ProfesionalesRepository profesionalesRepository;

  @Override
  public List<ProfesionalDTO> getAllProfesional() {

    return MapUtils.getAll(profesionalesRepository.findAll(), ProfesionalDTO.class);
  }

  @Override
  public ProfesionalDTO getProfesionalbyDNI(int dni) {
    return profesionalesRepository
        .findByDniProfesional(dni)
        .map(
            existingProfesional ->
                MapUtils.mapEntityToDTO(existingProfesional, ProfesionalDTO.class))
        .orElseThrow(
            () ->
                new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Profesional with ID " + dni + " not found"));
  }

  public void guardarProfesional(ProfesionalDTO profesionalDto) {
    Optional<profesionales> optionalPaciente =
        profesionalesRepository.findByDniProfesional(profesionalDto.getDni_profesional());

    optionalPaciente.ifPresent(
        paciente -> {
          throw new ResponseStatusException(HttpStatus.CONFLICT, "Paciente already exists.");
        });

    profesionales pacienteEntity = MapUtils.mapDtoToEntity(profesionalDto, profesionales.class);
    profesionalesRepository.save(pacienteEntity);
  }

  @Override
  public void updatearProfesional(int dni, ProfesionalDTO nuevoProfesional) {
    {
      Optional<profesionales> optionalPacienteExisting =
          profesionalesRepository.findByDniProfesional(dni);

      profesionales existingPaciente =
          optionalPacienteExisting.orElseThrow(
              () ->
                  new ResponseStatusException(
                      HttpStatus.NOT_FOUND, "Paciente with ID " + dni + " not found"));

      Optional<profesionales> optionalPacienteDtoExisting =
          profesionalesRepository.findByDniProfesional(nuevoProfesional.getDni_profesional());

      if (optionalPacienteDtoExisting.isPresent()
          && !optionalPacienteDtoExisting.get().equals(existingPaciente)) {
        throw new ResponseStatusException(HttpStatus.CONFLICT, "Paciente with DNI already exists");
      }

      existingPaciente.setApellido(nuevoProfesional.getApellido());
      existingPaciente.setNombre(nuevoProfesional.getNombre());
      existingPaciente.setDniProfesional(nuevoProfesional.getDni_profesional());
      existingPaciente.setMatricula(nuevoProfesional.getMatricula());
      existingPaciente.setCelular(nuevoProfesional.getCelular());
      existingPaciente.setTitulo_profesional(nuevoProfesional.getTitulo_profesional());
      existingPaciente.setEmail(nuevoProfesional.getEmail());

      profesionalesRepository.save(existingPaciente);
    }
  }

  @Override
  public void eliminarProfesional(int dni) {
    Optional<profesionales> optionalPaciente = profesionalesRepository.findByDniProfesional(dni);

    profesionales existspaciente = optionalPaciente.orElseThrow(() ->
            new ResponseStatusException(HttpStatus.NOT_FOUND , "Paciente with DNI NOT FOUND"));

    profesionalesRepository.delete(existspaciente);
  }
}
