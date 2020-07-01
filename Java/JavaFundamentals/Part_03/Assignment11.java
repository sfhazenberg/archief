public class Assignment11 {

    public static void main(String[] args) {
        StringArray();
    }

    private static void StringArray() {
        String[] strArray = {"Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday"};
        for(String day : strArray) {    //heb wederom foreach-loop gebruikt. Kort, simpel en doet wat ie moet doen.
            System.out.println(day);
        }
    }
}
