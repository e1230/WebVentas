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
import modelo.Cliente;

/**
 *
 * @author Edgar
 */
public class ClienteDAO {
    Conexion cn = new Conexion();
    Connection con;
    PreparedStatement ps;
    ResultSet rs;
    int r;
    
    public Cliente buscar(String dni){
        Cliente cl= new Cliente();
        String sql = "SELECT * from cliente WHERE Dni="+dni;
        try {
            con=cn.Conexion();
            ps=con.prepareStatement(sql);
            rs=ps.executeQuery(); 
            while(rs.next()){
                cl.setId(rs.getInt(1));
                cl.setDni(rs.getString(2));
                cl.setNom(rs.getString(3));
                cl.setDir(rs.getString(4));
                cl.setEstado(rs.getString(5));
            }
        } catch (Exception e) {
            System.out.println("error 7 cliente"+e.getMessage());
        }
        return cl;
    }
    
    //operaciones CRUD
    public List listar(){
        String sql="SELECT * FROM cliente";
        List<Cliente>lista=new ArrayList<>();
        try {
            con=cn.Conexion();
            ps=con.prepareStatement(sql);
            rs=ps.executeQuery();
            while (rs.next()) {       
                Cliente cl = new Cliente();
                cl.setId(rs.getInt(1));
                cl.setDni(rs.getString(2));
                cl.setNom(rs.getString(3));
                cl.setDir(rs.getString(4));
                cl.setEstado(rs.getString(5));
                lista.add(cl);
            }
        } catch (Exception e) {
            System.out.println("error 2 "+e.getMessage());
        }
        return lista;
    }
    public int agregar(Cliente cl){
        String sql = "INSERT INTO cliente(Dni,Nombres,Direccion,Estado) VALUES(?,?,?,?)";
        try {
            con=cn.Conexion();
            ps=con.prepareStatement(sql);
            ps.setString(1, cl.getDni());
            ps.setString(2, cl.getNom());
            ps.setString(3, cl.getDir());
            ps.setString(4, cl.getEstado());
            ps.executeUpdate();
            
        } catch (Exception e) {
            System.out.println("error 3 "+e.getMessage());
        }
        return r;   
    }
    public Cliente listarId(int id){
        Cliente cl=new Cliente();
        String sql="SELECT * FROM cliente WHERE idCliente="+id;
        try {
            con=cn.Conexion();
            ps=con.prepareStatement(sql);
            rs=ps.executeQuery();
            while(rs.next()){
                cl.setDni(rs.getString(2));
                cl.setNom(rs.getString(3));
                cl.setDir(rs.getString(4));
                cl.setEstado(rs.getString(5));
            }
        } catch (Exception e) {
            System.out.println("error 4 "+e.getMessage());
        }
        return cl;
    }
    public int actualizar(Cliente cl){
        String sql = "UPDATE cliente SET Dni=?, Nombres=?, Direccion=?, Estado=? WHERE idCliente=?";
        try {
            con=cn.Conexion();
            ps=con.prepareStatement(sql);
            ps.setString(1, cl.getDni());
            ps.setString(2, cl.getNom());
            ps.setString(3, cl.getDir());
            ps.setString(4, cl.getEstado());
            ps.setInt(5, cl.getId());
            ps.executeUpdate();
            
        } catch (Exception e) {
            System.out.println("error 5 "+e.getMessage());
        }
        return r;   
    }
    public void delete(int id){
        String sql = "DELETE FROM cliente WHERE idCliente="+id;
        try {
            con=cn.Conexion();
            ps=con.prepareStatement(sql);
            ps.executeUpdate();
        } catch (Exception e) {
            System.out.println("error 6 "+e.getMessage());
        }
    }
}
