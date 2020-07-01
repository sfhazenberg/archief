class Course {

    private String name;
    private int numberOfAssignments;
    private boolean fun = false;

    String getName() {
        return name;
    }

    int getNumberOfAssignments() {
        return numberOfAssignments;
    }

    boolean isFun() {
        fun = numberOfAssignments < 35;
        return fun;
    }

    void setName(String name) {
        this.name = name;
    }

    void setNumberOfAssignments(int numberOfAssignments) {
        this.numberOfAssignments = numberOfAssignments;
    }
}
