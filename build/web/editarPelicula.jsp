<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Editar Película - CIBERFLIX</title>
    <link href="https://cdn.jsdelivr.net/npm/tailwindcss@2.2.19/dist/tailwind.min.css" rel="stylesheet">
    <link href="https://cdn.jsdelivr.net/npm/daisyui@2.20.0/dist/full.css" rel="stylesheet">
</head>
<body class="bg-gray-900 text-white min-h-screen">

<!-- Header con navegación -->
<header class="p-6 flex justify-between items-center bg-gray-800">
    <h1 class="text-3xl font-bold text-red-600">CIBERFLIX</h1>
    <nav class="flex gap-4">
        <a href="InicioServlet" class="btn btn-sm btn-ghost">Volver al Inicio</a>
        <span class="mr-4">Bienvenido, ${sessionScope.usuario.usuario}</span>
        <a href="LogoutServlet" class="btn btn-sm btn-outline btn-error">Cerrar Sesión</a>
    </nav>
</header>

<div class="container mx-auto p-6 max-w-2xl">
    <h2 class="text-3xl font-bold mb-6">Editar Película</h2>
    
    <!-- Mensajes de error -->
    <c:if test="${not empty error}">
        <div class="alert alert-error mb-4 shadow-lg">
            <div>
                <svg xmlns="http://www.w3.org/2000/svg" class="stroke-current flex-shrink-0 h-6 w-6" fill="none" viewBox="0 0 24 24">
                    <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M10 14l2-2m0 0l2-2m-2 2l-2-2m2 2l2 2m7-2a9 9 0 11-18 0 9 9 0 0118 0z" />
                </svg>
                <span>${error}</span>
            </div>
        </div>
    </c:if>
    
    <!-- Mensaje de éxito -->
    <c:if test="${not empty exito}">
        <div class="alert alert-success mb-4 shadow-lg">
            <div>
                <svg xmlns="http://www.w3.org/2000/svg" class="stroke-current flex-shrink-0 h-6 w-6" fill="none" viewBox="0 0 24 24">
                    <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M9 12l2 2 4-4m6 2a9 9 0 11-18 0 9 9 0 0118 0z" />
                </svg>
                <span>${exito}</span>
            </div>
        </div>
    </c:if>
    
    <!-- Formulario de edición -->
    <form action="EditarPeliculaServlet" method="post" class="space-y-4 bg-gray-800 p-6 rounded-lg shadow-lg">
        
        <!-- ID oculto -->
        <input type="hidden" name="id" value="${pelicula.idPelicula}">
        
        <div class="form-control">
            <label class="label">
                <span class="label-text">Título *</span>
            </label>
            <input type="text" name="titulo" value="${pelicula.titulo}" 
                   placeholder="Ej: Five Nights at Freddy's" 
                   class="input input-bordered w-full bg-gray-700 text-white" 
                   required>
        </div>
        
        <div class="form-control">
            <label class="label">
                <span class="label-text">Descripción *</span>
            </label>
            <textarea name="descripcion" 
                      placeholder="Describe la película..." 
                      class="textarea textarea-bordered w-full h-24 bg-gray-700 text-white" 
                      required>${pelicula.descripcion}</textarea>
        </div>
        
        <div class="form-control">
            <label class="label">
                <span class="label-text">Duración (minutos) *</span>
            </label>
            <input type="number" name="duracion" value="${pelicula.duracion}" 
                   placeholder="Ej: 120" 
                   min="1" 
                   class="input input-bordered w-full bg-gray-700 text-white" 
                   required>
        </div>
        
        <div class="form-control">
            <label class="label">
                <span class="label-text">URL de Portada *</span>
            </label>
            <input type="text" name="urlPortada" value="${pelicula.urlPortada}" 
                   placeholder="https://ejemplo.com/imagen.jpg" 
                   class="input input-bordered w-full bg-gray-700 text-white" 
                   required>
            <label class="label">
                <span class="label-text-alt">Puede ser una URL o una imagen en base64</span>
            </label>
        </div>
        
        <div class="grid grid-cols-2 gap-4">
            <div class="form-control">
                <label class="label">
                    <span class="label-text">Año *</span>
                </label>
                <input type="number" name="anio" value="${pelicula.anio}" 
                       placeholder="Ej: 2025" 
                       min="1900" 
                       max="2100" 
                       class="input input-bordered w-full bg-gray-700 text-white" 
                       required>
            </div>
            
            <div class="form-control">
                <label class="label">
                    <span class="label-text">Precio (S/.) *</span>
                </label>
                <input type="number" name="precio" value="${pelicula.precio}" 
                       placeholder="Ej: 25.00" 
                       step="0.01" 
                       min="0" 
                       class="input input-bordered w-full bg-gray-700 text-white" 
                       required>
            </div>
        </div>
        
        <div class="form-control">
            <label class="label">
                <span class="label-text">Categoría *</span>
            </label>
            <select name="idCategoria" class="select select-bordered w-full bg-gray-700 text-white" required>
                <option value="">-- Seleccione una categoría --</option>
                <c:forEach var="cat" items="${categorias}">
                    <option value="${cat.idCategoria}" 
                            ${cat.idCategoria == pelicula.idCategoria ? 'selected' : ''}>
                        ${cat.nombre}
                    </option>
                </c:forEach>
            </select>
        </div>
        
        <div class="flex gap-4 mt-6">
            <button type="submit" class="btn btn-success flex-1">
                Actualizar Película
            </button>
            <a href="InicioServlet" class="btn btn-ghost flex-1">
                Cancelar
            </a>
        </div>
    </form>
</div>

<footer class="p-6 text-center text-gray-500 bg-gray-900 mt-12">
    &copy; 2025 CIBERFLIX. Todos los derechos reservados.
</footer>

</body>
</html>