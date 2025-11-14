public class UsuarioNormal extends Usuario {
    public UsuarioNormal(String usuario, String pass) {
        super(usuario, pass);
    }
    @Override
    public String getRol() {
        return "UsuarioNormal";
    }
}
