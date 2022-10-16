import java.util.ArrayList;

class CSMACD implements EstadoCanal {

    public static void test(){


        ArrayList<Estacion> estaciones = Estacion.estaciones;
        Hilo.estadoCanal = LIBRE;
        for(int i=1; i<5; i++){
            Estacion estacion = new Estacion();
            estaciones.add(estacion);
            int destino = i+1;
            if((destino)==5){
                destino=1;
            }
            for(int j=0; j<4; j++){
                String t=  new Util().generarTramaRandomConTamanioRandom();
                estacion.construirTrama(t,destino);
            }
        }



    }

    public static void main(String args[]) {

        Hilo.estadoCanal = LIBRE;
        ArrayList<Estacion> estaciones = Estacion.estaciones;
        int nroEstaciones=4;
        Hilo hilos[] = new Hilo[nroEstaciones+1];
        test();
        /*
        int nroEstaciones=0;
        while(nroEstaciones<2)
            nroEstaciones = Integer.parseInt(JOptionPane.showInputDialog("Ingrese el numero de estaciones: "));

        int arregloTramas[] = new int[nroEstaciones+1];

        for(int i=0; i<nroEstaciones; i++){
            estaciones.add(new Estacion());
        }

        for(int i = 0;i<nroEstaciones;i++) {
            Estacion estacion = estaciones.get(i);
            int nroEstacionOrigen=estacion.getNroEstacion();
            String valor="";
            while (!valor.equals("s") && !valor.equals("n") ) {
                valor = JOptionPane.showInputDialog("Estacion "+nroEstacionOrigen+": ¿Desea enviar tramas?(s/n)");
            }
            if(valor.equals("s")){

                int nroEstacionDestino=0;
                while (nroEstacionDestino<=0 || nroEstacionDestino>nroEstaciones || nroEstacionDestino==nroEstacionOrigen) {
                    nroEstacionDestino = Integer.parseInt(JOptionPane.showInputDialog("Estacion "+nroEstacionOrigen+": Ingrese el nro de la estacion destino"));
                }
                arregloTramas[i] = Integer.parseInt(JOptionPane.showInputDialog("Estacion "+nroEstacionOrigen+": Ingrese el numero de tramas para enviar a la Estacion "+nroEstacionDestino+": "));
                for(int j=1; j<=arregloTramas[i];j++){
                    valor="";
                    while (!valor.equals("s") && !valor.equals("n") ) {
                        valor = JOptionPane.showInputDialog("Estacion "+nroEstacionOrigen+": ¿Desea escribir los datos manualmente de la trama "+j+"?(s/n)");
                    }
                    String t;
                    if(valor.equals("n")){
                        t = Util.generarTramaRandomConTamanioRandom();
                    }else{
                        t = JOptionPane.showInputDialog("Estacion "+nroEstacionOrigen+": Ingrese la trama "+j);
                    }
                    estacion.construirTrama(t, nroEstacionDestino);
                    System.out.println("Trama "+j+" de la Estacion "+nroEstacionOrigen+": "+t);
                }
            }
        }
        */
        for(int i = 0;i<nroEstaciones;i++)
            hilos[i] = new Hilo(estaciones.get(i));

        try {
        // Espera que las estaciones completen la transmisiones
           for(int i=0;i<nroEstaciones;i++)
                hilos[i].hilo.join();
           }
        catch (InterruptedException e) {
            System.out.println("Hilo Principal ha sido interrumpido");
        }
        System.out.println("Transmision Completada");

    }



}
