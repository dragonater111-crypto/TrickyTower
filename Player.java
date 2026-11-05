import java.util.ArrayList;
import java.util.List;

/**
 * Tracks player progression, floor location, trial completion flags,
 * and coin-flip odds modifiers for Tricky Tower.
 */
public class Player {
    private String name;
    private int currentFloor;

    // Trial progression flags (Floors 2 through 5)
    private boolean floor2MazeCleared;
    private boolean floor3TrialCleared;
    private boolean floor4TrialCleared;
    private boolean floor5CoinFlipCleared;

    // Coin flip modifier flags
    private boolean talkedToGambler;
    private boolean trainedCoinFlip;
    private boolean readCoinFlipBook;

    // Tower rule & Guardian flags
    private boolean learnedRule5;
    private boolean guardianSummoned;

    // Tracks books or items currently held by the player
    private List<String> inventory;

    public Player(String name) {
        this.name = name;
        this.currentFloor = 1; // Starts on Floor 1: Library & Base Camp
        this.floor2MazeCleared = false;
        this.floor3TrialCleared = false;
        this.floor4TrialCleared = false;
        this.floor5CoinFlipCleared = false;
        this.talkedToGambler = false;
        this.trainedCoinFlip = false;
        this.readCoinFlipBook = false;
        this.learnedRule5 = false;
        this.guardianSummoned = false;
        this.inventory = new ArrayList<>();
    }

    /**
     * Calculates the player's probability of winning a coin flip (0.50 to 0.90).
     */
    public double getCoinFlipWinChance() {
        double odds = 0.50; // Base 50% coin flip
        if (talkedToGambler) {
            odds += 0.15;   // +15% for talking to the NPC
        }
        if (trainedCoinFlip) {
            odds += 0.15;   // +15% for training on a previous floor
        }
        if (readCoinFlipBook) {
            odds += 0.10;   // +10% for reading the coin flip book in the Library
        }
        return Math.min(odds, 0.95);
    }

    /**
     * Checks if the trial on the specified floor is completed.
     */
    public boolean isFloorCleared(int floor) {
        switch (floor) {
            case 1: return true; // Floor 1 is the Library hub
            case 2: return floor2MazeCleared;
            case 3: return floor3TrialCleared;
            case 4: return floor4TrialCleared;
            case 5: return floor5CoinFlipCleared;
            default: return false;
        }
    }

    /**
     * Checks if all four trials (Floors 2-5) are complete so the player can safely leave.
     */
    public boolean hasCompletedAllTrials() {
        return floor2MazeCleared
                && floor3TrialCleared
                && floor4TrialCleared
                && floor5CoinFlipCleared;
    }

    /**
     * Moves the player up one floor if the current floor's trial is complete.
     */
    public String moveUp() {
        if (currentFloor >= 5) {
            return "You are already on the top floor of the tower.";
        }
        if (!isFloorCleared(currentFloor)) {
            return "The staircase to Floor " + (currentFloor + 1)
                    + " is locked until you complete this floor's trial.";
        }
        currentFloor++;
        return "You ascend the staircase to Floor " + currentFloor + ".";
    }

    /**
     * Moves the player down one floor.
     */
    public String moveDown() {
        if (currentFloor <= 1) {
            return "You are already on Floor 1 (Library & Base Camp).";
        }
        currentFloor--;
        return "You descend the staircase to Floor " + currentFloor + ".";
    }

    /**
     * Resets player progress (e.g., if the final coin flip is lost).
     */
    public void resetProgress() {
        this.currentFloor = 1;
        this.floor2MazeCleared = false;
        this.floor3TrialCleared = false;
        this.floor4TrialCleared = false;
        this.floor5CoinFlipCleared = false;
        this.talkedToGambler = false;
        this.trainedCoinFlip = false;
        this.readCoinFlipBook = false;
        this.learnedRule5 = false;
        this.guardianSummoned = false;
        this.inventory.clear();
    }

    // --- Getters and Setters ---

    public String getName() {
        return name;
    }

    public int getCurrentFloor() {
        return currentFloor;
    }

    public void setCurrentFloor(int currentFloor) {
        this.currentFloor = currentFloor;
    }

    public boolean isFloor2MazeCleared() {
        return floor2MazeCleared;
    }

    public void setFloor2MazeCleared(boolean floor2MazeCleared) {
        this.floor2MazeCleared = floor2MazeCleared;
    }

    public boolean isFloor3TrialCleared() {
        return floor3TrialCleared;
    }

    public void setFloor3TrialCleared(boolean floor3TrialCleared) {
        this.floor3TrialCleared = floor3TrialCleared;
    }

    public boolean isFloor4TrialCleared() {
        return floor4TrialCleared;
    }

    public void setFloor4TrialCleared(boolean floor4TrialCleared) {
        this.floor4TrialCleared = floor4TrialCleared;
    }

    public boolean isFloor5CoinFlipCleared() {
        return floor5CoinFlipCleared;
    }

    public void setFloor5CoinFlipCleared(boolean floor5CoinFlipCleared) {
        this.floor5CoinFlipCleared = floor5CoinFlipCleared;
    }

    public boolean hasTalkedToGambler() {
        return talkedToGambler;
    }

    public void setTalkedToGambler(boolean talkedToGambler) {
        this.talkedToGambler = talkedToGambler;
    }

    public boolean hasTrainedCoinFlip() {
        return trainedCoinFlip;
    }

    public void setTrainedCoinFlip(boolean trainedCoinFlip) {
        this.trainedCoinFlip = trainedCoinFlip;
    }

    public boolean hasReadCoinFlipBook() {
        return readCoinFlipBook;
    }

    public void setReadCoinFlipBook(boolean readCoinFlipBook) {
        this.readCoinFlipBook = readCoinFlipBook;
    }

    public boolean hasLearnedRule5() {
        return learnedRule5;
    }

    public void setLearnedRule5(boolean learnedRule5) {
        this.learnedRule5 = learnedRule5;
    }

    public boolean isGuardianSummoned() {
        return guardianSummoned;
    }

    public void setGuardianSummoned(boolean guardianSummoned) {
        this.guardianSummoned = guardianSummoned;
    }

    public List<String> getInventory() {
        return inventory;
    }
}