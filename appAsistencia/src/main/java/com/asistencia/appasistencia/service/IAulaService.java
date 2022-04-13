package com.asistencia.appasistencia.service;

import java.util.Optional;
import com.asistencia.appasistencia.models.Asistencia;
import com.asistencia.appasistencia.models.Aula;
import com.asistencia.appasistencia.models.Estudiante;

public interface IAulaService {

    Optional<Aula> buscarAulaPorId(Long id);
    Optional<Asistencia> buscarAsistenciaPorCodigo(String codigo);
    Optional<Estudiante> buscarEstudiantePorCodigo(String codigo);
    void registrarAsistencia(Asistencia asistencia);
    void actualizarAsistencia(Asistencia asistencia);
}
