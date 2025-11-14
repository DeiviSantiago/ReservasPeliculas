import java.util.ArrayList;
import java.util.List;

public class Reserva {
    private Usuario usuario;
    private Pelicula pelicula;
    private String horario;
    private List<String> asientos = new ArrayList<>();

    public Reserva(Usuario usuario, Pelicula pelicula, String horario) {
        this.usuario = usuario;
        this.pelicula = pelicula;
        this.horario = horario;
    }

    public void agregarAsiento(String asiento) {
        asientos.add(asiento);
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public Pelicula getPelicula() {
        return pelicula;
    }

    public String getHorario() {
        return horario;
    }

    public List<String> getAsientos() {
        return asientos;
    }
}
