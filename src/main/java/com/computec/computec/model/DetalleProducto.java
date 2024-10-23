package com.computec.computec.model;

import jakarta.persistence.*;

@Entity
@Table(name = "detalleProducto")
public class DetalleProducto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String c1;
    private String c2;
    private String c3;
    private String c4;
    private String c5;
    private String c6;
    private String c7;
    private String c8;
    private String c9;
    private String c10;
    private String c11;

    @OneToOne(mappedBy = "detalleProducto")
    private Producto producto;

    public DetalleProducto() {
    }

    public DetalleProducto(Integer id, String c1, String c2, String c3, String c4, String c5, String c6, String c7, String c8, String c9, String c10, String c11) {
        this.id = id;
        this.c1 = c1;
        this.c2 = c2;
        this.c3 = c3;
        this.c4 = c4;
        this.c5 = c5;
        this.c6 = c6;
        this.c7 = c7;
        this.c8 = c8;
        this.c9 = c9;
        this.c10 = c10;
        this.c11 = c11;
    }
}
