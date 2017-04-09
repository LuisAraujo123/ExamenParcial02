<%-- 
    Document   : bodega
    Created on : Apr 8, 2017, 9:06:42 AM
    Author     : bernardo
--%>

<%@page import="com.sv.udb.controlador.BodegaCtrl"%>
<%@page import="com.sv.udb.modelo.Bodega"%>
<%@page import="com.sv.udb.controlador.PiezasCtrl"%>
<%@page import="com.sv.udb.modelo.Piezas"%>
<%@page import="com.sv.udb.modelo.Piezas"%>
<%@page import="com.sv.udb.controlador.ProveedoresCtrl"%>
<%@page import="com.sv.udb.modelo.Proveedores"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel='stylesheet' href='webjars/Materialize/0.96.0/dist/css/materialize.min.css'>
        <link href="http://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
        <script type="text/javascript" src="webjars/jquery/2.1.1/jquery.min.js"></script>
        <script type="text/javascript" src="webjars/Materialize/0.96.0/dist/js/materialize.min.js"></script>
        <meta name='viewport' content='width=device-width, initial-scale=1.0'/>
        <title>JSP Page</title>
    </head>
    <body>
    <header class='navbar-fixed'>
        <nav class='brown'>
            <div class='nav-wrapper'>
                <a href='#' class='brand-logo'><i class='material-icons left hide-on-med-and-down'>dashboard</i></a>
                <a href='#' data-activates='mobile' class='button-collapse'><i class='material-icons'>menu</i></a>
                <ul class='right hide-on-med-and-down'>
                    <li><a href='bodega.jsp'><i class='material-icons left'>shop</i>Bodega</a></li>
                    <li><a href='proveedores.jsp'><i class='material-icons left'>shop_two</i>Proveedores</a></li>
                    <li><a href='index.jsp'><i class='material-icons left'>group</i>Piezas</a></li>
                </ul>
            </div>
        </nav>
    </header>
    <ul class='side-nav' id='mobile'>
        <li><a href='bodega.jsp'><i class='material-icons'>shop</i>Bodega</a></li>
        <li><a href='proveedores.jsp'><i class='material-icons'>shop_two</i>Proveedores</a></li>
        <li><a href='index.jsp'><i class='material-icons'>group</i>Piezas</a></li>
    </ul>
    <main class='container'>
        <h3 class='center-align'>Bodega</h3>
        <form method="POST" action="BodegaServ" name="Demo">
            <div class="row">
                <div class="input-field col s12 m6">
                    <i class='material-icons prefix'>note_add</i>
                    <input id='codi' type='text' name='codi' value="${codi}"/>
                    <label for='codi'>Código de bodega</label>
                </div>
                <div class="input-field col s12 m6">
                    <select id="codiProv" name="codiProv" required> 
                    <option value="" disabled selected>Seleccione un proveedor</option>
                    <%
                        int indi = 0;
                        for(Proveedores temp : new ProveedoresCtrl().consTodo())
                        {
                            
                    %> 
                    <option value="<%=temp.getCodiProv()%>"><%=temp.getNombProv()%></option>
                    <%
                        indi++;
                        }
                    %>
                    </select>
                </div>
                <div class="input-field col s12 m6">
                    <select id="codiPiez" name="codiPiez" required> 
                    <option value="" disabled selected>Seleccione una pieza</option>
                    <%
                        int indiPiez = 0;
                        for(Piezas temp : new PiezasCtrl().consTodo())
                        {
                            
                    %> 
                    <option value="<%=temp.getCodiPiez()%>"><%=temp.getNombPiez()%></option>
                    <%
                        indiPiez++;
                        }
                    %>
                    </select>
                </div>
                <div class="input-field col s12 m6">
                    <i class='material-icons prefix'>note_add</i>
                    <input id='cant' type='number' name='cant' class='validate' max='999.99' min='0.01' step='any' value="${cant}"/>
                    <label for='cant'>Cantidad</label>
                </div>
            </div>
            <div class='row center-align'>
                <input class='btn waves-effect grey' ${estGuar} type="submit" name="btonBode" value="Guardar">
                <input class='btn waves-effect blue' ${estModi} type="submit" name="btonBode" value="Modificar">
            </div>
        </form>
        <h3>Bodega (Lista)</h3>
        <form method="POST" action="BodegaServ" name="Tabl">
            <table class="highlight" border="1">
            <tr>
                <th>Cons</th>
                <th>Proveedor</th>
                <th>Pieza</th>
                <th>Cantidad</th>
                <th>Fecha</th>
            </tr>
            <%
                for(Bodega temp : new BodegaCtrl().consTodo())
                {
                    Proveedores prov = new ProveedoresCtrl().consUno(temp.getCodiProv());                    
                    Piezas piez = new PiezasCtrl().consUno(temp.getCodPiez());
            %>
            <tr>
                <td><p><input type="radio" name="codiBodeRadi" class='with-gap' id="<%=temp.getCodiBode()%>" value="<%=temp.getCodiBode()%>"><label for="<%=temp.getCodiBode()%>"></label></p></td>
                <td><%=prov%></td>
                <td><%=piez%></td>
                <td><%=temp.getCant()%></td>
                <td><%=temp.getFech()%></td>
            </tr>
            <%
                }
            %>
            </table>
            <input type="submit" name="btonBode" value="Consultar">
            <input ${estElim} type="submit" onclick="return confirm('¿Esta seguro de eliminar este registro?');" name="btonBode" value="Eliminar">
            <input type="submit" name="btonBode" value="Nuevo">
        </form>
    </main>
    <footer class='page-footer brown'>
        <div class='container'>
            <div class='row'>
                <div class='col s12 m6'>
                    <h5 class='white-text'>Dashboard</h5>
                    <a class='white-text' href='mailto:dacasoft@outlook.com'><i class='material-icons left'>help</i>Ayuda</a>
                </div>
                <div class='col s12 m6'>
                    <h5 class='white-text'>Enlaces</h5>
                    <a class='white-text' href='' target='_blank'><i class='material-icons left'>store</i>Java web</a>
                </div>
            </div>
        </div>
        <div class='footer-copyright'>
            <div class='container'>
                <span>©ITR, todos los derechos reservados.</span>
                <span class='white-text right'>Diseñado con <a class='red-text text-accent-1' href='http://materializecss.com/' target='_blank'><b>Materialize</b></a></span>
            </div>
        </div>
    </footer>
        <script>
        $(document).ready(function(){
            $('.button-collapse').sideNav();
            $('.materialboxed').materialbox();
            $('select').material_select();
        });
        </script>
    </body>
</html>
