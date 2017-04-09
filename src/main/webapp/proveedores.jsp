<%-- 
    Document   : proveedores
    Created on : Apr 8, 2017, 9:06:30 AM
    Author     : bernardo
--%>

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
        <h3 class='center-align'>Proveedores</h3>
        <form method="POST" action="ProveedoresServ" name="Demo">
            <div class="row">
                <div class="input-field col s12 m6">
                    <i class='material-icons prefix'>note_add</i>
                    <input id='codi' type='text' name='codi' value="${codi}"/>
                    <label for='codi'>Código del proveedor</label>
                </div>
                <div class="input-field col s12 m6">
                    <i class='material-icons prefix'>note_add</i>
                    <input id='nomb' type='text' name='nomb' value="${nomb}"/>
                    <label for='nomb'>Nombre del proveedor</label>
                </div>
                <div class="input-field col s12 m6">
                    <i class='material-icons prefix'>note_add</i>
                    <input id='dire' type='text' name='dire' value="${dire}"/>
                    <label for='dire'>Dirección del proveedor</label>
                </div>
                <div class="input-field col s12 m6">
                    <i class='material-icons prefix'>note_add</i>
                    <input id='tele' type='text' name='tele' value="${tele}"  class='validate' pattern="^[2|6|7]{1}([\d]{3}[-][\d]{4})$"/>
                    <label for='tele'>Teléfono del proveedor</label>
                </div>
            </div>
            <div class='row center-align'>
                <input class='btn waves-effect grey' ${estGuar} type="submit" name="btonProv" value="Guardar">
                <input class='btn waves-effect blue' ${estModi} type="submit" name="btonProv" value="Modificar">
            </div>
        </form>
        <h3>Lista de proveedores</h3>
        <form method="POST" action="ProveedoresServ" name="Tabl">
            <table class="highlight" border="1">
            <tr>
                <th>Cons</th>
                <th>Nombre</th>
                <th>Dirección</th>
                <th>Teléfono</th>
            </tr>
            <%
                for(Proveedores temp : new ProveedoresCtrl().consTodo())
                { 
            %>
            <tr>
                <td><p><input type="radio" name="codiProvRadi" class='with-gap' id="<%=temp.getCodiProv()%>" value="<%=temp.getCodiProv()%>"><label for="<%=temp.getCodiProv()%>"></label></p></td>
                <td><%=temp.getNombProv()%></td>
                <td><%=temp.getDireProv()%></td>
                <td><%=temp.getTeleProv()%></td>
            </tr>
            <%
                }
            %>
            </table>
            <input type="submit" name="btonProv" value="Consultar">
            <input ${estElim} type="submit" onclick="return confirm('¿Esta seguro de eliminar este registro?');" name="btonProv" value="Eliminar">
            <input type="submit" name="btonProv" value="Nuevo">
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
