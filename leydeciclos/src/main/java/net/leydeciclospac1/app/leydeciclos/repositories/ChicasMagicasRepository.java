package net.leydeciclospac1.app.leydeciclos.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import net.leydeciclospac1.app.leydeciclos.entities.ChicasMagicas;

@Repository // Indica que esta interfaz es un componente que interactúa con la base de datos
public interface ChicasMagicasRepository extends CrudRepository<ChicasMagicas, Long> {

    // Busca chicas mágicas por su estado actual (por ejemplo, activa/inactiva)
    public List<ChicasMagicas> findByEstadoActual(String estadoActual);

    // Busca chicas mágicas por nombre
    List<ChicasMagicas> findByName(String name);

    // Busca chicas mágicas por su ciudad de origen
    List<ChicasMagicas> findByCiudadDeOrigen(String ciudadDeOrigen);

    // Obtiene todas las chicas mágicas ordenadas por la fecha de contrato de manera ascendente
    List<ChicasMagicas> findAllByOrderByFechaDeContratoAsc();
}
