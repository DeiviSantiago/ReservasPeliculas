import java.util.ArrayList;
import java.util.List;

public class Pelicula {
    private String titulo;
    private String genero;
    private List<String> horarios = new ArrayList<>();

    public Pelicula(String titulo, String genero) {
        this.titulo = titulo;
        this.genero = genero;
    }

    public void agregarHorario(String horario) {
        horarios.add(horario);
    }

    public List<String> getHorarios() {
        return horarios;
    }

    public String getTitulo() {
        return titulo;
    }

    @Override
    public String toString() {
        return titulo + " (" + genero + ")";
    }
}
