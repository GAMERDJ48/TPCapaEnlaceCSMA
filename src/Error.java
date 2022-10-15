public class Error {
    //Recibe una trama (solo los datos) como entrada y cambia bits aleatoreamente
    public static boolean error;
    public static int[] generarError(int[] arreglo){
        //Genero la cantidad de errores
        int cantErrores = generarCantidadErroresRealista();

        for(int i=0; i<cantErrores;i++){
            //stat guarda la posicion donde voy a cambiar el bit (error), genero un nro aleatorio y luego lo guardo en stat
            int stat = Util.generarAleatorio(0, arreglo.length);
            //Si el bit es 0 lo cambio a 1, si es 1 lo cambio a 0
            if(arreglo[stat]==0){
                arreglo[stat]=1;
            }else{
                arreglo[stat]=0;
            }
        }
        return arreglo;
    }

    //Generador de cantidad de errores basado en probabilidades que considera la posibilidad de que se generen cero errores, es usada en la funcion GenerarError
    private static int generarCantidadErroresRealista(){
        int a = (int)(Math.random()*100); // enteros generados aleatoriamente de [0,100), la probabilidad de que aparezca cada número es 1%// número de resultado requerido
        int b; //Cantidad de errores
        if (a <50) {// Intervalo de los primeros 50 números, que representa una probabilidad del 50%
            b= 0;
            error=false;
        } else {
            error=true;
            if (a <75) {// [50,75), un rango de 25 números, que representa una probabilidad del 25%
                b = 1;
            } else {
                if(a<90) {// [75,90), un intervalo de 15 dígitos, que representa una probabilidad del 15%
                    b = 2;
                }else{// [90,100), un intervalo de 10 dígitos, que representa una probabilidad del 10%
                    b=3;
                }
            }
        }
        return b;
    }

    //Siempre genera una cantidad igual o superior a 1 de errores
    private static int generarCantidadErrores(){
        int a = (int)(Math.random()*100);
        int b;
        error=true;
        if (a <50) {
            b= 1;
        } else {
            if (a <75) {
                b = 2;
            } else {
                if(a<90) {
                    b = 3;
                }else{
                    b=4;
                }
            }
        }
        return b;
    }

    public static void evaluarRendimientoCRC(){
        int[] datos;
        int[] crc;

        int largo=100;
        float totalErrores=0;
        float repeticiones=100;
        for(int j=0; j<repeticiones; j++){
            contadorErroresCRC =0;
            for(int i=0; i<largo; i++){
                datos = Util.convertirStringEnArreglo(Util.generarTramaRandomConTamanioRandom());
                crc = Error.aplicarCRC(datos);
                crc = Error.generarError(crc);
                Error.verificarCRC(crc);
            }
            totalErrores+=contadorErroresCRC;
        }
        System.out.println("Promedio de errores: "+(totalErrores/repeticiones));
    }

    private static int[] divisor = {1,0,1};
    public static int[] aplicarCRC(int[] datos) {
        // Divide la entrada de datos por el divisor y lo guarda en el arreglo resto
        determinarPolinomioGenerador(datos);
        int resto[] = divide(datos);

        return Util.concatenarArreglos(datos, resto);
    }

    public static void determinarPolinomioGenerador(int[] datos){
        int grado=0;
        for(int i=0; i<datos.length; i++){
            if(datos[i]==1){
                grado= datos.length-i-1;
                break;
            }
        }
        divisor = Util.convertirStringEnArreglo(Util.generarTramaRandomConTamanioFijo(grado));
    }
    // metodo para obtener el crc
    static int[] divide(int datosOriginales[]) {
        int resto[] = new int[divisor.length];
        int datos[] = new int[datosOriginales.length + divisor.length];

        // usamos el metodo arraycopy() de system para copiar los datosOriginales dentro de resto (solo se copian los primeros bits) y datos
        System.arraycopy(datosOriginales, 0, datos, 0, datosOriginales.length);
        System.arraycopy(datos, 0, resto, 0, divisor.length);

        // Iteramos datosOriginales y aplicamos la xor
        for( int i = 0; i < datosOriginales.length; i++) {
            if(resto[0] == 1) {
                // Realizamos la XOR de resto con divisor
                for(int j = 1; j < divisor.length; j++) {
                    resto[j-1] = operacionXOR(resto[j], divisor[j]);
                }
            }
            else {
                // Realizamos la XOR de resto con 0
                for(int j = 1; j < divisor.length; j++) {
                    resto[j-1] = operacionXOR(resto[j], 0);
                }
            }
            // El ultimo bit de resto sera remplazado por un bit de datos
            // Esto va tomando los bits de los datos y los va almacenando en resto, y luego a resto se le vuelve a aplicar la XOR con el divisor
            resto[divisor.length-1] = datos[i+divisor.length];
        }
        return resto;
    }
    /*Funcion XOR
     0 - 0 --> 0
     1 - 0 --> 1
     0 - 1 --> 1
     1 - 1 --> 0
     */
    private static int operacionXOR(int x, int y) {
        if(x == y) {
            return 0;
        }
        return 1;
    }

    public static int contadorErroresCRC;
    static void verificarCRC(int data[]) {
        //Se realiza la division con el divisor
        int resto[] = divide(data);

        for(int i = 0; i < resto.length; i++) {
            if(resto[i] != 0) {
                // Si el resto no equivale a cero, entonces los datos han sido corrompidos
                if(!error){
                    System.out.println("El CRC detecto un error pero este no existe");
                }
                //System.out.println("\nDatos corruptos, pidiendo retransmision...");
                return;
            }
        }
        if(error){
            contadorErroresCRC++;
        }
        //Si para el for sin entrar al if significa que los datos han sido entregados correctamente
    }

}
