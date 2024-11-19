package controller.tda.models;

public class DetalleCompra {
  
    private int idDetalleCompra;
    private int cantidadProductos;
    private String nombreProducto;
    private String descripcionCompra;

    public int getIdDetalleCompra() {
        return this.idDetalleCompra;
    }

    public void setIdDetalleCompra(int idDetalleCompra) {
        this.idDetalleCompra = idDetalleCompra;
    }

    public int getCantidadProductos() {
        return this.cantidadProductos;
    }

    public void setCantidadProductos(int cantidadProductos) {
        this.cantidadProductos = cantidadProductos;
    }

    public String getNombreProducto() {
        return this.nombreProducto;
    }

    public void setNombreProducto(String nombreProducto) {
        this.nombreProducto = nombreProducto;
    }

    public String getDescripcionCompra() {
        return this.descripcionCompra;
    }

    public void setDescripcionCompra(String descripcionCompra) {
        this.descripcionCompra = descripcionCompra;
    }


    
}
