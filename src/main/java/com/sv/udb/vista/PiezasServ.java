/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sv.udb.vista;

import com.sv.udb.controlador.PiezasCtrl;
import com.sv.udb.modelo.Piezas;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import javax.swing.ImageIcon;

/**
 *
 * @author bernardo
 */
@WebServlet(name = "PiezasServ", urlPatterns = {"/PiezasServ"})
public class PiezasServ extends HttpServlet {

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
            response.sendRedirect(request.getContextPath() + "/index.jsp");
        }
        else {
            try{
                String CRUD = request.getParameter("btonPiez");
                if (CRUD.equals("Guardar"))
                {
                    Piezas obje = new Piezas();
                    obje.setNombPiez(request.getParameter("nomb"));
                    obje.setMarcPiez(request.getParameter("marc"));
                    obje.setTipoPiez(request.getParameter("tipo")); 
                    if (new PiezasCtrl().guar(obje))
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
                    int codi = Integer.parseInt(request.getParameter("codiPiezRadi").isEmpty() ? "-1" : request.getParameter("codiPiezRadi"));
                    Piezas obje = new PiezasCtrl().consUno(codi);
                    if (obje != null)
                    {
                        try {
                            request.setAttribute("codi", obje.getCodiPiez());
                            request.setAttribute("nomb", obje.getNombPiez());
                            request.setAttribute("marc", obje.getMarcPiez());
                            request.setAttribute("tipo", obje.getTipoPiez());
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
                    int codi = Integer.parseInt(request.getParameter("codiPiezRadi").isEmpty() ? "-1" : request.getParameter("codiPiezRadi"));
                    if (codi != -1)
                    {
                        Piezas obje = new Piezas();
                        obje.setCodiPiez(codi);
                        if (new PiezasCtrl().dele(obje))
                        {
                            mens = "Datos eliminados";
                            request.setAttribute("estModi", "disabled");
                            request.setAttribute("estGuar", "enable");
                            request.removeAttribute("codi");
                            request.removeAttribute("nomb");
                            request.removeAttribute("marc");
                            request.removeAttribute("tipo");
                        }
                        else
                        {
                            request.setAttribute("estModi", "disabled");
                            request.setAttribute("estGuar", "enable");
                            request.removeAttribute("codi");
                            request.removeAttribute("nomb");
                            request.removeAttribute("marc");
                            request.removeAttribute("tipo");
                            mens = "Error al eliminar";
                        }
                    }
                }
                else if (CRUD.equals("Modificar"))
                {
                    int codi = Integer.parseInt(request.getParameter("codi").isEmpty() ? "-1" : request.getParameter("codi"));
                    if (codi != -1)
                    {
                        Piezas obje = new Piezas();
                        obje.setCodiPiez(codi);
                        obje.setNombPiez(request.getParameter("nomb"));
                        obje.setMarcPiez(request.getParameter("marc"));
                        obje.setTipoPiez(request.getParameter("tipo"));
                        if (new PiezasCtrl().upda(obje))
                        {
                            mens = "Datos modificados";
                            request.setAttribute("estModi", "disabled");
                            request.setAttribute("estGuar", "enable");
                            request.removeAttribute("codi");
                            request.removeAttribute("nomb");
                            request.removeAttribute("tipo");
                            request.removeAttribute("marc");
                        }
                        else
                        {
                            request.setAttribute("estModi", "disabled");
                            request.setAttribute("estGuar", "enable");
                            request.removeAttribute("codi");
                            request.removeAttribute("nomb");
                            request.removeAttribute("tipo");
                            request.removeAttribute("marc");
                            mens = "Error al modificar";
                        }
                    }                   
                }
                else if (CRUD.equals("Nuevo"))
                {
                    request.removeAttribute("codi");
                    request.removeAttribute("nomb");
                    request.removeAttribute("tipo");
                    request.removeAttribute("marc");
                    request.setAttribute("estModi", "disabled");
                    request.setAttribute("estGuar", "enable");
                }
                request.setAttribute("mensAler", mens);
                request.getRequestDispatcher("/index.jsp").forward(request, response);
                
            }
            catch (Exception ex){
                System.err.println(ex.getMessage());
                ex.printStackTrace();
                request.setAttribute("estModi", "disabled");
                request.getRequestDispatcher("/index.jsp").forward(request, response);
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
