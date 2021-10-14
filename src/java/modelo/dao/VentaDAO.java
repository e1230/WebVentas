/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo.dao;

import config.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import modelo.Venta;

/**
 *
 * @author Edgar
 */
public class VentaDAO {
    Conexion cn = new Conexion();
    Connection con;
    PreparedStatement ps;
    ResultSet rs;
    int r;
    public String GenerarSerie(){
        String numeroserie="";
        String sql="SELECT MAX(NumeroSerie) FROM ventas";
        try {
            con=cn.Conexion();
            ps=con.prepareStatement(sql);
            rs=ps.executeQuery();
            while(rs.next()){
                numeroserie=rs.getString(1);
                
            }
        } catch (Exception e) {
            System.out.println("error 1"+e.getMessage());
        }
        return numeroserie;
        
    }
    public String IdVentas(){
        String idventas="";
        String sql="SELECT MAX(IdVentas) FROM ventas";
       try {
            con=cn.Conexion();
            ps=con.prepareStatement(sql);
            rs=ps.executeQuery();
            while(rs.next()){
                idventas=rs.getString(1);
                
            }
        } catch (Exception e) {
            System.out.println("error 2"+e.getMessage());
        }
       return idventas;
    }
    
    public int guardarVenta(Venta ve){
        String sql = "INSERT INTO ventas(IdCliente, IdEmpleado, NumeroSerie, FechaVentas, Monto, Estado) VALUES(?,?,?,?,?,?)";
        try {
            con=cn.Conexion();
            ps=con.prepareStatement(sql);
            ps.setInt(1, ve.getIdCliente());
            ps.setInt(2, ve.getIdEmpleado());
            ps.setString(3, ve.getNumSerie());
            ps.setString(4, ve.getFecha());
            ps.setDouble(5, ve.getPrecio());
            ps.setString(6, ve.getEstado());
            ps.executeUpdate();
        } catch (Exception e) {
            System.out.println("error 3 "+e.getMessage());
        }
        return r;
    }
    
    public int guardarDetalleVentas(Venta venta){
        String sql="INSERT INTO detalle_ventas(IdVentas, IdProducto, Cantidad, PrecioVenta) VALUES(?,?,?,?)";
        try {
            con=cn.Conexion();
            ps=con.prepareStatement(sql);
            ps.setInt(1, venta.getId());
            ps.setInt(2, venta.getIdProducto());
            ps.setInt(3, venta.getCantidad());
            ps.setDouble(4, venta.getPrecio());
            ps.executeUpdate();
            } catch (Exception e) {
            System.out.println("error 4 "+e.getMessage());
        }
        return r;
    }
}
