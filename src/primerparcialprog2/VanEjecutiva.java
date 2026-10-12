package primerparcialprog2;

public class VanEjecutiva extends Vehiculo {
    private boolean aireAcondicionado;

    public VanEjecutiva(String patente, String marca, int capacidadPasajeros, int anioFabricacion, boolean aireAcondicionado) {
        super(patente, marca, capacidadPasajeros, anioFabricacion);
        this.aireAcondicionado = aireAcondicionado;
    }

    public boolean tieneAireAcondicionado() {
        return aireAcondicionado;
    }

    public void setAireAcondicionado(boolean aireAcondicionado) {
        this.aireAcondicionado = aireAcondicionado;
    }

    @Override
    public void realizarServicioTuristico() {
        String estadoAire;
        
        if (aireAcondicionado) {
            estadoAire = "CON";
        } else {
            estadoAire = "SIN";
        }
        
        System.out.println("La Van ejecutiva (Patente: " + getPatente() + ") esta realizando un traslado VIP hacia casa rosada " + estadoAire + " aire acondicionado.");
    }

    @Override
    public String toString() {
        String estadoAire;
        
        if (aireAcondicionado) {
            estadoAire = "Si";
        } else {
            estadoAire = "No";
        }
        
        return "Tipo: [Van Ejecutiva] | " + super.toString() + " | Aire Acondicionado: " + estadoAire;
    }
}
