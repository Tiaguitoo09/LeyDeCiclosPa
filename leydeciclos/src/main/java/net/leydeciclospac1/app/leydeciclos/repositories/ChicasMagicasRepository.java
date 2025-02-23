package net.leydeciclospac1.app.leydeciclos.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import net.leydeciclospac1.app.leydeciclos.entities.ChicasMagicas;

@Repository
public interface  ChicasMagicasRepository extends CrudRepository<ChicasMagicas, Long> {

    public List<ChicasMagicas> findByEstadoActual(String estadoActual);

     List<ChicasMagicas> findByName(String name);

     List<ChicasMagicas> findByCiudadDeOrigen(String ciudadDeOrigen);
 
     List<ChicasMagicas> findByEdadBetween(Integer minEdad, Integer maxEdad);
 
     List<ChicasMagicas> findAllByOrderByFechaDeContratoAsc();
    
    
}
