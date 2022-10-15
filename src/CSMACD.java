import javax.swing.*;
import java.util.ArrayList;

class CSMACD implements EstadoCanal {

    public static void main(String args[])
    {
        Error.crc();
        System.out.println();

    }



    /*
    public static void main(String args[]) {
        String dataStr = "";
        for(int i=0; i<10; i++){
            dataStr+=Util.generarAleatorio(0, 2);
        }

        Hilo.estadoCanal = LIBRE;
        int nroEstaciones=0;
        while(nroEstaciones<2)
            nroEstaciones = Integer.parseInt(JOptionPane.showInputDialog("Ingrese el numero de estaciones: "));

        Hilo hilos[] = new Hilo[nroEstaciones+1];
        int arregloTramas[] = new int[nroEstaciones+1];
        ArrayList<ArrayList<String>> tramas = new ArrayList<>();
        ArrayList<String> aux;
        for(int i = 1;i<=nroEstaciones;i++) {
            arregloTramas[i] = Integer.parseInt(JOptionPane.showInputDialog("Ingrese el numero de tramas para la estacion "+i+": "));
            aux = new ArrayList<>();
            for(int j=1; j<=arregloTramas[i];j++){
                aux.add(JOptionPane.showInputDialog("Ingrese la trama "+j+" de la Estacion "+i+": "));
            }
            tramas.add(aux);
        }

        for(int i=0; i<tramas.size(); i++){
            for(int j=0; j<tramas.get(i).size(); j++){

            }
        }

        for(int i = 1;i<=nroEstaciones;i++)
            hilos[i] = new Hilo("Estacion "+ Integer.toString(i), tramas.get(i));

        try {
        // Espera que las estaciones completen la transmisiones
           for(int i=1;i<=nroEstaciones;i++)
                hilos[i].hilo.join();
           }
        catch (InterruptedException e) {
            System.out.println("Hilo Principal ha sido interrumpido");
        }
        System.out.println("Transmision Completada");

    }
    */

}
