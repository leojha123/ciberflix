package model;

public class Usuario {
    private int idUsuario;
    private String usuario;
    private String password;
    private String correo;
    private int estado;

    public Usuario() {}

    public Usuario(int idUsuario, String usuario, String password, String correo, int estado) {
        this.idUsuario = idUsuario;
        this.usuario = usuario;
        this.password = password;
        this.correo = correo;
        this.estado = estado;
    }

    public int getIdUsuario() { return idUsuario; }
    public void setIdUsuario(int idUsuario) { this.idUsuario = idUsuario; }

    public String getUsuario() { return usuario; }
    public void setUsuario(String usuario) { this.usuario = usuario; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public String getCorreo() { return correo; }
    public void setCorreo(String correo) { this.correo = correo; }

    public int getEstado() { return estado; }
    public void setEstado(int estado) { this.estado = estado; }
}
