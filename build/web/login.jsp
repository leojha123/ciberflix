<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>CIBERFLIX - Login</title>
    <script src="https://cdn.tailwindcss.com"></script>
    <link href="https://cdn.jsdelivr.net/npm/daisyui@2.51.5/dist/full.css" rel="stylesheet" />
</head>
<body class="bg-gray-900 flex items-center justify-center h-screen">
    <div class="card w-96 bg-gray-800 text-white shadow-lg">
        <div class="card-body">
            <h2 class="text-center text-2xl font-bold mb-4">CIBERFLIX Login</h2>
            <form action="LoginServlet" method="post">
                <div class="form-control mb-3">
                    <label class="label"><span class="label-text">Usuario</span></label>
                    <input type="text" name="usuario" placeholder="Usuario" class="input input-bordered w-full" required>
                </div>
                <div class="form-control mb-3">
                    <label class="label"><span class="label-text">Contraseña</span></label>
                    <input type="password" name="password" placeholder="Contraseña" class="input input-bordered w-full" required>
                </div>
                <c:if test="${not empty error}">
                    <p class="text-red-500 text-sm mb-2">${error}</p>
                </c:if>
                <div class="form-control mt-4">
                    <button type="submit" class="btn btn-primary w-full">Ingresar</button>
                </div>
            </form>
        </div>
    </div>
</body>
</html>