package net.leydeciclospac1.app.leydeciclos.entities;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
@Entity
@Table(name = "chicasmagicas")
public class ChicasMagicas {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "ciudad_de_origen", nullable = false)
    private String ciudadDeOrigen;

    @Column(name = "estado_actual", nullable = false)
    private String estadoActual;

    @Column(name = "edad", nullable = false)
    private Integer edad;

    @Temporal(TemporalType.DATE)
    @Column(name = "fecha_de_contrato", nullable = false)
    private Date fechaDeContrato;

    // Constructor vacío
    public ChicasMagicas() {}

    // Constructor con parámetros
    public ChicasMagicas(String name, String ciudadDeOrigen, String estadoActual, Integer edad, Date fechaDeContrato) {
        this.name = name;
        this.ciudadDeOrigen = ciudadDeOrigen;
        this.estadoActual = estadoActual;
        this.edad = edad;
        this.fechaDeContrato = fechaDeContrato;
    }

    // Getters y Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCiudadDeOrigen() {
        return ciudadDeOrigen;
    }

    public void setCiudadDeOrigen(String ciudadDeOrigen) {
        this.ciudadDeOrigen = ciudadDeOrigen;
    }

    public String getEstadoActual() {
        return estadoActual;
    }

    public void setEstadoActual(String estadoActual) {
        this.estadoActual = estadoActual;
    }

    public Integer getEdad() {
        return edad;
    }

    public void setEdad(Integer edad) {
        this.edad = edad;
    }

    public Date getFechaDeContrato() {
        return fechaDeContrato;
    }

    public void setFechaDeContrato(Date fechaDeContrato) {
        this.fechaDeContrato = fechaDeContrato;
    }
}