package primerparcialprog2;

public class ColectivoTuristico extends Vehiculo {
    private int cantidadPisos;

    public ColectivoTuristico(String patente, String marca, int capacidadPasajeros, int anioFabricacion, int cantidadPisos) {
        super(patente, marca, capacidadPasajeros, anioFabricacion);
        this.cantidadPisos = cantidadPisos;
    }

    public int getCantidadPisos() {
        return cantidadPisos;
    }

    public void setCantidadPisos(int cantidadPisos) {
        this.cantidadPisos = cantidadPisos;
    }

    @Override
    public void realizarServicioTuristico() {
        System.out.println("El colectivo (Patente: " + getPatente() + ") con su(s) " + cantidadPisos + " piso(s) esta realizando un city tour por CABA.");
    }

    @Override
    public String toString() {
        return "Tipo: [Colectivo Turistico] | " + super.toString() + " | Pisos: " + cantidadPisos;
    }
}
