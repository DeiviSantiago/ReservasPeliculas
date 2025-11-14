public abstract class Usuario {
    protected String nombreUsuario;
    protected String contraseña;

    public Usuario(String nombreUsuario, String contraseña) {
        this.nombreUsuario = nombreUsuario;
        this.contraseña = contraseña;
    }

    public boolean autenticar(String usuario, String pass) {
        return nombreUsuario.equals(usuario) && contraseña.equals(pass);
    }

    public abstract String getRol();
}

class Administrador extends Usuario {
    public Administrador(String usuario, String pass) {
        super(usuario, pass);
    }
    @Override
    public String getRol() { return "Administrador"; }
}

class UsuarioNormal extends Usuario {
    public UsuarioNormal(String usuario, String pass) {
        super(usuario, pass);
    }
    @Override
    public String getRol() { return "UsuarioNormal"; }
}
