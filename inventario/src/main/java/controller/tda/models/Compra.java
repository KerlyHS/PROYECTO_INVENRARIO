package controller.tda.models;
import java.util.Date;

public class Compra {
    private int idCompra;
    private float compras;
    private int idProveedor;
    private Date fechaCompra;
    private String metodoPago;


    //constructor vacio
    public Compra(int idCompra, float compras, int idProveedor,
     Date fechaCompra, String metodoPago ){


    }
   


	public int getIdCompra() {
		return this.idCompra;
	}

	public void setIdCompra(int idCompra) {
		this.idCompra = idCompra;
	}

	public float getCompras() {
		return this.compras;
	}

	public void setCompras(float compras) {
		this.compras = compras;
	}

	public int getIdProveedor() {
		return this.idProveedor;
	}

	public void setIdProveedor(int idProveedor) {
		this.idProveedor = idProveedor;
	}

	public Date getFechaCompra() {
		return this.fechaCompra;
	}

	public void setFechaCompra(Date fechaCompra) {
		this.fechaCompra = fechaCompra;
	}

	public String getMetodoPago() {
		return this.metodoPago;
	}

	public void setMetodoPago(String metodoPago) {
		this.metodoPago = metodoPago;
	}
}