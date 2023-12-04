public class Main {
    public static void main(String[] args) {
        //se declara una variable con el numero a separar
        double numeroE = 2.7182818284;

        //se separa primero la parte entera mediante un casteo a tipo int
        int entero = (int) numeroE;

        //se realiza la diferencia entre la variable entera y el numero incial
        float decimal = (float)(numeroE - entero);

        //se printea por terminal los resultados
        System.out.println("Número E: " + numeroE);
        System.out.println("Parte Entera: " + entero);
        System.out.println("Parte Decimal: " + decimal);
    }
}