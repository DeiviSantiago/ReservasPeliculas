import java.util.*;

public class Sistema {
    private static List<Pelicula> peliculas = new ArrayList<>();
    private static List<Sala> salas = new ArrayList<>();
    private static List<Reserva> reservas = new ArrayList<>();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        inicializarDatos();

        System.out.println("==== Bienvenido al sistema de reservas ====");
        System.out.print("Ingrese nombre de usuario: ");
        String nombre = scanner.nextLine();
        System.out.print("Ingrese contraseña: ");
        String pass = scanner.nextLine();
        System.out.print("Ingrese rol (admin/usuario): ");
        String rol = scanner.nextLine();

        UsuarioCreator creator = rol.equalsIgnoreCase("admin") ? new AdministradorCreator() : new UsuarioNormalCreator();
        Usuario usuario = creator.crearUsuario(nombre, pass);

        if (!usuario.autenticar(nombre, pass)) {
            System.out.println("Error de autenticación.");
            return;
        }

        System.out.println("Autenticación exitosa! Bienvenido " + usuario.getRol());

        if (usuario instanceof Administrador) {
            menuAdministrador(scanner);
        } else {
            menuUsuario(scanner, usuario);
        }

        scanner.close();
    }

    private static void inicializarDatos() {
        Sala sala1 = new Sala("Sala 1", 50);
        salas.add(sala1);

        Pelicula pelicula1 = new Pelicula("Avengers", "Acción");
        pelicula1.agregarHorario("18:00");
        pelicula1.agregarHorario("20:00");
        peliculas.add(pelicula1);

        Pelicula pelicula2 = new Pelicula("Matrix", "Ciencia Ficción");
        pelicula2.agregarHorario("19:00");
        peliculas.add(pelicula2);
    }

    private static void menuAdministrador(Scanner scanner) {
        boolean salir = false;
        while (!salir) {
            System.out.println("\n=== Menú Administrador ===");
            System.out.println("1. Ver películas");
            System.out.println("2. Agregar película");
            System.out.println("3. Salir");
            System.out.print("Seleccione opción: ");
            String opcion = scanner.nextLine();

            switch (opcion) {
                case "1":
                    verPeliculas();
                    break;
                case "2":
                    agregarPelicula(scanner);
                    break;
                case "3":
                    salir = true;
                    break;
                default:
                    System.out.println("Opción no válida.");
            }
        }
    }

    private static void verPeliculas() {
        System.out.println("\nPelículas disponibles:");
        for (Pelicula p : peliculas) {
            System.out.println(" - " + p + " Horarios: " + p.getHorarios());
        }
    }

    private static void agregarPelicula(Scanner scanner) {
        System.out.print("Título: ");
        String titulo = scanner.nextLine();
        System.out.print("Género: ");
        String genero = scanner.nextLine();

        Pelicula nueva = new Pelicula(titulo, genero);

        System.out.print("Cantidad horarios a agregar: ");
        int n = Integer.parseInt(scanner.nextLine());

        for (int i = 0; i < n; i++) {
            System.out.print("Horario #" + (i+1) + ": ");
            String horario = scanner.nextLine();
            nueva.agregarHorario(horario);
        }
        peliculas.add(nueva);
        System.out.println("Película agregada exitosamente.");
    }

    private static void menuUsuario(Scanner scanner, Usuario usuario) {
        boolean salir = false;
        while(!salir) {
            System.out.println("\n=== Menú Usuario ===");
            System.out.println("1. Ver películas y horarios");
            System.out.println("2. Reservar asiento");
            System.out.println("3. Ver mis reservas");
            System.out.println("4. Salir");
            System.out.print("Seleccione opción: ");
            String opcion = scanner.nextLine();

            switch(opcion) {
                case "1":
                    verPeliculas();
                    break;
                case "2":
                    reservarAsiento(scanner, usuario);
                    break;
                case "3":
                    verReservas(usuario);
                    break;
                case "4":
                    salir = true;
                    break;
                default:
                    System.out.println("Opción no válida.");
            }
        }
    }

    private static void reservarAsiento(Scanner scanner, Usuario usuario) {
        System.out.println("\nSeleccione una película:");
        for (int i = 0; i < peliculas.size(); i++) {
            System.out.println((i+1) + ". " + peliculas.get(i));
        }
        System.out.print("Película elegida: ");
        int idxPelicula = Integer.parseInt(scanner.nextLine()) - 1;
        if (idxPelicula < 0 || idxPelicula >= peliculas.size()) {
            System.out.println("Película inválida.");
            return;
        }
        Pelicula pelicula = peliculas.get(idxPelicula);

        System.out.println("Horarios disponibles:");
        List<String> horarios = pelicula.getHorarios();
        for (int i = 0; i < horarios.size(); i++) {
            System.out.println((i+1) + ". " + horarios.get(i));
        }
        System.out.print("Horario elegido: ");
        int idxHorario = Integer.parseInt(scanner.nextLine()) - 1;
        if (idxHorario < 0 || idxHorario >= horarios.size()) {
            System.out.println("Horario inválido.");
            return;
        }
        String horario = horarios.get(idxHorario);

        Reserva reserva = new Reserva(usuario, pelicula, horario);
        System.out.println("Ingrese los asientos que desea reservar separados por coma (ejemplo: A1,A2): ");
        String asientosInput = scanner.nextLine();
        String[] asientosArray = asientosInput.split(",");
        for(String asiento : asientosArray) {
            reserva.agregarAsiento(asiento.trim());
        }
        reservas.add(reserva);
        System.out.println("Reserva creada exitosamente.");
    }

    private static void verReservas(Usuario usuario) {
        System.out.println("\nReservas de " + usuario.nombreUsuario + ":");
        boolean encontrado = false;
        for (Reserva r : reservas) {
            if (r.getUsuario().equals(usuario)) {
                encontrado = true;
                System.out.println("Película: " + r.getPelicula() + ", Horario: " + r.getHorario() + ", Asientos: " + r.getAsientos());
            }
        }
        if(!encontrado) {
            System.out.println("No tiene reservas.");
        }
    }
}
