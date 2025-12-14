package util;

import dao.CategoriaDAO;
import dao.PeliculaDAO;
import dao.UsuarioDAO;

public class DAOFactory {
    

    public static PeliculaDAO getPeliculaDAO() {
        return new PeliculaDAO();
    }
    
    public static UsuarioDAO getUsuarioDAO() {
        return new UsuarioDAO();
    }
    
    public static CategoriaDAO getCategoriaDAO() {
        return new CategoriaDAO();
    }
    
    @SuppressWarnings("unchecked")
    public static <T> T getDAO(Class<T> daoClass) {
        try {
            return daoClass.getDeclaredConstructor().newInstance();
        } catch (Exception e) {
            throw new RuntimeException("Error al crear instancia de " + daoClass.getName(), e);
        }
    }
    
    private DAOFactory() {
        throw new IllegalStateException("Esta es una clase utilitaria y no debe ser instanciada");
    }
}

