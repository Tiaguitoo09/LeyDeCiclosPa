package net.leydeciclospac1.app.leydeciclos.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.leydeciclospac1.app.leydeciclos.entities.ChicasMagicas;
import net.leydeciclospac1.app.leydeciclos.repositories.ChicasMagicasRepository;

@Service
public class ChicasMagicasService {
    @Autowired
    ChicasMagicasRepository chicasMagicasRepository;

       
       public ArrayList<ChicasMagicas> obtenerChicasMagicas() {
        return (ArrayList<ChicasMagicas>) chicasMagicasRepository.findAll();
    }

    
    public ChicasMagicas guardarChicaMagica(ChicasMagicas chicaMagica) {
        return chicasMagicasRepository.save(chicaMagica);
    }

    
    public Optional<ChicasMagicas> obtenerPorId(Long id) {
        return chicasMagicasRepository.findById(id);
    }

    
    public ChicasMagicas actualizarChicaMagica(Long id, ChicasMagicas detallesChicaMagica) {
        return chicasMagicasRepository.findById(id).map(chica -> {
            chica.setName(detallesChicaMagica.getName());
            chica.setEdad(detallesChicaMagica.getEdad());
            chica.setCiudadDeOrigen(detallesChicaMagica.getCiudadDeOrigen());
            chica.setEstadoActual(detallesChicaMagica.getEstadoActual());
            chica.setFechaDeContrato(detallesChicaMagica.getFechaDeContrato());
            return chicasMagicasRepository.save(chica);
        }).orElseThrow(() -> new RuntimeException("Chica mágica no encontrada"));
    }

    
    public boolean eliminarChicaMagica(Long id) {
        try {
            chicasMagicasRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    
    public List<ChicasMagicas> filtrarPorEstado(String estadoActual) {
        return chicasMagicasRepository.findByEstadoActual(estadoActual);
    }
}
