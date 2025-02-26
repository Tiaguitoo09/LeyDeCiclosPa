package net.leydeciclospac1.app.leydeciclos.controllers;


import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import net.leydeciclospac1.app.leydeciclos.entities.ChicasMagicas;
import net.leydeciclospac1.app.leydeciclos.services.ChicasMagicasService;


@RestController
@RequestMapping("chicas-magicas")
@CrossOrigin(origins = "*")
public class ChicasMagicasController {

    @Autowired
    private ChicasMagicasService chicasMagicasService;

    // Obtener todas las chicas mágicas
    @GetMapping
    public ResponseEntity<ArrayList<ChicasMagicas>> obtenerChicasMagicas() {
        return ResponseEntity.ok(chicasMagicasService.obtenerChicasMagicas());
    }

    // Guardar una nueva chica mágica
    @PostMapping
    public ResponseEntity<ChicasMagicas> guardarChicaMagica(@RequestBody ChicasMagicas chicaMagica) {
        return ResponseEntity.ok(chicasMagicasService.guardarChicaMagica(chicaMagica));
    }

    // Obtener una chica mágica por su ID
    @GetMapping("/{id}")
    public ResponseEntity<?> obtenerChicaMagicaPorId(@PathVariable Long id) {
        Optional<ChicasMagicas> chica = chicasMagicasService.obtenerPorId(id);
        return chica.isPresent() ? ResponseEntity.ok(chica.get()) : ResponseEntity.status(404).body("Chica mágica no encontrada");
    }

    // Actualizar una chica mágica
    @PutMapping("/{id}")
    public ResponseEntity<?> actualizarChicaMagica(@PathVariable Long id, @RequestBody ChicasMagicas chicaMagica) {
        try {
            return ResponseEntity.ok(chicasMagicasService.actualizarChicaMagica(id, chicaMagica));
        } catch (RuntimeException e) {
            return ResponseEntity.status(404).body(e.getMessage());
        }
    }

    // Eliminar una chica mágica
    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminarChicaMagica(@PathVariable Long id) {
        boolean eliminado = chicasMagicasService.eliminarChicaMagica(id);
        return eliminado ? ResponseEntity.ok("Chica mágica eliminada exitosamente") : ResponseEntity.status(404).body("Error al eliminar la chica mágica");
    }

    // Filtrar chicas mágicas por estado
    @GetMapping("/estado/{estado}")
    public ResponseEntity<ArrayList<ChicasMagicas>> filtrarPorEstado(@PathVariable("estado") String estado) {
    String estadoDecodificado = URLDecoder.decode(estado, StandardCharsets.UTF_8);
    return ResponseEntity.ok((ArrayList<ChicasMagicas>) chicasMagicasService.filtrarPorEstado(estadoDecodificado));
    }

}
