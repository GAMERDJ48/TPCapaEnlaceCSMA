import java.util.ArrayList;

public class Estacion implements EstadoEstacion{
    public static ArrayList<Estacion> estaciones;
    private int nroEstacion;
    private ArrayList<Trama> tramas;
    private ArrayList<String> payloads;
    private int estadoEstacion;

    static{
        estaciones=new ArrayList<>();
    }
    public Estacion(){
        nroEstacion=estaciones.size()+1;
        estadoEstacion = INACTIVO;
        tramas = new ArrayList<>();
        payloads = new ArrayList<>();
    }
    public void enviarTrama(int indice){
        Trama trama = tramas.get(indice);
        String datos = payloads.get(indice);
        int[] data =  new GenerarYDetectarError().aplicarCRC(new Util().convertirStringEnArreglo(datos));
        data = Entramado.entramar(data);
        trama.setPayload(data);
    }

    public void construirTrama(String payload, int destino){
        Trama trama = new Trama(nroEstacion, destino);
        payloads.add(payload);
        tramas.add(trama);
    }

    public boolean recibirTrama(Trama trama){
        GenerarYDetectarError error = new GenerarYDetectarError();
        int estacion = trama.getDestino();
        int[] payload = Entramado.desentramar(trama.getPayload());
        String datos = error.recuperarPayload(payload);
        return error.verificarCRC(payload);
    }

    public static Estacion buscarEstacion(int nroEstacion){
        if(estaciones!=null){
            for(Estacion estacion: estaciones){
                if(estacion.getNroEstacion()==nroEstacion){
                    return estacion;
                }
            }
        }
        System.out.println("No hay estaciones");
        return null;
    }

    public int getNroEstacion() {
        return nroEstacion;
    }

    public void setNroEstacion(int nroEstacion) {
        this.nroEstacion = nroEstacion;
    }

    public ArrayList<Trama> getTramas() {
        return tramas;
    }

    public void setTramas(ArrayList<Trama> tramas) {
        this.tramas = tramas;
    }

    public int getEstadoEstacion() {
        return estadoEstacion;
    }

    public void setEstadoEstacion(int estadoEstacion) {
        this.estadoEstacion = estadoEstacion;
    }
}
