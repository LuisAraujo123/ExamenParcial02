<%@page import="com.sv.udb.controlador.PiezasCtrl"%>
<%@page import="com.sv.udb.modelo.Piezas"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

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
        <h3 class='center-align'>Piezas</h3>
        <form method="POST" action="PiezasServ" name="Demo">
            <div class="row">
                <div class="input-field col s12 m6">
                    <i class='material-icons prefix'>note_add</i>
                    <input id='codi' type='text' name='codi' value="${codi}"/>
                    <label for='codi'>Código de la pieza</label>
                </div>
                <div class="input-field col s12 m6">
                    <i class='material-icons prefix'>note_add</i>
                    <input id='nomb' type='text' name='nomb' value="${nomb}"/>
                    <label for='nomb'>Nombre de la pieza</label>
                </div>
                <div class="input-field col s12 m6">
                    <i class='material-icons prefix'>note_add</i>
                    <input id='tipo' type='text' name='tipo' value="${tipo}"/>
                    <label for='tipo'>Tipo de la pieza</label>
                </div>
                <div class="input-field col s12 m6">
                    <i class='material-icons prefix'>note_add</i>
                    <input id='marc' type='text' name='marc' value="${marc}"/>
                    <label for='marc'>Marca de la pieza</label>
                </div>
            </div>
            <div class='row center-align'>
                <input class='btn waves-effect grey' ${estGuar} type="submit" name="btonPiez" value="Guardar">
                <input class='btn waves-effect blue' ${estModi} type="submit" name="btonPiez" value="Modificar">
            </div>
        </form>
        <h3>Lista de piezas</h3>
        <form method="POST" action="PiezasServ" name="Tabl">
            <table class="highlight" border="1">
            <tr>
                <th>Cons</th>
                <th>Nombre</th>
                <th>Tipo</th>
                <th>Marca</th>
            </tr>
            <%
                for(Piezas temp : new PiezasCtrl().consTodo())
                { 
            %>
            <tr>
                <td><p><input type="radio" name="codiPiezRadi" class='with-gap' id="<%=temp.getCodiPiez()%>" value="<%=temp.getCodiPiez()%>"><label for="<%=temp.getCodiPiez()%>"></label></p></td>
                <td><%=temp.getNombPiez()%></td>
                <td><%=temp.getTipoPiez()%></td>
                <td><%=temp.getMarcPiez()%></td>
            </tr>
            <%
                }
            %>
            </table>
            <input type="submit" name="btonPiez" value="Consultar">
            <input ${estElim} type="submit" onclick="return confirm('¿Esta seguro de eliminar este registro?');" name="btonPiez" value="Eliminar">
            <input type="submit" name="btonPiez" value="Nuevo">
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
