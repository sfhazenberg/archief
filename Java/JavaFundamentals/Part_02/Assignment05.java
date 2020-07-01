public class Assignment05 {

    public static void main(String[] args) {
        //InitialFunction();
        SecondFunction();
    }

    private static void InitialFunction() {
        String firstName = "James";
        String lastName = "Dean";
        int age = 24;
        System.out.println(String.format("The American actor %s %s was %d years when he died", firstName, lastName, age));
    }

    private static void SecondFunction() {
        double dblTemperature = 28.53;
        String strTemperature = String.valueOf(dblTemperature);
        String strHumidity = "54.65";
        float fltHumidity = Float.parseFloat(strHumidity);
        System.out.println("Double temperature is " + dblTemperature);
        System.out.println("String temperature is " + strTemperature);
        System.out.println("String humidity is " + strHumidity);
        System.out.println("Float humidity is " + fltHumidity);
    }
}
