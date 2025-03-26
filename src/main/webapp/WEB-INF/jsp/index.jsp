<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %> <!-- Importar JSTL es usado actualmente -->

<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
    <style>
        .form-control {
            width: 100%; /* Forzar ancho completo */
            max-width: 300px; /* Opcional: límite de ancho */
        }
    </style>
    <title>Formulario de Usuario</title>
</head>
<body>
    
    <!-- Mostrar el mensaje de la variable 'texto' pasada al modelo -->
    <h2><c:out value="${texto}" /></h2>
    
    <h1>Enviar datos al controlador</h1>
    
    <!-- Formulario para capturar el email y la contraseña -->
    <form class="row g-3" action="/procesarFormulario" method="post">
        <div class="col-md-5">
            <label for="user" class="form-label">Email</label>
			<input type="email" class="form-control w-100" name="email" id="email" required>
        </div>
        <div class="col-md-12">
            <label for="password" class="form-label">Password</label>
            <input type="password" class="form-control w-100" id="password" name="password" required>
        </div>
        <div class="col-12">
            <button type="submit" class="btn btn-primary">Sign in</button>
        </div>
    </form>
    
    <!-- Mostrar el dato enviado y el mensaje (si está disponible en el modelo) -->
    <h2>Usuario enviado:</h2>
    <p><c:out value="${user}" /></p> <!-- Mostrar el valor del usuario -->
    <p style="background-color: orange"><c:out value="${mensaje}" /></p> <!-- Mostrar el mensaje -->

</body>
</html>
