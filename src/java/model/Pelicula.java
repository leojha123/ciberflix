package model;

public class Pelicula {
    private int idPelicula;
    private String titulo;
    private String descripcion;
    private int duracion;
    private String urlPortada;
    private int anio;
    private double precio;
    private int idCategoria;
    private String nombreCategoria;
    
    public Pelicula() {}
    
    public Pelicula(String titulo, String descripcion, int duracion, String urlPortada, int anio, double precio, int idCategoria) {
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.duracion = duracion;
        this.urlPortada = urlPortada;
        this.anio = anio;
        this.precio = precio;
        this.idCategoria = idCategoria;
    }
    
    public int getIdPelicula() { return idPelicula; }
    public void setIdPelicula(int idPelicula) { this.idPelicula = idPelicula; }
    
    public String getTitulo() { return titulo; }
    public void setTitulo(String titulo) { this.titulo = titulo; }
    
    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }
    
    public int getDuracion() { return duracion; }
    public void setDuracion(int duracion) { this.duracion = duracion; }
    
    public String getUrlPortada() { return urlPortada; }
    public void setUrlPortada(String urlPortada) { this.urlPortada = urlPortada; }
    
    public int getAnio() { return anio; }
    public void setAnio(int anio) { this.anio = anio; }
    
    public double getPrecio() { return precio; }
    public void setPrecio(double precio) { this.precio = precio; }
    
    public int getIdCategoria() { return idCategoria; }
    public void setIdCategoria(int idCategoria) { this.idCategoria = idCategoria; }
    
    public String getNombreCategoria() { return nombreCategoria; }
    public void setNombreCategoria(String nombreCategoria) { this.nombreCategoria = nombreCategoria; }
}