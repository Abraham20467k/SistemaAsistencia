package com.asistencia.appasistencia.service.impl;

import java.time.LocalDate;
import java.util.Optional;
import com.asistencia.appasistencia.models.Asistencia;
import com.asistencia.appasistencia.models.Aula;
import com.asistencia.appasistencia.models.Estudiante;
import com.asistencia.appasistencia.repository.IAsistenciaRepo;
import com.asistencia.appasistencia.repository.IAulaRepo;
import com.asistencia.appasistencia.repository.IEstudianteRepo;
import com.asistencia.appasistencia.service.IAulaService;
import lombok.RequiredArgsConstructor;
import org.apache.tomcat.jni.Local;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional // 
public class AulaServiceImpl implements IAulaService {
    
    private final IAsistenciaRepo asistenciaRepo;
    private final IEstudianteRepo estudianteRepo;
    private final IAulaRepo aulaRepo;

    @Override
    public Optional<Aula> buscarAulaPorId(Long id) {
        return  aulaRepo.findById(id);
    }

    @Override
    public Optional<Asistencia> buscarAsistenciaPorCodigo(String codigo) {

        LocalDate fechaActual = LocalDate.now();
        return asistenciaRepo.findByEstudiante_CodigoAndFechaIngreso(codigo,fechaActual);
    }

    @Override
    public Optional<Estudiante> buscarEstudiantePorCodigo(String codigo) {
        return estudianteRepo.findByCodigo(codigo);
    }

    @Override
    public void registrarAsistencia(Asistencia asistencia) {
        LocalDate fechaActual = LocalDate.now();
        asistencia.setFechaIngreso(fechaActual);
        asistencia.setIngresoConfirmado(true);
        asistencia.setSalidaConfirmado(false);
        asistenciaRepo.save(asistencia);
    }

    @Override
    public void actualizarAsistencia(Asistencia asistencia) {
        asistencia.setSalidaConfirmado(true);
        asistenciaRepo.save(asistencia);
    }
}
