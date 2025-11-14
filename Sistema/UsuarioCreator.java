public abstract class UsuarioCreator {
    public abstract Usuario crearUsuario(String usuario, String pass);
}

class AdministradorCreator extends UsuarioCreator {
    @Override
    public Usuario crearUsuario(String usuario, String pass) {
        return new Administrador(usuario, pass);
    }
}

class UsuarioNormalCreator extends UsuarioCreator {
    @Override
    public Usuario crearUsuario(String usuario, String pass) {
        return new UsuarioNormal(usuario, pass);
    }
}
