package com.tayra.model;

public class Producto {

    private int id;
    private String ean;
    private String nombre;
    private String descripcion;
    private int cantidad;
    private double precio;

    public Producto() {
    }

    public Producto(String ean, String nombre, String descripcion,
                    int cantidad, double precio) {
        this.ean = ean;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.cantidad = cantidad;
        this.precio = precio;
    }

    public Producto(int id, String ean, String nombre, String descripcion,
                    int cantidad, double precio) {
        this(ean, nombre, descripcion, cantidad, precio);
        this.id = id;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getEan() { return ean; }
    public void setEan(String ean) { this.ean = ean; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getCantidad() { return cantidad; }
    public void setCantidad(int cantidad) { this.cantidad = cantidad; }

    public double getPrecio() { return precio; }
    public void setPrecio(double precio) { this.precio = precio; }
}
