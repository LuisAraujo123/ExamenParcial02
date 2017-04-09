/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sv.udb.vista;

import com.sv.udb.controlador.ProveedoresCtrl;
import com.sv.udb.modelo.Proveedores;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author bernardo
 */
@WebServlet(name = "ProveedoresServ", urlPatterns = {"/ProveedoresServ"})
public class ProveedoresServ extends HttpServlet {

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
            response.sendRedirect(request.getContextPath() + "/proveedores.jsp");
        }
        else {
            try{
                String CRUD = request.getParameter("btonProv");
                if (CRUD.equals("Guardar"))
                {
                    Proveedores obje = new Proveedores();
                    obje.setNombProv(request.getParameter("nomb"));
                    obje.setDireProv(request.getParameter("dire"));
                    obje.setTeleProv(request.getParameter("tele")); 
                    if (new ProveedoresCtrl().guar(obje))
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
                    //El operador unario sirve como if para validar que el radio buton este selecionado, si no lo esta devuelve -1
                    int codi = Integer.parseInt(request.getParameter("codiProvRadi").isEmpty() ? "-1" : request.getParameter("codiProvRadi"));
                    Proveedores obje = new ProveedoresCtrl().consUno(codi);
                    if (obje != null)
                    {
                        try {
                            request.setAttribute("codi", obje.getCodiProv());
                            request.setAttribute("nomb", obje.getNombProv());
                            request.setAttribute("dire", obje.getDireProv());
                            request.setAttribute("tele", obje.getTeleProv());
                            request.setAttribute("estModi", "enable");
                            request.setAttribute("estGuar", "disabled");
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                    else
                    {
                        mens = "Error al consultar";
                    }
                }
                else if  (CRUD.equals("Eliminar"))
                {
                    int codi = Integer.parseInt(request.getParameter("codiProvRadi").isEmpty() ? "-1" : request.getParameter("codiProvRadi"));
                    if (codi != -1)
                    {
                        Proveedores obje = new Proveedores();
                        obje.setCodiProv(codi);
                        if (new ProveedoresCtrl().dele(obje))
                        {
                            mens = "Datos eliminados";
                            request.setAttribute("estModi", "disabled");
                            request.setAttribute("estGuar", "enable");
                            request.removeAttribute("codi");
                            request.removeAttribute("nomb");
                            request.removeAttribute("dire");
                            request.removeAttribute("tele");
                        }
                        else
                        {
                            request.setAttribute("estModi", "disabled");
                            request.setAttribute("estGuar", "enable");
                            request.removeAttribute("codi");
                            request.removeAttribute("nomb");
                            request.removeAttribute("dire");
                            request.removeAttribute("tele");
                            mens = "Error al eliminar";
                        }
                    }
                }
                else if (CRUD.equals("Modificar"))
                {
                    int codi = Integer.parseInt(request.getParameter("codi").isEmpty() ? "-1" : request.getParameter("codi"));
                    if (codi != -1)
                    {
                        Proveedores obje = new Proveedores();
                        obje.setCodiProv(codi);
                        obje.setNombProv(request.getParameter("nomb"));
                        obje.setDireProv(request.getParameter("dire"));
                        obje.setTeleProv(request.getParameter("tele"));
                        if (new ProveedoresCtrl().upda(obje))
                        {
                            mens = "Datos modificados";
                            request.setAttribute("estModi", "disabled");
                            request.setAttribute("estGuar", "enable");
                            request.removeAttribute("codi");
                            request.removeAttribute("nomb");
                            request.removeAttribute("dire");
                            request.removeAttribute("tele");
                        }
                        else
                        {
                            request.setAttribute("estModi", "disabled");
                            request.setAttribute("estGuar", "enable");
                            request.removeAttribute("codi");
                            request.removeAttribute("nomb");
                            request.removeAttribute("dire");
                            request.removeAttribute("tele");
                            mens = "Error al modificar";
                        }
                    }                   
                }
                else if (CRUD.equals("Nuevo"))
                {
                    request.removeAttribute("codi");
                    request.removeAttribute("nomb");
                    request.removeAttribute("dire");
                    request.removeAttribute("tele");
                    request.setAttribute("estModi", "disabled");
                    request.setAttribute("estGuar", "enable");
                }
                request.setAttribute("mensAler", mens);
                request.getRequestDispatcher("/proveedores.jsp").forward(request, response);
                
            }
            catch (Exception ex){
                System.err.println(ex.getMessage());
                ex.printStackTrace();
                request.setAttribute("estModi", "disabled");
                request.getRequestDispatcher("/proveedores.jsp").forward(request, response);
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
