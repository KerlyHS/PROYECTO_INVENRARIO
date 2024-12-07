package models;

public class Lote {
    private Integer id;
    private float precioLote;
    private int cantidad;
    private float precioCompra;
    private float precioVenta;
    private String fechaVen;
    private String fechaCreacion;
    private String descripcionLote;

    public Lote(Integer id, float precioLote, int cantidad, float precioCompra, float precioVenta, String fechaVen,
            String fechaCreacion, String descripcionLote) {
        this.id = id;
        this.precioLote = precioLote;
        this.cantidad = cantidad;
        this.precioCompra = precioCompra;
        this.precioVenta = precioVenta;
        this.fechaVen = fechaVen;
        this.fechaCreacion = fechaCreacion;
        this.descripcionLote = descripcionLote;
    }

    public Lote() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public float getPrecioLote() {
        return precioLote;
    }

    public void setPrecioLote(float precioLote) {
        this.precioLote = precioLote;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public float getPrecioCompra() {
        return precioCompra;
    }

    public void setPrecioCompra(float precioCompra) {
        this.precioCompra = precioCompra;
    }

    public float getPrecioVenta() {
        return precioVenta;
    }

    public void setPrecioVenta(float precioVenta) {
        this.precioVenta = precioVenta;
    }

    public String getFechaVen() {
        return fechaVen;
    }

    public void setFechaVen(String fechaVen) {
        this.fechaVen = fechaVen;
    }

    public String getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(String fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public String getDescripcionLote() {
        return descripcionLote;
    }

    public void setDescripcionLote(String descripcionLote) {
        this.descripcionLote = descripcionLote;
    }

    
    

   
}
