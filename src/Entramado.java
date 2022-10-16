import java.util.ArrayList;

public class Entramado {
    private static final int flag = 5; //Indico la cantidad de unos que voy a usar de bandera

    //Se utiliza el METODO del relleno de bits: se agregan 5 unos al principio y al final para delimitar la trama y si hay 5 unos dentro
    //de la trama se le agrega un 0 luego de los 5 unos

    //Toma como entrada la trama y le agrega el entramado utilizado
    public static int[] entramar(int[] t){
        Util util = new Util();
        ArrayList<Integer> trama = util.convertirArregloEnArrayList(t);
        int contador=0; //Cuenta la cantidad de unos que se detectan
        for(int i=0; i<trama.size(); i++){
            if(contador<flag){
                if(trama.get(i)==1){
                    contador++;
                }else{
                    contador=0;
                }
            }else{//Contador es igual a 5
                //Se agrega un 0 luego de los 5 unos
                trama.add(i, 0);
                contador=0;
            }
        }

        //Agrega los 5 unos que delimitan la trama tanto al final como al principio de esta.
        for(int i=0; i<flag; i++){
            trama.add(1);
            trama.add(0, 1);
        }
        return util.convertirArrayListEnArreglo(trama);
    }

    //Cuando el receptor recibe la trama debe obtener la trama original, sin entramar
    public static int[] desentramar(int[] t){
        Util util = new Util();
        ArrayList<Integer> trama = util.convertirArregloEnArrayList(t);
        //Quita los unos que estan al principio y al final de la trama
        for(int i=0; i<flag; i++){
            trama.remove(trama.size()-1);
            trama.remove(0);
        }

        //Realiza el proceso inverso que el entramado, en vez de agregar ceros, los quita
        int contador=0;
        for(int i=0; i<trama.size(); i++){
            if(contador<flag){
                if(trama.get(i)==1){
                    contador++;
                }else{
                    contador=0;
                }
            }else{
                trama.remove(i);
                i-=1;
                contador=0;
            }
        }


        return util.convertirArrayListEnArreglo(trama);
    }



}
