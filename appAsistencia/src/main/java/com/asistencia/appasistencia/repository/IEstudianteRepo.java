package com.asistencia.appasistencia.repository;

import java.util.Optional;
import com.asistencia.appasistencia.models.Estudiante;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IEstudianteRepo extends JpaRepository<Estudiante,Long> {

    
    Optional<Estudiante> findByCodigo(String codigo);
}
