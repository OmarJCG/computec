package com.computec.computec.model;

import jakarta.persistence.*;

import java.util.Date;
import java.util.List;

@Entity
@Table(name = "orden")
public class Orden {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String numero;
    private Date fechaCreacion;

    private double total;

    @ManyToOne
    private Usuario usuario;

    @OneToMany(mappedBy = "orden")
    private List<DetalleOrden> detalle;

    public Orden() {
    }

    public Orden(Integer id, String numero, Date fechaCreacion, double total) {
        this.id = id;
        this.numero = numero;
        this.fechaCreacion = fechaCreacion;
        this.total = total;
    }

    @Override
    public String toString() {
        return "Orden{" +
                "id=" + id +
                ", numero='" + numero + '\'' +
                ", fechaCreacion=" + fechaCreacion +
                ", total=" + total +
                '}';
    }
}
