public class Assignment15 {

    public static void main(String[] args) {
        boolean checker;
        char[] aChars = {'u', 'R', 'a' , 'B', 'O', 'L', 'D', 'o', 'n', 'e'};
        for (char c : aChars) {
            checker = isVowel(c);
            if (checker) {
                System.out.println("The letter " + Character.toLowerCase(c) + " is a vowel");
            } else {
                System.out.println("The letter " + Character.toLowerCase(c) + " is a consonant");
            }
        }
    }

    private static boolean isVowel(char c) {
        boolean isVowel = false;
        char[] vowels = {'a', 'e', 'i', 'o', 'u'};
        for (char vowel : vowels) {
            if (c == vowel) {
                isVowel = true;     //then it's a vowel!
                break;
            }
        }
        return isVowel;
    }
}
