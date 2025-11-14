public class Administrador extends Usuario {
    public Administrador(String usuario, String pass) {
        super(usuario, pass);
    }
    @Override
    public String getRol() {
        return "Administrador";
    }
}
