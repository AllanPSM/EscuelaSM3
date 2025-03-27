<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
    <style>
        body {
            background-color: #f4f4f9;
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
            margin: 0;
        }

        .container {
            background-color: #fff;
            border-radius: 8px;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
            padding: 30px;
            width: 100%;
            max-width: 400px;
        }

        h1, h2 {
            text-align: center;
        }

        .form-control {
            width: 100%;
            margin-bottom: 15px;
        }

        .btn-primary {
            width: 100%;
        }

        .btn-secondary {
            width: 100%;
            margin-top: 10px;
            background-color: #6c757d;
            border-color: #6c757d;
        }

        .mensaje {
            background-color: orange;
            color: white;
            padding: 10px;
            border-radius: 5px;
            margin-top: 20px;
        }
    </style>
    <title>Formulario de Login</title>
</head>
<body>
    <div class="container">
        <h2><c:out value="${texto}" /></h2>
        <h1>Iniciar Sesión</h1>

        <!-- Formulario de login -->
		<form action="/iniciarSesion" method="post">
		    <div class="mb-3">
		        <label for="email" class="form-label">Email</label>
		        <input type="email" class="form-control" id="email" name="email" required>
		    </div>
		    <div class="mb-3">
		        <label for="password" class="form-label">Contraseña</label>
		        <input type="password" class="form-control" id="password" name="password" required>
		    </div>
		    <div class="mb-3">
		        <button type="submit" class="btn btn-primary">Iniciar Sesión</button>
		    </div>
		</form>


        <br><br>

        <!-- Botón para redirigir al formulario de index (sin necesidad de campos para registrar) -->
        <form action="/index" method="get">
            <button type="submit" class="btn btn-secondary">Registrar</button>
        </form>

    
        </div>
    </div>
</body>
</html>
