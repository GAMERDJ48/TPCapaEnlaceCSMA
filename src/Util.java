import java.util.ArrayList;
//Clase que contiene metodos que son utiles para todas las clases
public class Util {
    //Genera nro aleatorio especificando los limites
    public static int generarAleatorio(int desde, int hasta){
        return (int)(Math.random()*hasta+desde);
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
