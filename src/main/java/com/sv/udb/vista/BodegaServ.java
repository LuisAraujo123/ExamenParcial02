/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sv.udb.vista;

import com.sv.udb.controlador.BodegaCtrl;
import com.sv.udb.modelo.Bodega;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.util.Calendar;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author bernardo
 */
@WebServlet(name = "BodegaServ", urlPatterns = {"/BodegaServ"})
public class BodegaServ extends HttpServlet {

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
        boolean esValido = request.getMethod().equals("POST");
        String mens = "";
        System.err.println("Entra al process");
        if(!esValido)
        {
            response.sendRedirect(request.getContextPath() + "/bodega.jsp");
        }
        else {
            try{
                String CRUD = request.getParameter("btonBode");
                if (CRUD.equals("Guardar"))
                {
                    Bodega obje = new Bodega();
                    obje.setCodPiez(Integer.parseInt(request.getParameter("codiPiez")));
                    obje.setCodiProv(Integer.parseInt(request.getParameter("codiProv")));
                    obje.setCant(Integer.parseInt(request.getParameter("cant"))); 
                    Calendar c1 = Calendar.getInstance();
                    int anio = c1.get(Calendar.YEAR);
                    int mes = c1.get(Calendar.MONTH)+1;
                    int dia = c1.get(Calendar.DATE);
                    String fechAnio = Integer.toString(anio);
                    String fechMes = Integer.toString(mes);
                    String fechDia = Integer.toString(dia);
                    if (mes < 10)
                    {
                        fechMes = "0"+fechMes;
                        
                    }
                    if (dia < 10)
                    {
                        fechDia = "0"+fechDia;
                    }
                    String fechActu = fechAnio + "-" + fechMes + "-" + fechDia;
                    System.out.println(fechActu);
                    obje.setFech(Date.valueOf(fechActu)); 
                    if (new BodegaCtrl().guar(obje))
                    {
                        mens = "Datos guardados";
                        request.setAttribute("estModi", "disabled");
                    }
                    else
                    {
                        mens = "Error al guardar";
                        request.setAttribute("estModi", "disabled");
                    }
                }
                else if (CRUD.equals("Consultar"))
                {
                    
                }
                else if  (CRUD.equals("Eliminar"))
                {
                    
                }
                else if (CRUD.equals("Modificar"))
                {
                                      
                }
                else if (CRUD.equals("Nuevo"))
                {
                    request.removeAttribute("codiPiez");
                    request.removeAttribute("codiProv");
                    request.removeAttribute("cant");
                    request.removeAttribute("fech");
                    request.setAttribute("estModi", "disabled");
                    request.setAttribute("estGuar", "enable");
                }
                request.setAttribute("mensAler", mens);
                request.getRequestDispatcher("/bodega.jsp").forward(request, response);
                
            }
            catch (Exception ex){
                System.err.println(ex.getMessage());
                ex.printStackTrace();
                request.setAttribute("estModi", "disabled");
                request.getRequestDispatcher("/bodega.jsp").forward(request, response);
            }
            
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
