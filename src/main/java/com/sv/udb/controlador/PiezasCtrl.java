/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sv.udb.controlador;

import com.sv.udb.modelo.Piezas;
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
public class PiezasCtrl {
    
    public boolean guar(Piezas obje){
        boolean resp = false;
        Connection cn = new Conexion().getConn();
        try {
            PreparedStatement cmd = cn.prepareStatement("INSERT INTO piezas VALUES(NULL, ?, ?, ?)");
            cmd.setString(1, obje.getNombPiez());
            cmd.setString(2, obje.getTipoPiez());
            cmd.setString(3, obje.getMarcPiez());
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
    
    public boolean upda(Piezas obje){
        boolean resp = false;
        Connection cn = new Conexion().getConn();
        try {
            PreparedStatement cmd = cn.prepareStatement("update piezas set nomb_piez = ?, tipo_piez = ?, marc_piez = ? where codi_piez = ?;");
            cmd.setString(1, obje.getNombPiez());
            cmd.setString(2, obje.getTipoPiez());
            cmd.setString(3, obje.getMarcPiez());
            cmd.setInt(4, obje.getCodiPiez());
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
    
    public boolean dele(Piezas obje){
        boolean resp = false;
        Connection cn = new Conexion().getConn();
        try {
            PreparedStatement cmd = cn.prepareStatement("delete from piezas where codi_piez = ?");
            cmd.setInt(1, obje.getCodiPiez());
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
    
    public List<Piezas> consTodo(){
        List<Piezas> resp = new ArrayList<>();
        Connection cn = new Conexion().getConn();
        try {
            PreparedStatement cmd = cn.prepareStatement("Select * from piezas");
            ResultSet rs = cmd.executeQuery();
            while (rs.next())
            {
                resp.add(new Piezas(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4)));
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
    }
}
