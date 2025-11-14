import java.util.ArrayList;
import java.util.List;

public class Sala {
    private String nombre;
    private int capacidad;
    private List<String> asientos = new ArrayList<>();

    public Sala(String nombre, int capacidad) {
        this.nombre = nombre;
        this.capacidad = capacidad;
        // Generar asientos A1, A2, ..., E10
        char fila = 'A';
        for (int i = 0; i < (capacidad / 10); i++) {  // filas
            for (int j = 1; j <= 10; j++) {  // asientos por fila
                asientos.add(fila + String.valueOf(j));
            }
            fila++;
        }
    }

    public List<String> getAsientos() {
        return asientos;
    }

    public String getNombre() {
        return nombre;
    }
}
