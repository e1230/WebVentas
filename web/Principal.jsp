<%-- 
    Author     : Edgar
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
        <title>JSP Page</title>
    </head>
    <body>
      <nav class="navbar navbar-expand-lg navbar-light bg-info">
        <div class="collapse navbar-collapse" id="navbarNav">
            <ul class="navbar-nav">
              <li class="nav-item active">
                <a style="margin-left: 10px; border: none;" class="btn btn-outline-light" href="#">HOME</a>
              </li>
              <li class="nav-item">
                  <a style="margin-left: 10px; border: none;" class="btn btn-outline-light" href="Controlador?menu=Producto&accion=Listar" target="myFrame">Producto</a>
              </li>
              <li class="nav-item">
                <a style="margin-left: 10px; border: none;"  class="btn btn-outline-light" href="Controlador?menu=Empleado&accion=Listar" target="myFrame">Empleado</a>
              </li>
              <li class="nav-item">
                <a style="margin-left: 10px; border: none;"  class="btn btn-outline-light" href="Controlador?menu=Cliente" target="myFrame">Clientes</a>
              </li>
              <li class="nav-item">
                <a style="margin-left: 10px; border: none;"  class="btn btn-outline-light" href="Controlador?menu=NuevaVenta&accion=default" target="myFrame">Nueva venta</a>
              </li>
            </ul>
            <div class="dropdown">
                <button style="border: none;" class="btn btn-outline-light dropdown-toggle" type="button" id="dropdownMenuButton" data-bs-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                  ${usuario.getNom()}
                </button>
                <div class="dropdown-menu text-center" aria-labelledby="dropdownMenuButton">
                    <ul class="dropdown-item" href="#">
                        <img src="img/usuario.png" alt="user" height="60" width="60"/>
                    </ul>
                  <a class="dropdown-item" href="#">${usuario.getUser()}</a>
                  <a class="dropdown-item" href="#">usuario@usuario.com</a>
                  <div class="dropdown-divider"></div>
                  <form action="Validar" method="POST">
                      <button name="accion" value="salir" class="dropdown-item" href="#">Salir</button>
                  </form>
                </div>
            </div>
        </div>
    </nav>
    <div class="m-4" style="height: 550px;"> 
        <iframe name="myFrame" style="height: 100%; width: 100%;">
            
        </iframe>
    </div>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>
    </body>
</html>
