package com.example.demo.service;

import com.example.demo.dto.PacienteDTO;

import com.example.demo.exceptions.PacienteAlreadyExist;
import com.example.demo.utils.MapUtils;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import com.example.demo.model.pacientes;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import com.example.demo.repository.I_PacienteRepository;
import com.example.demo.serviceInterface.I_PacienteService;
import org.springframework.web.server.ResponseStatusException;
import java.util.List;
import java.util.Optional;


@RequiredArgsConstructor
@Transactional
@Service
public class PacienteServiceImpl implements I_PacienteService {

    private final I_PacienteRepository pacienteRepository;





    @Override
    public List<PacienteDTO> getAllPacientes() {

            List<pacientes> listaPacientes = pacienteRepository.findAll();



            return  MapUtils.getAll(listaPacientes,PacienteDTO.class);

    }

    @Override
    public PacienteDTO getPacientebyDNI(int dni) {
        Optional<pacientes> optionalPaciente = pacienteRepository.findByDniPaciente(dni);

        if (optionalPaciente.isPresent()) {

          return MapUtils.mapEntityToDTO(optionalPaciente.get(),PacienteDTO.class);

        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Paciente with DNI " + dni + " not found");
        }



    }

    @Override
    public void guardarPaciente(PacienteDTO pacienteDto) {
            Optional<pacientes> optionalPaciente = pacienteRepository.findByDniPaciente(pacienteDto.getDniPaciente());

            if (optionalPaciente.isPresent()) {
                throw new PacienteAlreadyExist("Paciente with DNI " + pacienteDto.getDniPaciente() + " already exists.");
            }


                pacientes pacienteEntity = MapUtils.mapDtoToEntity(pacienteDto, pacientes.class);
                pacienteRepository.save(pacienteEntity);

    }




    @Override
    public String updatearPaciente(int dni, PacienteDTO pacienteExistt) {
        Optional<pacientes> optionalPacienteExisting = pacienteRepository.findByDniPaciente(dni);



        pacientes existingPaciente = optionalPacienteExisting.orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "Paciente with ID " + dni + " not found"));

        Optional<pacientes> optionalPacienteDtoExisting = pacienteRepository.findByDniPaciente(pacienteExistt.getDniPaciente());


        if (optionalPacienteDtoExisting.isPresent() && !optionalPacienteDtoExisting.get().equals(existingPaciente)) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Paciente with DNI already exists");
        }

        existingPaciente.setApellido(pacienteExistt.getApellido());
        existingPaciente.setNombre(pacienteExistt.getNombre());
        existingPaciente.setDomicilio(pacienteExistt.getDomicilio());
        existingPaciente.setDniPaciente(pacienteExistt.getDniPaciente());
        existingPaciente.setLocalidad(pacienteExistt.getLocalidad());
        existingPaciente.setCelular(pacienteExistt.getCelular());
        existingPaciente.setProvincia(pacienteExistt.getProvincia());
        existingPaciente.setEmail(pacienteExistt.getEmail());
        existingPaciente.setTelefono(pacienteExistt.getTelefono());

        pacienteRepository.save(existingPaciente);

        return "Paciente actualizado correctamente";
    }

    @Override
    public void eliminarPaciente(int dni) {
        Optional<pacientes> optionalPaciente = pacienteRepository.findByDniPaciente(dni);

        pacientes existspaciente = optionalPaciente.orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND , "Paciente with DNI NOT FOUND"));

        pacienteRepository.deleteById(existspaciente.getId());
        }
    }













