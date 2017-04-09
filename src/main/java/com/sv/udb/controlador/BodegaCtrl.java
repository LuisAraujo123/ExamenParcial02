/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sv.udb.controlador;

import com.sv.udb.modelo.Bodega;
import com.sv.udb.recursos.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author bernardo
 */
public class BodegaCtrl {
    public boolean guar(Bodega obje){
        boolean resp = false;
        Connection cn = new Conexion().getConn();
        try {
            PreparedStatement cmd = cn.prepareStatement("INSERT INTO bodega VALUES(NULL, ?, ?, ?, ?)");
            cmd.setInt(1, obje.getCodPiez());
            cmd.setInt(2, obje.getCodiProv());
            cmd.setInt(3, obje.getCant());
            cmd.setDate(4, obje.getFech());
            cmd.executeUpdate();
            resp = true;
            
        } catch (Exception ex) {
            System.err.println("Error: " + ex.getMessage());
        }
        finally 
        {
            try 
            {
                if (cn!=null)
                {
                    if (!cn.isClosed())
                    {
                        cn.close();
                    }
                }
            } 
            catch (Exception e) 
            {
                System.err.println("Error: " + e.getMessage());
            }
        }
        return resp;
    }
    
    public boolean upda(Bodega obje){
        boolean resp = false;
        Connection cn = new Conexion().getConn();
        try {
            PreparedStatement cmd = cn.prepareStatement("update piezas set codi_piez = ?, codi_prov = ?, cant = ?, fech = ? where codi_bode = ?;");
            cmd.setInt(1, obje.getCodPiez());
            cmd.setInt(2, obje.getCodiProv());
            cmd.setInt(3, obje.getCant());
            cmd.setDate(4, obje.getFech());
            cmd.setInt(5, obje.getCodiBode());
            cmd.executeUpdate();
            resp = true;
            
        } catch (Exception ex) {
            System.err.println("Error: " + ex.getMessage());
        }
        finally 
        {
            try 
            {
                if (cn!=null)
                {
                    if (!cn.isClosed())
                    {
                        cn.close();
                    }
                }
            } 
            catch (Exception e) 
            {
                System.err.println("Error: " + e.getMessage());
            }
        }
        return resp;
    }
    /*
    public boolean dele(Bodega obje){
        boolean resp = false;
        Connection cn = new Conexion().getConn();
        try {
            PreparedStatement cmd = cn.prepareStatement("delete from piezas where codi_piez = ?");
            cmd.setInt(1, obje.get());
            cmd.executeUpdate();
            resp = true;
            
        } catch (Exception ex) {
            System.err.println("Error: " + ex.getMessage());
        }
        finally 
        {
            try 
            {
                if (cn!=null)
                {
                    if (!cn.isClosed())
                    {
                        cn.close();
                    }
                }
            } 
            catch (Exception e) 
            {
                System.err.println("Error: " + e.getMessage());
            }
        }
        return resp;
    }
    */
    public List<Bodega> consTodo(){
        List<Bodega> resp = new ArrayList<>();
        Connection cn = new Conexion().getConn();
        try {
            PreparedStatement cmd = cn.prepareStatement("Select * from bodega");
            ResultSet rs = cmd.executeQuery();
            while (rs.next())
            {
                resp.add(new Bodega(rs.getInt(1), rs.getInt(2), rs.getInt(3), rs.getInt(4), rs.getDate(5)));
            }
        } catch (Exception ex) {
            System.err.println("Error: " + ex.getMessage());
        }
        finally 
        {
            try 
            {
                if (cn!=null)
                {
                    if (!cn.isClosed())
                    {
                        cn.close();
                    }
                }
            } 
            catch (Exception e) 
            {
                System.err.println("Error: " + e.getMessage());
            }
        }
        return resp;
    }
    
    /*
    public Piezas consUno(int id){
        Piezas resp = null;
        Connection cn = new Conexion().getConn();
        try {
            PreparedStatement cmd = cn.prepareStatement("Select * from piezas where codi_piez = ?");
            cmd.setInt(1, id);
            ResultSet rs = cmd.executeQuery();
            if(rs.next())
            {
                resp = new Piezas(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4));
            }
        } catch (Exception ex) {
            System.err.println("Error: " + ex.getMessage());
        }
        finally 
        {
            try 
            {
                if (cn!=null)
                {
                    if (!cn.isClosed())
                    {
                        cn.close();
                    }
                }
            } 
            catch (Exception e) 
            {
                System.err.println("Error: " + e.getMessage());
            }
        }
        return resp;
    }*/
}
