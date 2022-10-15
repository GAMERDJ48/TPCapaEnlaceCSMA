import java.util.ArrayList;
//Clase que contiene metodos que son utiles para todas las clases
public class Util {
    //Genera nro aleatorio especificando los limites
    public static int generarAleatorio(int desde, int hasta){
        return (int)(Math.random()*hasta+desde);
    }

    public static void imprimirArreglo(int[] arreglo){
        for(int i = 0; i < arreglo.length; i++) {
            System.out.print(arreglo[i]);
        }
        System.out.println();
    }
    public static int [] concatenarArreglos(int[] arreglo1, int[] arreglo2){
        int largo = arreglo1.length + arreglo2.length;
        int [] result = new int[largo];
        for(int i=0; i<arreglo1.length; i++){
            result[i]=arreglo1[i];
        }
        for(int i=arreglo1.length; i<largo; i++){
            result[i]=arreglo2[i-arreglo1.length];
        }
        return result;
    }
    public static String generarTramaRandomConTamanioRandom(){
        return generarTramaRandomConTamanioFijo(Util.generarAleatorio(4, 15));
    }

    public static String generarTramaRandomConTamanioFijo(int largo){
        String dataStr = "1";
        for(int i=1; i<largo; i++){
            dataStr+=Util.generarAleatorio(0, 2);
        }
        return dataStr;
    }

    public static ArrayList<Integer> convertirArregloEnArrayList(int[] arreglo){
        ArrayList<Integer> result = new ArrayList<>();
        for(int i=0; i<arreglo.length; i++){
            result.add(arreglo[i]);
        }
        return result;
    }

    public static int[] convertirArrayListEnArreglo(ArrayList<Integer> arreglo){
        int[] result = new int[arreglo.size()];
        for(int i=0; i<arreglo.size(); i++){
            result[i]=arreglo.get(i);
        }
        return result;
    }

    public static int[] convertirStringEnArreglo(String cadena){
        int arreglo[] = new int[cadena.length()];
        char [] values;
        values = cadena.toCharArray();
        int cantdatos=values.length;

        for(int i=0; i<cantdatos; i++){
            String auh=Character.toString(values[i]);
            int aux = Integer.parseInt(auh);
            arreglo[i]=aux;
        }
        return arreglo;
    }


    public static ArrayList<Integer> convertirStringEnArrayList(String cadena){
        ArrayList<Integer> arreglo = new ArrayList<>();
        for(int i=0; i<cadena.length(); i++){
            arreglo.add(Integer.parseInt(cadena.charAt(i)+""));
        }
        return arreglo;
    }


    public static String convertirArregloEnString(int[] arreglo){
        String cadena = "";
        for(int i=0; i<arreglo.length; i++){
            cadena+=arreglo[i];
        }
        return cadena;
    }

    public static String convertirArrayListEnString(ArrayList<Integer> arreglo){
        String cadena = "";
        for(int i=0; i<arreglo.size(); i++){
            cadena+=arreglo.get(i);
        }
        return cadena;
    }
}
