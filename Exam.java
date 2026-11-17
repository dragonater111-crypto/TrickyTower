import java.util.Scanner;

public class Exam {

    private static final double PASSING_PERCENT = 80.0;

    private static final String[] QUESTIONS = {
        "1. What is the derivative of x^2?",
        "2. What is the derivative of sin(x)?",
        "3. What is the integral of 2x dx? (omit +C)",
        "4. What is the limit of sin(x)/x as x approaches 0?",
        "5. What is the derivative of e^x?",
        "6. What is the derivative of ln(x)?",
        "7. What is the integral of cos(x) dx? (omit +C)",
        "8. What is the derivative of x^3?",
        "9. What is the integral of x dx from 0 to 1?",
        "10. What is the derivative of 5x?"
    };

    // Each question can have several accepted answers
    private static final String[][] ANSWERS = {
        { "2x" },
        { "cos(x)", "cosx" },
        { "x^2" },
        { "1" },
        { "e^x", "exp(x)" },
        { "1/x", "x^-1" },
        { "sin(x)", "sinx" },
        { "3x^2" },
        { "1/2", "0.5", ".5" },
        { "5" }
    };

    // Strips spaces, asterisks, and case so "2 * x" matches "2x"
    private static String normalize(String s) {
        return s.toLowerCase().replaceAll("[\\s*]", "");
    }

    private static boolean isCorrect(String response, String[] accepted) {
        String cleaned = normalize(response);
        for (String answer : accepted) {
            if (cleaned.equals(normalize(answer))) {
                return true;
            }
        }
        return false;
    }

    public static boolean hasPassed(int correct, int total) {
        return (correct * 100.0 / total) >= PASSING_PERCENT;
    }

    public static boolean runExam() {
        Scanner scanner = new Scanner(System.in);
        int correct = 0;

        for (int i = 0; i < QUESTIONS.length; i++) {
            System.out.println(QUESTIONS[i]);
            System.out.print("> ");
            String response = scanner.nextLine();
            if (isCorrect(response, ANSWERS[i])) {
                correct++;
            }
        }


        System.out.printf("%nScore: %d/%d (%.1f%%)%n",
                correct, QUESTIONS.length, correct * 100.0 / QUESTIONS.length);

        return hasPassed(correct, QUESTIONS.length);
    }

    public static void main(String[] args) {
        boolean passed = runExam();
        String passedString;
        if(passed) passedString = "You have passed the exam";
        else passedString = "You failed the EXAM you can try again later";
        System.out.println(passedString);
    }
}