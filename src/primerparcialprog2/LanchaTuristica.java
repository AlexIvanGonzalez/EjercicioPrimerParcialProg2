package primerparcialprog2;

public class LanchaTuristica extends Vehiculo {
    private String tipoMotor;

    public LanchaTuristica(String patente, String marca, int capacidadPasajeros, int anioFabricacion, String tipoMotor) {
        super(patente, marca, capacidadPasajeros, anioFabricacion);
        this.tipoMotor = tipoMotor;
    }

    public String getTipoMotor() {
        return tipoMotor;
    }

    public void setTipoMotor(String tipoMotor) {
        this.tipoMotor = tipoMotor;
    }

    @Override
    public void realizarServicioTuristico() {
        System.out.println("La lancha (Patente: " + getPatente() + ") con su motor " + tipoMotor + " esta navegando por la costanera.");
    }

    @Override
    public String toString() {
        return "Tipo: [Lancha Turistica] | " + super.toString() + " | Tipo de Motor: " + tipoMotor;
    }
}
