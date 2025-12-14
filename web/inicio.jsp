<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="es" data-theme="dark">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>CIBERFLIX - Tu plataforma de películas</title>
    <script src="https://cdn.tailwindcss.com"></script>
    <link href="https://cdn.jsdelivr.net/npm/daisyui@4.4.19/dist/full.css" rel="stylesheet" type="text/css" />
    <style>
        @import url('https://fonts.googleapis.com/css2?family=Inter:wght@400;500;600;700;800;900&display=swap');
        
        * {
            font-family: 'Inter', -apple-system, BlinkMacSystemFont, 'Segoe UI', sans-serif;
        }
        
        body {
            background-color: #0a0a0a;
        }
        
        .hero-banner {
            background: linear-gradient(to right, rgba(0,0,0,0.9) 30%, rgba(0,0,0,0.4) 70%), 
                        url('https://images.unsplash.com/photo-1536440136628-849c177e76a1?w=1920&q=80') center/cover;
            min-height: 75vh;
        }
        
        @keyframes fadeInUp {
            from {
                opacity: 0;
                transform: translateY(30px);
            }
            to {
                opacity: 1;
                transform: translateY(0);
            }
        }
        
        .animate-fade-in {
            animation: fadeInUp 0.8s ease-out forwards;
        }
        
        .movie-card {
            transition: all 0.3s cubic-bezier(0.4, 0, 0.2, 1);
        }
        
        .movie-card:hover {
            transform: scale(1.05) translateY(-8px);
            z-index: 10;
        }
        
        .movie-card:hover .movie-overlay {
            opacity: 1;
        }
        
        .movie-overlay {
            opacity: 0;
            transition: opacity 0.3s ease;
        }
        
        .navbar-glass {
            background: rgba(10, 10, 10, 0.7);
            backdrop-filter: blur(10px);
            border-bottom: 1px solid rgba(255, 255, 255, 0.05);
        }
        
        .logo-netflix {
            background: linear-gradient(to right, #e50914, #b20710);
            -webkit-background-clip: text;
            -webkit-text-fill-color: transparent;
            background-clip: text;
            font-weight: 900;
            letter-spacing: -1px;
        }
        
        html {
            scroll-behavior: smooth;
        }
    </style>
</head>
<body class="bg-base-300">

<!-- Navbar fija -->
<div class="navbar navbar-glass fixed top-0 z-50 px-8">
    <div class="navbar-start">
        <div class="dropdown lg:hidden">
            <label tabindex="0" class="btn btn-ghost btn-circle">
                <svg xmlns="http://www.w3.org/2000/svg" class="h-5 w-5" fill="none" viewBox="0 0 24 24" stroke="currentColor">
                    <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M4 6h16M4 12h16M4 18h7" />
                </svg>
            </label>
            <ul tabindex="0" class="menu menu-sm dropdown-content mt-3 z-[1] p-2 shadow bg-base-200 rounded-box w-52">
                <li><a href="InicioServlet">Inicio</a></li>
                <li><a href="#peliculas">Películas</a></li>
            </ul>
        </div>
        <a class="text-3xl font-black logo-netflix">CIBERFLIX</a>
        <div class="hidden lg:flex ml-8">
            <ul class="menu menu-horizontal px-1 gap-2">
                <li><a href="InicioServlet" class="hover:text-red-500 transition-colors">Inicio</a></li>
                <li><a href="#peliculas" class="hover:text-red-500 transition-colors">Películas</a></li>
            </ul>
        </div>
    </div>
    
    <div class="navbar-end gap-4">
        <a href="RegistrarPeliculaServlet" class="btn btn-error btn-sm gap-2">
            <svg xmlns="http://www.w3.org/2000/svg" class="h-4 w-4" viewBox="0 0 20 20" fill="currentColor">
                <path fill-rule="evenodd" d="M10 3a1 1 0 011 1v5h5a1 1 0 110 2h-5v5a1 1 0 11-2 0v-5H4a1 1 0 110-2h5V4a1 1 0 011-1z" clip-rule="evenodd" />
            </svg>
            Registrar Peliculas
        </a>
        
        <div class="dropdown dropdown-end">
            <label tabindex="0" class="btn btn-ghost btn-circle avatar placeholder">
                <div class="bg-red-600 text-white rounded-full w-10">
                    <span class="text-lg font-bold">${sessionScope.usuario.usuario.substring(0,1).toUpperCase()}</span>
                </div>
            </label>
            <ul tabindex="0" class="mt-3 z-[1] p-2 shadow menu menu-sm dropdown-content bg-base-200 rounded-box w-52">
                <li class="menu-title">
                    <span>Bienvenido, ${sessionScope.usuario.usuario}</span>
                </li>
                <li><a href="LogoutServlet" class="text-error">Cerrar sesión</a></li>
            </ul>
        </div>
    </div>
</div>

<!-- Hero Banner -->
<div class="hero hero-banner mt-16">
    <div class="hero-content text-left max-w-7xl w-full">
        <div class="max-w-2xl animate-fade-in">
            <h1 class="text-6xl font-black mb-4 leading-tight">
                Bienvenido a <span class="logo-netflix">CIBERFLIX</span>
            </h1>
            <p class="text-xl mb-8 text-gray-300 leading-relaxed">
                Miles de películas para disfrutar. Explora nuestro catálogo completo 
                de los mejores títulos del cine mundial.
            </p>
        </div>
    </div>
</div>

<!-- Mensajes de Éxito/Error -->
<c:if test="${not empty sessionScope.exito}">
    <div class="max-w-7xl mx-auto px-8 mt-8">
        <div class="alert alert-success shadow-lg">
            <svg xmlns="http://www.w3.org/2000/svg" class="stroke-current flex-shrink-0 h-6 w-6" fill="none" viewBox="0 0 24 24">
                <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M9 12l2 2 4-4m6 2a9 9 0 11-18 0 9 9 0 0118 0z" />
            </svg>
            <span>${sessionScope.exito}</span>
        </div>
    </div>
    <c:remove var="exito" scope="session"/>
</c:if>

<c:if test="${not empty sessionScope.error}">
    <div class="max-w-7xl mx-auto px-8 mt-8">
        <div class="alert alert-error shadow-lg">
            <svg xmlns="http://www.w3.org/2000/svg" class="stroke-current flex-shrink-0 h-6 w-6" fill="none" viewBox="0 0 24 24">
                <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M10 14l2-2m0 0l2-2m-2 2l-2-2m2 2l2 2m7-2a9 9 0 11-18 0 9 9 0 0118 0z" />
            </svg>
            <span>${sessionScope.error}</span>
        </div>
    </div>
    <c:remove var="error" scope="session"/>
</c:if>

<!-- Sección de Películas -->
<section id="peliculas" class="py-12 px-8">
    <div class="max-w-7xl mx-auto">
        <div class="flex flex-col sm:flex-row justify-between items-start sm:items-center gap-4 mb-6">
            <h2 class="text-3xl font-bold">Catálogo de Películas</h2>
            
            <!-- 🔍 BUSCADOR DE PELÍCULAS -->
            <form action="InicioServlet" method="get" class="flex gap-2 w-full sm:w-auto">
                <input type="text" 
                       name="busqueda" 
                       value="${param.busqueda}" 
                       placeholder="Buscar película..." 
                       class="input input-bordered w-full sm:w-64 bg-gray-800 text-white">
                <button type="submit" class="btn btn-primary">
                    <svg xmlns="http://www.w3.org/2000/svg" class="h-5 w-5" viewBox="0 0 20 20" fill="currentColor">
                        <path fill-rule="evenodd" d="M8 4a4 4 0 100 8 4 4 0 000-8zM2 8a6 6 0 1110.89 3.476l4.817 4.817a1 1 0 01-1.414 1.414l-4.816-4.816A6 6 0 012 8z" clip-rule="evenodd" />
                    </svg>
                    Buscar
                </button>
                <c:if test="${not empty param.busqueda}">
                    <a href="InicioServlet" class="btn btn-ghost">
                        Limpiar
                    </a>
                </c:if>
            </form>
        </div>
        
        <!-- Mostrar término de búsqueda si existe -->
        <c:if test="${not empty param.busqueda}">
            <div class="alert alert-info mb-6">
                <svg xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 24 24" class="stroke-current shrink-0 w-6 h-6">
                    <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M13 16h-1v-4h-1m1-4h.01M21 12a9 9 0 11-18 0 9 9 0 0118 0z"></path>
                </svg>
                <span>Resultados para: <strong>"${param.busqueda}"</strong> (${peliculas.size()} películas encontradas)</span>
            </div>
        </c:if>
        
        <div class="grid grid-cols-1 sm:grid-cols-2 lg:grid-cols-4 xl:grid-cols-5 gap-6">
            <c:forEach var="pelicula" items="${peliculas}">
                <div class="movie-card cursor-pointer">
                    <div class="card bg-base-100 shadow-xl h-full">
                        <figure class="relative h-80">
                            <img src="${pelicula.urlPortada}" 
                                 alt="${pelicula.titulo}" 
                                 class="w-full h-full object-cover"
                                 onerror="this.src='https://via.placeholder.com/300x450/1a1a1a/666?text=Sin+Imagen'">
                            
                            <!-- Overlay con info al hover -->
                            <div class="movie-overlay absolute inset-0 bg-gradient-to-t from-black via-black/50 to-transparent flex flex-col justify-end p-4">
                                <div class="space-y-2">
                                    <div class="flex gap-2">
                                        <button class="btn btn-circle btn-sm btn-neutral hover:scale-110 transition-transform">
                                            <svg xmlns="http://www.w3.org/2000/svg" class="h-4 w-4" viewBox="0 0 20 20" fill="currentColor">
                                                <path fill-rule="evenodd" d="M10 18a8 8 0 100-16 8 8 0 000 16zM9.555 7.168A1 1 0 008 8v4a1 1 0 001.555.832l3-2a1 1 0 000-1.664l-3-2z" clip-rule="evenodd" />
                                            </svg>
                                        </button>
                                        
                                        <!-- Botón Editar -->
                                        <a href="EditarPeliculaServlet?id=${pelicula.idPelicula}" 
                                           class="btn btn-circle btn-sm btn-warning hover:scale-110 transition-transform"
                                           title="Editar película">
                                            <svg xmlns="http://www.w3.org/2000/svg" class="h-4 w-4" viewBox="0 0 20 20" fill="currentColor">
                                                <path d="M13.586 3.586a2 2 0 112.828 2.828l-.793.793-2.828-2.828.793-.793zM11.379 5.793L3 14.172V17h2.828l8.38-8.379-2.83-2.828z" />
                                            </svg>
                                        </a>
                                        
                                        <!-- Botón Eliminar -->
                                        <button onclick="confirmarEliminacion(${pelicula.idPelicula}, '${pelicula.titulo}')" 
                                                class="btn btn-circle btn-sm btn-error hover:scale-110 transition-transform"
                                                title="Eliminar película">
                                            <svg xmlns="http://www.w3.org/2000/svg" class="h-4 w-4" viewBox="0 0 20 20" fill="currentColor">
                                                <path fill-rule="evenodd" d="M9 2a1 1 0 00-.894.553L7.382 4H4a1 1 0 000 2v10a2 2 0 002 2h8a2 2 0 002-2V6a1 1 0 100-2h-3.382l-.724-1.447A1 1 0 0011 2H9zM7 8a1 1 0 012 0v6a1 1 0 11-2 0V8zm5-1a1 1 0 00-1 1v6a1 1 0 102 0V8a1 1 0 00-1-1z" clip-rule="evenodd" />
                                            </svg>
                                        </button>
                                    </div>
                                    <div class="badge badge-sm badge-ghost">${pelicula.nombreCategoria}</div>
                                </div>
                            </div>
                        </figure>
                        
                        <div class="card-body p-4">
                            <h3 class="card-title text-base line-clamp-2">${pelicula.titulo}</h3>
                            <div class="flex items-center justify-between text-sm mt-2">
                                <div class="flex items-center gap-2 text-gray-400">
                                    <span class="badge badge-success badge-sm">${pelicula.anio}</span>
                                    <span>${pelicula.duracion} min</span>
                                </div>
                                <div class="text-error font-bold">S/. ${pelicula.precio}</div>
                            </div>
                            <p class="text-xs text-gray-400 line-clamp-2 mt-2">${pelicula.descripcion}</p>
                        </div>
                    </div>
                </div>
            </c:forEach>
        </div>
        
        <!-- Si no hay películas -->
        <c:if test="${empty peliculas}">
            <div class="alert alert-warning">
                <svg xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 24 24" class="stroke-current shrink-0 w-6 h-6">
                    <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M12 9v2m0 4h.01m-6.938 4h13.856c1.54 0 2.502-1.667 1.732-3L13.732 4c-.77-1.333-2.694-1.333-3.464 0L3.34 16c-.77 1.333.192 3 1.732 3z"></path>
                </svg>
                <span>
                    <c:choose>
                        <c:when test="${not empty param.busqueda}">
                            No se encontraron películas con el término "<strong>${param.busqueda}</strong>". 
                            <a href="InicioServlet" class="link link-primary">Ver todas</a>
                        </c:when>
                        <c:otherwise>
                            No hay películas registradas. <a href="RegistrarPeliculaServlet" class="link link-primary">Registra la primera</a>
                        </c:otherwise>
                    </c:choose>
                </span>
            </div>
        </c:if>
    </div>
</section>

<!-- Modal de Confirmación para Eliminar -->
<dialog id="modal_eliminar" class="modal">
    <div class="modal-box">
        <h3 class="font-bold text-lg">️ Confirmar Eliminación</h3>
        <p class="py-4">¿Estás seguro de que deseas eliminar la película <strong id="titulo_pelicula"></strong>?</p>
        <p class="text-sm text-gray-400">Esta acción no se puede deshacer.</p>
        <div class="modal-action">
            <form method="dialog">
                <button class="btn btn-ghost">Cancelar</button>
            </form>
            <a id="btn_confirmar_eliminar" href="#" class="btn btn-error">Eliminar</a>
        </div>
    </div>
    <form method="dialog" class="modal-backdrop">
        <button>close</button>
    </form>
</dialog>

<!-- Footer -->
<footer class="footer footer-center p-10 bg-base-200 text-base-content mt-20">
    <div>
        <p class="text-3xl font-black logo-netflix mb-4">CIBERFLIX</p>
        <p class="text-sm text-gray-400">
            Plataforma de streaming PIPIPI        </p>
    </div>
    <div>
        <div class="grid grid-flow-col gap-4">
            <a class="link link-hover">Acerca de</a>
            <a class="link link-hover">Contacto</a>
            <a class="link link-hover">Privacidad</a>
            <a class="link link-hover">Términos</a>
        </div>
    </div>
    <div>
        <p class="text-sm text-gray-500">&copy; 2025 CIBERFLIX. Todos los derechos reservados.</p>
    </div>
</footer>

<script>
    function confirmarEliminacion(id, titulo) {
        document.getElementById('titulo_pelicula').textContent = titulo;
        document.getElementById('btn_confirmar_eliminar').href = 'EliminarPeliculaServlet?id=' + id;
        document.getElementById('modal_eliminar').showModal();
    }
</script>

</body>
</html>