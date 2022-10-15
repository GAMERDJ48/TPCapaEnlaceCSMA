import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.atomic.*;

class Hilo implements Runnable, EstadoCanal {
    String nombreEstacion;
    Thread hilo;
    static int distancia, nroEstacionActual =0, trama;
    static int estadoCanal; //Indica si el canal esta en uso
    int nroTrama, nroMaxTramas;
    private ArrayList<String> tramas;
    private AtomicBoolean transmisionExitosa;
    static final int constanteTiempo =50; //Constante usada para el tiempo de contencion
    private int nroIntento;


    Hilo(String nombreHilo, ArrayList<String> tramas) {
        nombreEstacion = nombreHilo;
        //Creo el hilo y le asigno el nombre de la Estacion
        hilo = new Thread(this, nombreEstacion);
        this.tramas = tramas;
        nroTrama = 1;
        this.nroMaxTramas =tramas.size();
        //Atomic Boolean es lo mismo que Boolean, la diferencia radica en que suele ser mas util el Atomic para hilos ya que cambia de valor sin afectar los procesos de los hilos
        transmisionExitosa = new AtomicBoolean();
        //Inicia el funcionamiento del hilo, aqui pasan dos cosas: el hilo empieza a ejecutar el codigo de la funcion "run()" mientras que el hilo principal (el del main) sigue ejecutando lineas de codigo de la funcion MAIN
        hilo.start();
    }


    //Cuando se ejecuta el comando "start" se manda a llamar esta funcion
    public void run() {
        //Para generar nros aleatorios
        Random rand = new Random();
        while (!transmisionExitosa.get()) {
            nroIntento++;
            while(nroTrama <= nroMaxTramas) {
                if (nroIntento < 16) { //16 es el numero maximo de intentos
                    try {
                        if (estadoCanal == ENUSO) {
                            System.out.println("Canal en uso: "+nombreEstacion + "esta en espera....");
                            try {
                                Thread.sleep(rand.nextInt(50)+1000);
                            }
                            catch (InterruptedException e) {
                                System.out.println(("Interrupcion"));
                            }
                        }
                        else {
                            System.out.println(nombreEstacion + " esta intentando transmitir la trama " + nroTrama);

                            if (estadoCanal == LIBRE && distancia == 0) {//Se empieza ha transmitir
                                System.out.println("Transmitiendo trama "+nroTrama+" de "+nombreEstacion+"...." );
                                nroEstacionActual = getNroEstacion(nombreEstacion);
                                trama = this.nroTrama;
                                estadoCanal = ENUSO;//Seteo el canal en uso
                                for (; distancia < 9000000; distancia++)
                                    for(int i =0;i<1000;i++); //Simula el tiempo de transmision


                                System.out.println(nombreEstacion + ": ha transmitido la trama " + nroTrama + " exitosamente");

                                transmisionExitosa.set(true);
                                nroTrama++;
                                distancia = 0; //Reseteo la distancia para la siguente transmision
                                estadoCanal = LIBRE;
                            }
                            else {//Ocurre una colision
                                System.out.println("Colision para la trama " + nroTrama + " de la " +
                                        nombreEstacion + " con la trama " + trama + " de la Estacion " + nroEstacionActual);

                                System.out.println("Retransmitiendo trama"+trama+" de la Estacion "+nroEstacionActual+".....");
                                transmisionExitosa.set(false);
                                estadoCanal = LIBRE;

                                nroIntento++;

                                try {
                                    //Mando a dormir al hilo en base al retroceso exponencial binario
                                    int R = rand.nextInt((int) (Math.pow(2, nroIntento - 1)));
                                    int BackOffTime = R * constanteTiempo;
                                    Thread.sleep(BackOffTime);

                                } catch (InterruptedException e) {
                                    System.out.println("Interrumpido");
                                }
                            }
                            Thread.sleep(1000);
                        }
                    } catch (InterruptedException e) {
                        System.out.println(nombreEstacion + "Interrupcion Principal");
                    }
                }
                else {
                    transmisionExitosa.set(true);
                    System.out.println("Demasiados intentos para transmitir la trama " + nroTrama + "de la " +
                            nombreEstacion + ". La transmision se cancelo");
                }

            }
        }
    }
    //Obtiene el nro de la estacion como entero eliminado la cadena "Estacion", ejemplo= entrada: Estation 1 ---> salida: 1
    public static int getNroEstacion(String estacion){
        return Integer.parseInt(estacion.charAt(estacion.length()-1)+"");
    }
}
