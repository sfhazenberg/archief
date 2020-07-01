public class Assignment24 {

    public static void main(String[] args) {
        Course Tmap = new Course();
        Tmap.setName("Tmap");
        Tmap.setNumberOfAssignments(35);
        System.out.println(String.format(
                "The course %s has %d assignments and is %s.",
                Tmap.getName(),
                Tmap.getNumberOfAssignments(),
                (Tmap.isFun() ? "fun" : "not fun")
        ));

        Course leanGreenBelt = new Course();
        leanGreenBelt.setName("Lean Green Belt");
        leanGreenBelt.setNumberOfAssignments(13);
        System.out.println(String.format(
                "The course %s has %d assignments and is %s.",
                leanGreenBelt.getName(),
                leanGreenBelt.getNumberOfAssignments(),
                (leanGreenBelt.isFun() ? "fun" : "not fun")
        ));
    }
}
