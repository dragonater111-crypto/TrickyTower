import java.util.Random;
import java.util.Scanner;

/**
 * Represents the Coin Flip trial and training mechanics in Tricky Tower.
 * Odds of winning increase if the player has talked to the Gambler NPC
 * and trained on a previous floor.
 */
public class CoinFlip {
    private final Random random;
    private final Scanner scanner;
    private boolean trialCompleted;

    public CoinFlip() {
        this.random = new Random();
        this.scanner = new Scanner(System.in);
        this.trialCompleted = false;
    }

    /**
     * Triggered when the player talks to the Gambler NPC on a previous floor.
     */
    public String talkToGambler(Player player) {
        if (player.hasTalkedToGambler()) {
            return "The Gambler nods quietly. 'Remember: watch the thumb, not the coin.'";
        }
        player.setTalkedToGambler(true);
        return "You speak with the Gambler. He shows you how the tower's coins are weighted. "
                + "(Coin flip odds increased!)";
    }

    /**
     * Triggered when the player practices coin flipping on a previous floor.
     */
    public String trainCoinFlip(Player player) {
        if (player.hasTrainedCoinFlip()) {
            return "You have already honed your reflexes at the training table.";
        }
        player.setTrainedCoinFlip(true);
        return "You spend time practicing the toss and catching the glint of the coin in the dark. "
                + "(Coin flip odds increased!)";
    }

    /**
     * Starts the interactive Coin Flip trial for the given player.
     */
    public boolean startTrial(Player player) {
        this.trialCompleted = false;
        double winChance = player.getCoinFlipWinChance();

        System.out.println("A spectral figure holds a heavy iron coin before the final staircase.");
        System.out.printf("Current chance of calling the flip correctly: %.0f%%%n", winChance * 100);

        String call = promptForCall();
        while (call == null) {
            System.out.println("Invalid choice. Please enter 1 or 2.");
            call = promptForCall();
        }

        boolean won = evaluateFlip(player, call);
        if (won) {
            this.trialCompleted = true;
            player.setFloor5CoinFlipCleared(true);
            System.out.println("The coin lands on " + call + "! The final trial is complete.");
        } else {
            String opposite = call.equals("Heads") ? "Tails" : "Heads";
            System.out.println("The coin lands on " + opposite + ". You lost the toss.");
            System.out.println("The tower resets your progress back to Floor 1...");
            player.resetProgress();
        }
        return won;
    }

    /**
     * Prompts the user to call Heads or Tails.
     */
    private String promptForCall() {
        System.out.println();
        System.out.println("Call the coin flip:");
        System.out.println("1 = Heads");
        System.out.println("2 = Tails");
        System.out.print("Enter choice (1-2): ");

        String input = scanner.nextLine().trim();
        switch (input) {
            case "1": return "Heads";
            case "2": return "Tails";
            default: return null;
        }
    }

    /**
     * Evaluates a single coin flip using the player's current modified odds.
     */
    public boolean evaluateFlip(Player player, String playerCall) {
        if (playerCall == null || (!playerCall.equalsIgnoreCase("Heads")
                && !playerCall.equalsIgnoreCase("Tails"))) {
            return false;
        }
        double roll = random.nextDouble(); // 0.0 to 1.0
        return roll < player.getCoinFlipWinChance();
    }

    public boolean isTrialCompleted() {
        return trialCompleted;
    }

    public static void main(String[] args) {
        Player player = new Player("Goblin");
        CoinFlip coinFlip = new CoinFlip();

        // Simulate talking to the NPC and training on an earlier floor
        System.out.println(coinFlip.talkToGambler(player));
        System.out.println(coinFlip.trainCoinFlip(player));
        System.out.println();

        // Run the trial
        coinFlip.startTrial(player);
    }
}