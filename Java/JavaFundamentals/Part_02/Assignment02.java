public class Assignment02 {

    public static void main(String[] args) {
        DeclareConstants();
    }

    private static void DeclareConstants() {
        final byte MONTHS_IN_YEAR = 12;         //hoeveelheid maanden in een jaar
        final int AMSTERDAM_POPULACE = 821_752;  //bevolkingsaantal van Amsterdam
        final float DISCOUNT = 33.3f;           //kortingspercentage
        final double PI = 3.141_592_653_589_793;  //x aantal plekken na de komma van pi
        System.out.println("Aantal maanden: " + MONTHS_IN_YEAR);
        System.out.println("Aantal inwoners in 020: " + AMSTERDAM_POPULACE);
        System.out.println("Huidige korting: " + DISCOUNT);
        System.out.println("Pi is: " + PI);
    }
}
