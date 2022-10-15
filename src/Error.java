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


    public static void crc(){
        int [] r = {1,0,1,0,1,1,1};
        ArrayList<Integer> trama = Util.convertirArregloEnArrayList(r);
        int [] a = {1,0,1};
        ArrayList<Integer> generador = Util.convertirArregloEnArrayList(a);
        int[] b= {0,0,0};
        ArrayList<Integer> resto = Util.convertirArregloEnArrayList(b);
        ArrayList<Integer> crc;


        resto = divide(trama, generador, resto);

        /*
        String lom="011101111";
        trama=Util.convertirStringEnArreglo(lom);

        String rami="1010";
        generador = Util.convertirStringEnArreglo(rami);

        largo=lom.length()+generador.length-1;

        resto=new int[largo];
        crc=new int[largo];


        for(int i=0;i<trama.length;i++){
            resto[i] = trama[i];
        }

        //resto=divide(trama, generador, resto);


        for(int i=0;i<trama.length;i++)
        {
            crc[i]=(trama[i]^resto[i]);
        }

        //resto=divide(crc, generador, resto);

        for(int i=0; i< resto.length; i++)
        {
            if(resto[i]!=0)
            {
                System.out.println("Error su resto no es cero");
                break;
            }
            if(i==resto.length-1)
                System.out.println("No hay error su resto es igual  a 0");
        }

         */
    }

    //usado en el crc
    static ArrayList<Integer> divide(ArrayList<Integer> trama, ArrayList<Integer> divisor,ArrayList<Integer> resto)
    {
        int contador=1;
        ArrayList<Integer> diviendo = new ArrayList<>();
        ArrayList<Integer> aux;
        for(int i=0; i<divisor.size(); i++){
            diviendo.add(trama.get(i));
        }

        while(true){
            int result=0;
            aux = new ArrayList<>();
            boolean flag=false;
            for(int i=0;i<divisor.size();i++) {
                result = xor(diviendo.get(i), divisor.get(i));
                if ((result == 1 && !flag)) {
                    flag = true;
                }
                if(flag){
                    aux.add(result);
                }
            }
            if(aux.size()!=divisor.size()){
                int dif = divisor.size()-aux.size();
                for(int i=0; i<dif; i++){
                    if(divisor.size()*contador>trama.size()) return diviendo;
                    aux.add(trama.get((divisor.size()+i)*contador));
                }
                contador++;
            }
            diviendo.clear();
            diviendo.addAll(aux);

        }
    }
    static int xor(int a, int b){
        if(a==b){
            return 0;
        }else{
            return 1;
        }
    }

}
