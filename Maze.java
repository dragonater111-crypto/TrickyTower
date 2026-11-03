import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Maze {
	private int movement;
	private boolean reachedExit;

	// Sequence needed to reach exit.
	private final String[] requiredMoves = {"Forward", "Left", "Forward", "Right"};

	// Tracks the moves made so far.
	private List<String> playerMoves;

	private Scanner scanner;

	public Maze() {
		this.movement = 0;
		this.reachedExit = false;
		this.playerMoves = new ArrayList<>();
		this.scanner = new Scanner(System.in);
	}

	// Starts and resets the maze.
	public int mazeStart() {
		this.movement = 0;
		this.reachedExit = false;
		this.playerMoves.clear();

		System.out.println("Reach the exit of by choosing the correct moves.");

		while (!reachedExit) {
			String move = promptForMove();
			if (move == null) {
				System.out.println("Invalid choice. Please try again.");
				continue;
			}
			String result = movement(move);
			System.out.println(result);
		}
		System.out.println("Congratulations! You have escaped.");
		return movement;
	}

	// Prompts the user to pick Forward, Left or Right.
	private String promptForMove() {
		System.out.println();
		System.out.println("Which way do you want to move?");
		System.out.println("1 = Forward");
		System.out.println("2 = Left");
		System.out.println("3 = Right");
		System.out.print("Enter choice (1-3): ");

		String input = scanner.nextLine().trim();

		switch (input) {
			case "1": return "Forward";
			case "2": return "Left";
			case "3": return "Right";
			default: return null;
		}
	}

	public String movement(String move) {
		if (reachedExit) {
			return "You have already reached the exit.";
		}
		playerMoves.add(move);
		int index = playerMoves.size() - 1;

		if (index < requiredMoves.length && move.equals(requiredMoves[index])) {
			if (playerMoves.size() == requiredMoves.length) {
				reachedExit = true;
				return "You reached the exit!";
			}
			return "You move " + move + ", you must keep moving.";
		} else {
			playerMoves.clear();
			return "You hit a wall. The maze has reset.";
		}
	}

	//Getters
	public int getMovement() {
		return movement;
	}
	public boolean isReachedExit() {
		return reachedExit;
	}
	public void setReachedExit(boolean reachedExit) {
		this.reachedExit = reachedExit;
	}

	public static void main(String[] args) {
		Maze maze = new Maze();
		maze.mazeStart();
	}
}