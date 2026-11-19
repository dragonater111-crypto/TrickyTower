import java.util.Scanner;

public class GameLoreQuiz {

    // lore questions for the quiz
    private String[] questions = {
        "1. What surrounds the world outside of the tower?",
        "2. How many great beasts terrorize this world?",
        "3. How many witches are there?",
        "4. What does each witch represent?",
        "5. What kind of creature used to live in this tower?",
        "6. What was the name of the one who used to live in this tower?",
        "7. Who was the last person to clear these trials?"
    };

    // correct answers for the quiz
    private String[] answers = {
        "waterfall",
        "3",
        "7",
        "cardinal sin",
        "scorpion",
        "shaula",
        "subaru natsuki"
    };

    private int score;

    public GameLoreQuiz() {
        score = 0;
    }

    // checks if the answer is correct (ignores caps and extra spaces)
    public boolean checkAnswer(int questionNumber, String playerAnswer) {
        String cleanAnswer = playerAnswer.trim().toLowerCase();
        return cleanAnswer.equals(answers[questionNumber]);
    }

    // runs the quiz and returns true if passed, false if failed
    public boolean runQuiz() {
        Scanner scanner = new Scanner(System.in);
        score = 0;

        System.out.println("Welcome to the Game Lore Quiz!");
        System.out.println("Answer every question correctly to pass.");
        System.out.println();

        for (int i = 0; i < questions.length; i++) {
            System.out.println(questions[i]);
            System.out.print("> ");
            String playerAnswer = scanner.nextLine();

            if (checkAnswer(i, playerAnswer)) {
                System.out.println("Correct!");
                score++;
            } else {
                System.out.println("Wrong!");
            }
            System.out.println();
        }

        System.out.println("Score: " + score + "/" + questions.length);
        return hasPassed();
    }

    // the player only passes if they get every question right
    public boolean hasPassed() {
        return score == questions.length;
    }

    public int getScore() {
        return score;
    }

    public static void main(String[] args) {
        GameLoreQuiz quiz = new GameLoreQuiz();
        boolean passed = quiz.runQuiz();

        if (passed) {
            System.out.println("You have passed the Game Lore Quiz!");
        } else {
            System.out.println("You failed the Game Lore Quiz. Read the Lore Book and try again.");
        }
    }
}
