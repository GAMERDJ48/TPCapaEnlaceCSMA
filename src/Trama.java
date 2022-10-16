public class Trama {
    private int origen;
    private int destino;
    private int[] payload;

    public Trama(int origen, int destino) {
        this.origen = origen;
        this.destino = destino;
    }
    public Trama(int origen, int destino, int[] payload) {
        this.origen = origen;
        this.destino = destino;
        this.payload = payload;
    }

    public int getOrigen() {
        return origen;
    }

    public void setOrigen(int origen) {
        this.origen = origen;
    }

    public int getDestino() {
        return destino;
    }

    public void setDestino(int destino) {
        this.destino = destino;
    }

    public int[] getPayload() {
        return payload;
    }

    public void setPayload(int[] payload) {
        this.payload = payload;
    }
}
