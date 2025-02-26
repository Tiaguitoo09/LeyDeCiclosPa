package net.leydeciclospac1.app.leydeciclos.services;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.leydeciclospac1.app.leydeciclos.Exceptions.ChicasMagicasExceptions;
import net.leydeciclospac1.app.leydeciclos.entities.ChicasMagicas;
import net.leydeciclospac1.app.leydeciclos.repositories.ChicasMagicasRepository;

@Service // Anotación que marca esta clase como un servicio de Spring, permitiendo la inyección de dependencias
public class ChicasMagicasService {
    
    @Autowired // Inyección del repositorio para interactuar con la base de datos
    ChicasMagicasRepository chicasMagicasRepository;

    // Lista de estados válidos para las chicas mágicas
    private static final List<String> ESTADOS_VALIDOS_CHICAS = Arrays.asList("activa", "desaparecida", "rescatada");

    // Método privado para validar si el estado proporcionado es válido
    private void validarEstado(String estado) {
        if (!ESTADOS_VALIDOS_CHICAS.contains(estado.toLowerCase())) {
            throw new ChicasMagicasExceptions("Estado inválido. Los estados válidos son: " + ESTADOS_VALIDOS_CHICAS);
        }
    }

    // Obtener todas las chicas mágicas almacenadas
    public ArrayList<ChicasMagicas> obtenerChicasMagicas() {
        return (ArrayList<ChicasMagicas>) chicasMagicasRepository.findAll();
    }

    // Guardar una nueva chica mágica después de validar su estado
    public ChicasMagicas guardarChicaMagica(ChicasMagicas chicaMagica) {
        validarEstado(chicaMagica.getEstadoActual()); // Asegura que el estado es correcto antes de guardar
        return chicasMagicasRepository.save(chicaMagica); // Guarda la entidad en la base de datos
    }

    // Obtener una chica mágica por su ID
    public Optional<ChicasMagicas> obtenerPorId(Long id) {
        return chicasMagicasRepository.findById(id); // Retorna un Optional por si el ID no existe
    }

    // Actualizar los datos de una chica mágica
    public ChicasMagicas actualizarChicaMagica(Long id, ChicasMagicas detallesChicaMagica) {
        validarEstado(detallesChicaMagica.getEstadoActual()); // Verifica el estado antes de actualizar
        // Busca la chica por ID y actualiza sus datos si existe
        return chicasMagicasRepository.findById(id).map(chica -> {
            chica.setName(detallesChicaMagica.getName());
            chica.setEdad(detallesChicaMagica.getEdad());
            chica.setCiudadDeOrigen(detallesChicaMagica.getCiudadDeOrigen());
            chica.setEstadoActual(detallesChicaMagica.getEstadoActual());
            chica.setFechaDeContrato(detallesChicaMagica.getFechaDeContrato());
            return chicasMagicasRepository.save(chica); // Guarda los cambios
        }).orElseThrow(() -> new RuntimeException("Chica mágica no encontrada")); // Error si no existe
    }

    // Eliminar una chica mágica por su ID
    public boolean eliminarChicaMagica(Long id) {
        try {
            chicasMagicasRepository.deleteById(id); // Borra el registro
            return true; // Si tiene éxito
        } catch (Exception e) {
            return false; // Si ocurre algún error
        }
    }

    // Filtrar chicas mágicas según su estado
    public List<ChicasMagicas> filtrarPorEstado(String estadoActual) {
        validarEstado(estadoActual); // Valida el estado antes de filtrar
        return chicasMagicasRepository.findByEstadoActual(estadoActual); // Busca por estado
    }
}
