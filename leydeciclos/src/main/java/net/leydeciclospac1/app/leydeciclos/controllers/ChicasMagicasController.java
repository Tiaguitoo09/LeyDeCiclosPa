package net.leydeciclospac1.app.leydeciclos.controllers;


import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
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

@CrossOrigin(origins = "http://localhost:8000") 
@RestController
@RequestMapping("/chicas-magicas")
public class ChicasMagicasController {

    @Autowired
    private ChicasMagicasService chicasMagicasService;

    // Obtener todas las chicas mágicas
    @GetMapping ("/Chicas")
    public ArrayList<ChicasMagicas> obtenerChicasMagicas() {
        return chicasMagicasService.obtenerChicasMagicas();
    }

    // Guardar una nueva chica mágica
    @PostMapping
    public ChicasMagicas guardarChicaMagica(@RequestBody ChicasMagicas chicaMagica) {
        return chicasMagicasService.guardarChicaMagica(chicaMagica);
    }

    // Obtener una chica mágica por su ID
    @GetMapping("/{id}")
    public Optional<ChicasMagicas> obtenerChicaMagicaPorId(@PathVariable Long id) {
        return chicasMagicasService.obtenerPorId(id);
    }

    // Actualizar una chica mágica
    @PutMapping("/{id}")
    public ChicasMagicas actualizarChicaMagica(@PathVariable Long id, @RequestBody ChicasMagicas chicaMagica) {
        return chicasMagicasService.actualizarChicaMagica(id, chicaMagica);
    }

    // Eliminar una chica mágica
    @DeleteMapping("/{id}")
    public String eliminarChicaMagica(@PathVariable Long id) {
        boolean eliminado = chicasMagicasService.eliminarChicaMagica(id);
        return eliminado ? "Chica mágica eliminada exitosamente" : "Error al eliminar la chica mágica";
    }

    // Filtrar chicas mágicas por estado
    @GetMapping("/estado/{estado}")
    public ArrayList<ChicasMagicas> filtrarPorEstado(@PathVariable String estado) {
        return (ArrayList<ChicasMagicas>) chicasMagicasService.filtrarPorEstado(estado);
    }
}