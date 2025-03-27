<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Inicio</title>
    
    <!-- Estilos CSS -->
    <style>
        /* Estilos generales */
        body {
            font-family: 'Arial', sans-serif;
            background: linear-gradient(to right, #667eea, #764ba2);
            color: white;
            margin: 0;
            padding: 20px;
            display: flex;
            justify-content: center;
            align-items: center;
            min-height: 100vh;
        }

        /* Contenedor principal */
        .container {
            background: rgba(0, 0, 0, 0.8);
            padding: 20px;
            border-radius: 10px;
            max-width: 800px;
            width: 90%;
            text-align: center;
            box-shadow: 0 4px 10px rgba(0, 0, 0, 0.3);
        }

        h1, h2 {
            margin-bottom: 15px;
        }

        /* Estilo del JSON */
        .json-container {
            background-color: #1e1e1e;
            color: #00ffcc;
            padding: 15px;
            border-radius: 5px;
            text-align: left;
            overflow-x: auto;
            max-height: 400px;
            font-family: 'Courier New', monospace;
            white-space: pre-wrap;
            word-wrap: break-word;
            border: 1px solid #444;
        }

        /* Scroll bonito */
        .json-container::-webkit-scrollbar {
            width: 10px;
        }
        .json-container::-webkit-scrollbar-thumb {
            background: #00ffcc;
            border-radius: 5px;
        }

        /* Botón de recarga */
        .btn {
            background: #00ffcc;
            color: black;
            padding: 10px 15px;
            border: none;
            border-radius: 5px;
            cursor: pointer;
            font-size: 16px;
            margin-top: 10px;
            transition: 0.3s;
        }
        .btn:hover {
            background: #009977;
        }

    </style>
</head>
<body>

<div class="container">
    <h1>${texto}</h1> <!-- Mensaje de bienvenida -->
    <h2>📡 Datos de la API:</h2>

    <!-- JSON sin formatear (se oculta inicialmente) -->
    <pre id="rawData" class="json-container" style="display: none;">${apiData}</pre>

    <!-- JSON Formateado -->
    <pre id="formattedData" class="json-container"></pre>

    <!-- Botón para recargar los datos -->
    <button class="btn" onclick="formatJSON()">🔄 Recargar Datos</button>
</div>

<script>
    function formatJSON() {
        var rawData = `${apiData}`; // Datos de la API en formato String
        
        try {
            var jsonData = JSON.parse(rawData); // Convertimos a objeto JSON
            var formattedJson = JSON.stringify(jsonData, null, 4); // Aplicamos formato bonito

            // Mostramos el JSON formateado
            document.getElementById("formattedData").textContent = formattedJson;
        } catch (error) {
            console.error("Error al parsear JSON:", error);
            document.getElementById("formattedData").textContent = "⚠️ Error al cargar los datos.";
        }
    }

    // Ejecutar automáticamente al cargar la páginaaaaa
    formatJSON();
</script>

</body>
</html>
