package com.asistencia.appasistencia.repository;

import java.time.LocalDate;
import java.util.Optional;
import com.asistencia.appasistencia.models.Asistencia;
import com.asistencia.appasistencia.models.Aula;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IAsistenciaRepo extends JpaRepository<Asistencia,Long> {

    
    Optional<Asistencia> findByEstudiante_CodigoAndFechaIngreso(String codigo, LocalDate fechaIngreso);
}
