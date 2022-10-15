import java.util.ArrayList;

public class Error {

    //Recibe una trama (solo los datos) como entrada y cambia bits aleatoreamente
    public ArrayList<Integer> generarError(ArrayList<Integer> arreglo){
        //Genero la cantidad de errores
        int cantErrores = generarCantidadErrores();

        for(int i=0; i<cantErrores;i++){
            //stat guarda la posicion donde voy a cambiar el bit (error), genero un nro aleatorio y luego lo guardo en stat
            int stat = Util.generarAleatorio(0, arreglo.size());
            //Si el bit es 0 lo cambio a 1, si es 1 lo cambio a 0
            if(arreglo.get(stat)==0){
                arreglo.set(stat, 1);
            }else{
                arreglo.set(stat, 0);
            }
        }
        return arreglo;
    }

    //Generador de cantidad de errores basado en probabilidades, es usada en la funcion GenerarError
    private int generarCantidadErrores(){
        int a = (int)(Math.random()*100); // enteros generados aleatoriamente de [0,100), la probabilidad de que aparezca cada número es 1%// número de resultado requerido
        int b; //Cantidad de errores
        if (a <50) {// Intervalo de los primeros 50 números, que representa una probabilidad del 50%
            b= 0;
        } else {
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


    public static void crc(int[] datos) {
        int[] divisor = {1,0,1};
        // Divide the input datos by the input divisor and store the result in the resto array
        int resto[] = divideDataWithDivisor(datos, divisor);
        // iterate resto using for loop to print each bit
        for(int i = 0; i < resto.length-1; i++) {
            System.out.print(resto[i]);
        }
        System.out.println("\nGenerated CRC code is: ");

        for(int i = 0; i < datos.length; i++) {
            System.out.print(datos[i]);
        }
        for(int i = 0; i < resto.length-1; i++) {
            System.out.print(resto[i]);
        }
    }
    // create divideDataWithDivisor() method to get CRC
    static int[] divideDataWithDivisor(int oldData[], int divisor[]) {
        // declare resto[] array
        int resto[] = new int[divisor.length];
        int i;
        int data[] = new int[oldData.length + divisor.length];
        // use system's arraycopy() method for copying data into resto and data arrays
        System.arraycopy(oldData, 0, data, 0, oldData.length);
        System.arraycopy(data, 0, resto, 0, divisor.length);
        // iterate the oldData and exor the bits of the remainder and the divisor
        for(i = 0; i < oldData.length; i++) {
            System.out.println((i+1) + ".) First data bit is : "+ resto[0]);
            System.out.print("Remainder : ");
            if(resto[0] == 1) {
                // We have to exor the remainder bits with divisor bits
                for(int j = 1; j < divisor.length; j++) {
                    resto[j-1] = exorOperation(resto[j], divisor[j]);
                    System.out.print(resto[j-1]);
                }
            }
            else {
                // We have to exor the remainder bits with 0
                for(int j = 1; j < divisor.length; j++) {
                    resto[j-1] = exorOperation(resto[j], 0);
                    System.out.print(resto[j-1]);
                }
            }
            // The last bit of the remainder will be taken from the data
            // This is the 'carry' taken from the dividend after every step
            // of division
            resto[divisor.length-1] = data[i+divisor.length];
            System.out.println(resto[divisor.length-1]);
        }
        return resto;
    }
    // create exorOperation() method to perform exor data
    static int exorOperation(int x, int y) {
        // This simple function returns the exor of two bits
        if(x == y) {
            return 0;
        }
        return 1;
    }

}
