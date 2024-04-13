package com.example.demo.service;
import com.example.demo.dto.PacienteDTO;
import com.example.demo.dto.ProfesionalDTO;
import com.example.demo.exceptions.PacienteAlreadyExist;
import com.example.demo.model.profesionales;
import com.example.demo.repository.I_ProfesionalesRepository;
import com.example.demo.serviceInterface.I_ProfesionalService;
import com.example.demo.utils.MapUtils;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
@Transactional
public class ProfesionalServiceImpl implements I_ProfesionalService {

    private final I_ProfesionalesRepository profesionalesRepository;



    private static profesionales getProfesional(ProfesionalDTO profesionalDTO) {

        profesionales nuevoProfesional = new profesionales();

        nuevoProfesional.setNombre(profesionalDTO.getNombre());
        nuevoProfesional.setCelular(profesionalDTO.getCelular());
        nuevoProfesional.setEmail(profesionalDTO.getEmail());
        nuevoProfesional.setApellido(profesionalDTO.getApellido());
        nuevoProfesional.setTitulo_profesional(profesionalDTO.getTitulo_profesional());
        nuevoProfesional.setMatricula(profesionalDTO.getMatricula());
        nuevoProfesional.setDniProfesional(profesionalDTO.getDni_profesional());

        return nuevoProfesional;
    }

    private ProfesionalDTO mapProfesionalToDTO(profesionales profesional) {

        ProfesionalDTO profesionalDTO = new ProfesionalDTO();
        profesionalDTO.setNombre(profesional.getNombre());
        profesionalDTO.setApellido(profesional.getApellido());
        profesionalDTO.setCelular(profesional.getCelular());
        profesionalDTO.setEmail(profesional.getEmail());
        profesionalDTO.setDni_profesional(profesional.getDniProfesional());
        profesionalDTO.setTitulo_profesional(profesional.getTitulo_profesional());
        profesionalDTO.setMatricula(profesional.getMatricula());


        return profesionalDTO;

    }








    @Override
    public List<?> getAllProfesional() {
        try{
           List <profesionales> listProfesionales = profesionalesRepository.findAll();


            return  MapUtils.getAll(listProfesionales, ProfesionalDTO.class);
        }catch (Exception e) {

            throw new RuntimeException("Failed to retrieve all Profesionales. Reason: " + e.getMessage());
        }
    }


    @Override
    public Optional<ProfesionalDTO> getProfesionalbyDNI(int dni) {
        Optional<profesionales> optionalProfesional = profesionalesRepository.findByDniProfesional(dni);
        if (optionalProfesional.isPresent()) {

            profesionales profesional = optionalProfesional.get();
            ProfesionalDTO profesional_DTO = mapProfesionalToDTO(profesional);
            return Optional.of(profesional_DTO);


        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Paciente with ID " + dni + " not found");
        }
    }

    @Override
    public void guardarProfesional(ProfesionalDTO profesional) {
        Optional<profesionales> optionalPaciente = profesionalesRepository.findByDniProfesional(profesional.getDni_profesional());

        if (optionalPaciente.isPresent()) {
            throw new PacienteAlreadyExist("Profesional with ID " + profesional.getDni_profesional() + " already exists.");
        }

        try {
            profesionales profesionalNew = getProfesional(profesional);
            profesionalesRepository.save(profesionalNew);
        } catch (PacienteAlreadyExist e) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, e.getMessage());
        }
    }

    @Override
    public void updatearProfesional(int dni, ProfesionalDTO nuevoProfesional) {
        Optional<profesionales> optionalProfesionalExisting = profesionalesRepository.findByDniProfesional(dni);
        Optional<profesionales> optionalProfesionalDtoExisting = profesionalesRepository.findByDniProfesional(nuevoProfesional.getDni_profesional());

        profesionales existingProfesional = optionalProfesionalExisting.orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "Profesional with ID " + dni + " not found"));


        if (optionalProfesionalDtoExisting.isEmpty()) {

            existingProfesional.setApellido(nuevoProfesional.getApellido());
            existingProfesional.setNombre(nuevoProfesional.getNombre());
            existingProfesional.setMatricula(nuevoProfesional.getMatricula());
            existingProfesional.setDniProfesional(nuevoProfesional.getDni_profesional());
            existingProfesional.setEmail(nuevoProfesional.getEmail());
            existingProfesional.setCelular(nuevoProfesional.getCelular());
            existingProfesional.setTitulo_profesional(nuevoProfesional.getTitulo_profesional());


            profesionalesRepository.save(existingProfesional);


        }


    }








    @Override
    public void eliminarProfesional(int dni) {
        Optional<profesionales> optionalProfesional = profesionalesRepository.findByDniProfesional(dni);
        if (optionalProfesional.isPresent()) {
            profesionalesRepository.deleteById(optionalProfesional.get().getId());
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Profesional with ID " + dni + " not found");
        }

    }
}
