/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import config.GenerarSerie;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modelo.Cliente;
import modelo.Empleado;
import modelo.Producto;
import modelo.Venta;
import modelo.dao.ClienteDAO;
import modelo.dao.EmpleadoDAO;
import modelo.dao.ProductoDAO;
import modelo.dao.VentaDAO;

/**
 *
 * @author Edgar
 */
public class Controlador extends HttpServlet {
    
    Empleado em = new Empleado();
    EmpleadoDAO edao = new EmpleadoDAO();
    Cliente cl = new Cliente();
    ClienteDAO cdao = new ClienteDAO();
    Producto p = new Producto();
    ProductoDAO pdao = new ProductoDAO();
    int ide;
    int idc;
    int idp;
    
    Venta v = new Venta();
    List<Venta>lista=new ArrayList<>();
    int item;
    int cod;
    String descripcion;
    double precio;
    int cant;
    double subtotal;
    double totalPagar;
    String numeroserie;
    VentaDAO vdao = new VentaDAO();
    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
            String menu = request.getParameter("menu");
            String accion = request.getParameter("accion");
            if(menu.equals("Principal")){
                request.getRequestDispatcher("Principal.jsp").forward(request, response);
            }
            if(menu.equals("Producto")){
                switch(accion){
                    case "Listar":
                        List lista=pdao.listar();              
                        request.setAttribute("productos", lista);
                        break;
                    case "Agregar":
                        String nom=request.getParameter("txtNom");
                        double precio=Double.parseDouble(request.getParameter("txtPrecio"));
                        int stock=Integer.parseInt(request.getParameter("txtStock"));
                        String est=request.getParameter("txtEstado");
                        p.setNom(nom);
                        p.setPrecio(precio);
                        p.setStock(stock);
                        p.setEstado(est);
                        pdao.agregar(p);
                        request.getRequestDispatcher("Controlador?menu=Producto&accion=Listar").forward(request, response);
                        break;
                    case "Editar":
                        ide=Integer.parseInt(request.getParameter("id"));
                        Producto pro=pdao.listarId(ide);
                        request.setAttribute("producto", pro);
                        request.getRequestDispatcher("Controlador?menu=Producto&accion=Listar").forward(request, response);
                        break;
                    case "Actualizar":
                        String nom1=request.getParameter("txtNom");
                        double precio1=Double.parseDouble(request.getParameter("txtPrecio"));
                        int stock1=Integer.parseInt(request.getParameter("txtStock"));
                        String est1=request.getParameter("txtEstado");
                        p.setNom(nom1);
                        p.setPrecio(precio1);
                        p.setStock(stock1);
                        p.setEstado(est1);
                        p.setId(ide);
                        pdao.actualizar(p);
                        request.getRequestDispatcher("Controlador?menu=Producto&accion=Listar").forward(request, response);
                        break;
                    case "Borrar":
                        idp=Integer.parseInt(request.getParameter("id"));
                        pdao.delete(ide);
                        request.getRequestDispatcher("Controlador?menu=Producto&accion=Listar").forward(request, response);
                        break;
                }
                request.getRequestDispatcher("Producto.jsp").forward(request, response);
            }
            if(menu.equals("Cliente")){
                
                request.getRequestDispatcher("Cliente.jsp").forward(request, response);
            }
            if(menu.equals("Empleado")){
                switch(accion){
                    case "Listar":
                        List lista=edao.listar();              
                        request.setAttribute("empleados", lista);
                        break;
                    case "Agregar":
                        String dni=request.getParameter("txtDni");
                        String nom=request.getParameter("txtNom");
                        String tel=request.getParameter("txtTel");
                        String est=request.getParameter("txtEstado");
                        String user=request.getParameter("txtUser");
                        em.setDni(dni);
                        em.setNom(nom);
                        em.setTel(tel);
                        em.setEstado(est);
                        em.setUser(user);
                        edao.agregar(em);
                        request.getRequestDispatcher("Controlador?menu=Empleado&accion=Listar").forward(request, response);
                        break;
                    case "Editar":
                        ide=Integer.parseInt(request.getParameter("id"));
                        Empleado e=edao.listarId(ide);
                        request.setAttribute("empleado", e);
                        request.getRequestDispatcher("Controlador?menu=Empleado&accion=Listar").forward(request, response);
                        break;
                    case "Actualizar":
                        String dni1=request.getParameter("txtDni");
                        String nom1=request.getParameter("txtNom");
                        String tel1=request.getParameter("txtTel");
                        String est1=request.getParameter("txtEstado");
                        String user1=request.getParameter("txtUser");
                        em.setDni(dni1);
                        em.setNom(nom1);
                        em.setTel(tel1);
                        em.setEstado(est1);
                        em.setUser(user1);
                        em.setId(ide);
                        edao.actualizar(em);
                        request.getRequestDispatcher("Controlador?menu=Empleado&accion=Listar").forward(request, response);
                        break;
                    case "Borrar":
                        ide=Integer.parseInt(request.getParameter("id"));
                        edao.delete(ide);
                        request.getRequestDispatcher("Controlador?menu=Empleado&accion=Listar").forward(request, response);
                        break;
                }
                request.getRequestDispatcher("Empleado.jsp").forward(request, response);
            }
            if(menu.equals("NuevaVenta")){
                switch(accion){
                    case "BuscarCliente":
                        String dni=request.getParameter("codigocliente");
                        cl.setDni(dni);
                        cl = cdao.buscar(dni);
                        request.setAttribute("c", cl);
                        request.setAttribute("nserie", numeroserie);
                        break;
                    case "BuscarProducto":
                        int id=Integer.parseInt(request.getParameter("codigoproducto"));
                        p=pdao.listarId(id);
                        request.setAttribute("c", cl);
                        request.setAttribute("producto", p);
                        request.setAttribute("lista", lista);
                        request.setAttribute("totalPagar", totalPagar);
                        request.setAttribute("nserie", numeroserie);
                        break;
                    case "Agregar":
                        request.setAttribute("nserie", numeroserie);
                        request.setAttribute("c", cl);
                        totalPagar=0.0;
                        item=item+1;
                        cod=p.getId();
                        descripcion=request.getParameter("nombreproducto");
                        precio=Double.parseDouble(request.getParameter("precio"));
                        cant=Integer.parseInt(request.getParameter("cant"));
                        subtotal=precio*cant;
                        v=new Venta();
                        v.setItem(item);
                        v.setIdProducto(cod);
                        v.setDescricpionP(descripcion);
                        v.setPrecio(precio);
                        v.setCantidad(cant);
                        v.setSubtotal(subtotal);
                        lista.add(v);
                        for(int i=0; i<lista.size();i++){
                            totalPagar=totalPagar + lista.get(i).getSubtotal();
                        }
                        request.setAttribute("totalPagar", totalPagar);
                        request.setAttribute("lista", lista);
                        break;
                    case "generarVenta":
                        for(int i=0; i<lista.size(); i++){
                            Producto pr = new Producto();
                            int cantidad=lista.get(i).getCantidad();
                            int idproducto=lista.get(i).getIdProducto();
                            ProductoDAO pdao = new ProductoDAO();
                            pr=pdao.buscar(idproducto);
                            int sac=pr.getStock()-cantidad;
                            System.out.println(pr.getStock());
                            pdao.actualizarstock(idproducto,sac);
                            
                        }
                        v.setIdCliente(cl.getId());
                        v.setIdEmpleado(2);
                        v.setNumSerie(numeroserie);
                        v.setFecha("12/12/2021");
                        v.setMonto(totalPagar);
                        v.setEstado("1");
                        vdao.guardarVenta(v);
                        
                        int idv=Integer.parseInt(vdao.IdVentas());
                        for(int i=0;i<lista.size();i++){
                            v=new Venta();
                            v.setId(idv);
                            v.setIdProducto(lista.get(i).getIdProducto());
                            v.setCantidad(lista.get(i).getCantidad());
                            v.setPrecio(lista.get(i).getPrecio());
                            vdao.guardarDetalleVentas(v);
                        }
                    default:
                        v=new Venta();
                        lista = new ArrayList<>();
                        item=0;
                        totalPagar=0.0;
                        numeroserie = vdao.GenerarSerie();
                        if(numeroserie==null){
                            numeroserie="00000001";
                            request.setAttribute("nserie", numeroserie);
                        }else{
                            int incrementar=Integer.parseInt(numeroserie);
                            GenerarSerie gs = new GenerarSerie();
                            numeroserie = gs.NumeroSerie(incrementar);
                            request.setAttribute("nserie", numeroserie);
                        }
                        request.getRequestDispatcher("RegistrarVenta.jsp").forward(request, response);
                }
                request.getRequestDispatcher("RegistrarVenta.jsp").forward(request, response);
            }
            
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
