package com.asistencia.appasistencia.controller;

import java.util.Optional;
import com.asistencia.appasistencia.models.Asistencia;
import com.asistencia.appasistencia.models.Aula;
import com.asistencia.appasistencia.models.Estudiante;
import com.asistencia.appasistencia.service.IAulaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
@RequiredArgsConstructor
@RequestMapping ("/api/aulas")
public class AulaController {

    private final IAulaService aulaService;

    @GetMapping("/buscar-aula/{id}")
    public ResponseEntity<?> buscarPorId(@PathVariable Long id){
        Optional<Aula> aulaEncontrada = aulaService.buscarAulaPorId(id);
        if(!aulaEncontrada.isPresent()){
            return new ResponseEntity<>("No se encontro el aula"+id , HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(aulaEncontrada.get(),HttpStatus.OK);
    }

    @PostMapping("/registrar-asistencia")
    public ResponseEntity<?> registrarAsistencia(@RequestBody Asistencia asistencia){
        Optional<Estudiante> estudianteEncontrado;
        String codigoEstudiante =asistencia.getEstudiante().getCodigo();
        estudianteEncontrado = aulaService.buscarEstudiantePorCodigo(asistencia.getEstudiante().getCodigo());

        if(!estudianteEncontrado.isPresent()){
            return new ResponseEntity<>("No se encontro el estudiante"+codigoEstudiante , HttpStatus.NOT_FOUND);
        }

         Optional<Asistencia> asistenciaEncontrada = aulaService.buscarAsistenciaPorCodigo(codigoEstudiante);

        if(asistenciaEncontrada.isPresent()){
            return new ResponseEntity<>("No se puede registrar 2 veces la asistencia", HttpStatus.NOT_FOUND);
        }

        asistencia.setEstudiante(estudianteEncontrado.get());
        aulaService.registrarAsistencia(asistencia);

        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("/registrar-salida")
    public ResponseEntity<?> actualizarAsistencia(@RequestBody Asistencia asistencia){
        Optional<Estudiante> estudianteEncontrado;
        String codigoEstudiante =asistencia.getEstudiante().getCodigo();
        estudianteEncontrado = aulaService.buscarEstudiantePorCodigo(asistencia.getEstudiante().getCodigo());

        if(!estudianteEncontrado.isPresent()){
            return new ResponseEntity<>("No se encontro el estudiante"+codigoEstudiante , HttpStatus.NOT_FOUND);
        }
        Optional<Asistencia> asistenciaEncontrada= aulaService.buscarAsistenciaPorCodigo(codigoEstudiante);

        if(!asistenciaEncontrada.isPresent()){
            return new ResponseEntity<>("No Hay asistencia", HttpStatus.BAD_REQUEST);
        }

        aulaService.actualizarAsistencia(asistenciaEncontrada.get());

        return new ResponseEntity<>(HttpStatus.CREATED);

    }

    @GetMapping("/buscar-asistencia/{codigoEstudiante}")
    public ResponseEntity<?> buscarAsistenciaPorCodigo(@PathVariable String codigoEstudiante){

        Optional<Asistencia> asistenciaEncontrada= aulaService.buscarAsistenciaPorCodigo(codigoEstudiante);

        if(!asistenciaEncontrada.isPresent()){
            return new ResponseEntity<>("No se encontro asistencia con ese codigo"+codigoEstudiante , HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(asistenciaEncontrada.get(), HttpStatus.OK);
    }

}
