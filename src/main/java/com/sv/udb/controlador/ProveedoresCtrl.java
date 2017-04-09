/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sv.udb.controlador;

import com.sv.udb.modelo.Proveedores;
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
public class ProveedoresCtrl {
    
    public boolean guar(Proveedores obje){
        boolean resp = false;
        Connection cn = new Conexion().getConn();
        try {
            PreparedStatement cmd = cn.prepareStatement("INSERT INTO proveedores VALUES(NULL, ?, ?, ?)");
            cmd.setString(1, obje.getNombProv());
            cmd.setString(2, obje.getDireProv());
            cmd.setString(3, obje.getTeleProv());
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
    
    public boolean upda(Proveedores obje){
        boolean resp = false;
        Connection cn = new Conexion().getConn();
        try {
            PreparedStatement cmd = cn.prepareStatement("update proveedores set nomb_prov = ?, dire_prov = ?, tele_prov = ? where codi_prov = ?;");
            cmd.setString(1, obje.getNombProv());
            cmd.setString(2, obje.getDireProv());
            cmd.setString(3, obje.getTeleProv());
            cmd.setInt(4, obje.getCodiProv());
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
    
    public boolean dele(Proveedores obje){
        boolean resp = false;
        Connection cn = new Conexion().getConn();
        try {
            PreparedStatement cmd = cn.prepareStatement("delete from proveedores where codi_prov = ?");
            cmd.setInt(1, obje.getCodiProv());
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
    
    public List<Proveedores> consTodo(){
        List<Proveedores> resp = new ArrayList<>();
        Connection cn = new Conexion().getConn();
        try {
            PreparedStatement cmd = cn.prepareStatement("Select * from proveedores");
            ResultSet rs = cmd.executeQuery();
            while (rs.next())
            {
                resp.add(new Proveedores(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4)));
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
    
    public Proveedores consUno(int id){
        Proveedores resp = null;
        Connection cn = new Conexion().getConn();
        try {
            PreparedStatement cmd = cn.prepareStatement("Select * from proveedores where codi_prov = ?");
            cmd.setInt(1, id);
            ResultSet rs = cmd.executeQuery();
            if(rs.next())
            {
                resp = new Proveedores(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4));
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
