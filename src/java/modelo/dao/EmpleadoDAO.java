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
import java.util.ArrayList;
import java.util.List;
import modelo.Empleado;

public class EmpleadoDAO {
    // conectado con la bases de datos
    Conexion cn = new Conexion();
    Connection con;
    PreparedStatement ps;
    ResultSet rs;
    int r;
    
    public Empleado validar(String user, String dni){
        Empleado em = new Empleado();
        String sql = "SELECT * FROM empleado WHERE User=? and Dni=?";
        try {
            con = cn.Conexion();
            ps = con.prepareStatement(sql);
            ps.setString(1, user);
            ps.setString(2, dni);
            rs = ps.executeQuery();
            while(rs.next()){
                em.setId(rs.getInt("IdEmpleado"));
                em.setUser(rs.getString("User"));
                em.setDni(rs.getString("Dni"));
                em.setNom(rs.getString("Nombres"));
            }
        } catch (Exception e) {
            System.out.println("error 1 "+e.getMessage());
        }
        return em;
    }
    
    //operaciones CRUD
    public List listar(){
        String sql="SELECT * FROM empleado";
        List<Empleado>lista=new ArrayList<>();
        try {
            System.out.println("listado");
            con=cn.Conexion();
            ps=con.prepareStatement(sql);
            rs=ps.executeQuery();
            while (rs.next()) {       
                Empleado em = new Empleado();
                em.setId(rs.getInt(1));
                em.setDni(rs.getString(2));
                em.setNom(rs.getString(3));
                em.setTel(rs.getString(4));
                em.setEstado(rs.getString(5));
                em.setUser(rs.getString(6));
                lista.add(em);
            }
        } catch (Exception e) {
            System.out.println("error 2 "+e.getMessage());
        }
        return lista;
    }
    public int agregar(Empleado emp){
        String sql = "INSERT INTO empleado(Dni,Nombres,Telefono,Estado,User) VALUES(?,?,?,?,?)";
        try {
            System.out.println("agregado");
            con=cn.Conexion();
            ps=con.prepareStatement(sql);
            ps.setString(1, emp.getDni());
            ps.setString(2, emp.getNom());
            ps.setString(3, emp.getTel());
            ps.setString(4, emp.getEstado());
            ps.setString(5, emp.getUser());
            ps.executeUpdate();
            
        } catch (Exception e) {
            System.out.println("error 3 "+e.getMessage());
        }
        return r;   
    }
    public Empleado listarId(int id){
        Empleado emp=new Empleado();
        String sql="SELECT * FROM empleado WHERE idEmpleado="+id;
        try {
            con=cn.Conexion();
            ps=con.prepareStatement(sql);
            rs=ps.executeQuery();
            while(rs.next()){
                emp.setDni(rs.getString(2));
                emp.setNom(rs.getString(3));
                emp.setTel(rs.getString(4));
                emp.setEstado(rs.getString(5));
                emp.setUser(rs.getString(6));
                
            }
        } catch (Exception e) {
            System.out.println("error 4 "+e.getMessage());
        }
        return emp;
    }
    public int actualizar(Empleado emp){
        String sql = "UPDATE empleado SET Dni=?, Nombres=?, Telefono=?, Estado=?, User=? WHERE idEmpleado=?";
        try {
            con=cn.Conexion();
            ps=con.prepareStatement(sql);
            ps.setString(1, emp.getDni());
            ps.setString(2, emp.getNom());
            ps.setString(3, emp.getTel());
            ps.setString(4, emp.getEstado());
            ps.setString(5, emp.getUser());
            ps.setInt(6, emp.getId());
            ps.executeUpdate();
            
        } catch (Exception e) {
            System.out.println("error 5 "+e.getMessage());
        }
        return r;   
    }
    public void delete(int id){
        String sql = "DELETE FROM empleado WHERE idEmpleado="+id;
        try {
            con=cn.Conexion();
            ps=con.prepareStatement(sql);
            ps.executeUpdate();
        } catch (Exception e) {
            System.out.println("error 6 "+e.getMessage());
        }
    }
}
