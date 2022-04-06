
package com.emergentes.controlador;

import com.emergentes.modelo.Almacen;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name = "MainController", urlPatterns = {"/MainController"})
public class MainController extends HttpServlet {

    
   
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
       HttpSession ses = request.getSession();
        
        if( ses.getAttribute("listal") == null ){
            ArrayList<Almacen> listaux = new ArrayList<Almacen>();
            ses.setAttribute("listal", listaux);
        }
        
        ArrayList<Almacen> lista = (ArrayList<Almacen>)ses.getAttribute("listal");
        
        
        String op = request.getParameter("op");
        String opcion = (op != null)? request.getParameter("op") : "view";
        
        Almacen obj1 = new Almacen();
        int id, pos;
        
        switch (opcion){
            
            case "nuevo": // insertar nuevo registro
                request.setAttribute("miAlmacen", obj1);
                request.getRequestDispatcher("editar.jsp").forward(request, response);
                break;
                
            case "editar": //modificar registro
                id = Integer.parseInt(request.getParameter("id"));
                pos = buscarIndice(request,id);
                obj1 = lista.get(pos);
                request.setAttribute("miAlmacen", obj1);
                request.getRequestDispatcher("editar.jsp").forward(request, response);
                break;
                
                
            case "eliminar": //eliminar registro
                pos = buscarIndice(request,Integer.parseInt(request.getParameter("id")));
                lista.remove(pos);
                ses.setAttribute("listal", lista);
                response.sendRedirect("index.jsp");
                break;
            
            case "view":
                response.sendRedirect("index.jsp");
        }
        
        
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        HttpSession ses = request.getSession();
        ArrayList<Almacen> lista = (ArrayList<Almacen>)ses.getAttribute("listal");
        
        Almacen obj1 = new Almacen();
        
        obj1.setId(Integer.parseInt(request.getParameter("id")));
        obj1.setDescripcion(request.getParameter("descripcion"));
        obj1.setCantidad(Integer.parseInt(request.getParameter("cantidad")));
        obj1.setPrecio(Double.parseDouble(request.getParameter("precio")));
        obj1.setCategoria(request.getParameter("categoria"));
        
         int idt = obj1.getId();
        
        if(idt == 0){
            //nuevo
            //actualizar el ultimo id
            int ultID;
            ultID = ultimoId(request);
            obj1.setId(ultID);
            lista.add(obj1);
        }
        else{
            // editar'
            lista.set(buscarIndice(request,idt), obj1);
        }
        ses.setAttribute("listal", lista);
        response.sendRedirect("index.jsp");
    }
    
    private int buscarIndice (HttpServletRequest request, int id){
        HttpSession ses = request.getSession();
        ArrayList<Almacen> lista = (ArrayList<Almacen>)ses.getAttribute("listal");
        
        int i = 0;
        
        if(lista.size() > 0){
            while (i < lista.size()){
                if(lista.get(i).getId() == id){
                    break;
                
                }
                else {
                    i++;
                }
            }
        }
         return i;
    }

    
    private int ultimoId(HttpServletRequest request){
         HttpSession ses = request.getSession();
        ArrayList<Almacen> lista = (ArrayList<Almacen>)ses.getAttribute("listal");
        
        int idaux = 0;
        for (Almacen item: lista){
            idaux = item.getId();
        }
        return idaux + 1;
    }
    

}
