import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Library {
	private List<String> bookList;
	private Map<String, String> bookContent;

	public Library() {
		// Books currently on the shelf
		bookList = new ArrayList<>();
		bookList.add("Book1");
		bookList.add("Book2");
		bookList.add("Book3");
		bookList.add("Book4");

		// Map of book name -> book text
		bookContent = new HashMap<>();
		bookContent.put("Book1", "text1");
		bookContent.put("Book2", "text2");
		bookContent.put("Book3", "text3");
		bookContent.put("Book4", "text4");
	}

	// Returns list of all books avaliable on the shelf.
	public String[] viewBooks() {
		return bookList.toArray(new String[0]);
	}

	// Select a book from the shelf and read it.
	// Removes the book from shelf the player is holding it.
	// Returns the books content, or not found if not avaliable.
	public String readBook(String bookName) {
		if (bookList.contains(bookName)) {
			bookList.remove(bookName);
			String content = bookContent.get(bookName);
			if (content != null) {
				return content;
			} else {
				return "text1";
			}
		} else {
			if (bookContent.get(bookName) != null) {
				return "You're already holding that book.";
			} else {
				return "That book is not on the shelf.";
			}
		}
	}

	// Puts book back on shelf.
	public String returnBook(String bookName) {
		String content = bookContent.get(bookName);
		if (content != null && !bookList.contains(bookName)) {
			bookList.add(bookName);
			return "You put " + bookName + " back on the shelf.";
		} else {
			return "That book can't be returned.";
		}
	}

	// Peek at a books content.
	// Meaning you can read it without taking it off the shelf.
	public String peekReadBook(String bookName) {
		if (bookList.contains(bookName)) {
			String content = bookContent.get(bookName);
			if (content != null) {
				return content;
			} else {
				return "text1";
			}
		} else {
			return "That book is not on the shelf.";
		}
	}

	// Shows shelf menu and handles the players choice.
	public static void main(String[] args) {
		Library library = new Library();
		Scanner scanner = new Scanner(System.in);
		boolean running = true;

		System.out.println("You arrived at the library.");

		while (running) {
			displayShelf(library);
			displayMenu();
			String choice = scanner.nextLine().trim();

			switch (choice) {
				case "1":
					pickUpAndReadBook(library, scanner);
					break;
				case "2":
					peekAtBook(library, scanner);
					break;
				case "3":
					putBookBack(library, scanner);
					break;
				case "4":
					running = false;
					System.out.println("You are leaving the Library");
					break;
				default:
					System.out.println("Invalid choice, pleas enter 1,2,3 or 4.");
			}
		}
		scanner.close();
	}

	// Displays all books on the shelf.
	private static void displayShelf(Library library) {
		String[] shelf = library.viewBooks();
		System.out.println();
		if (shelf.length == 0) {
			System.out.println("The shelf is empty.");
		} else {
			System.out.println("Books on the shelf: ");
			for (int i = 0; i < shelf.length; i++) {
				System.out.print(shelf[i]);
				if (i < shelf.length - 1) {
					System.out.print(", ");
				}
			}
			System.out.println();
		}
	}

	//Prints menu options.
	private static void displayMenu() {
		System.out.println();
		System.out.println("What would you like to do?");
		System.out.println("1 = Pick up and read a book.");
		System.out.println("2 = Peek at a book (read and put it back when done.");
		System.out.println("3 = Put a book back on the shelf.");
		System.out.println("4 = Quit.");
		System.out.print("Enter choice: ");
	}

	// Prompt for book name, picks it up, and reads it.
	private static void pickUpAndReadBook(Library library, Scanner scanner) {
		System.out.print("Enter the name of the book to pick up and read: ");
		String bookName = scanner.nextLine().trim();
		System.out.println();
		System.out.println("-> " + library.readBook(bookName));
	}

	// Prompt for a book name and peeks at it. (Read and put it back when done).
	private static void peekAtBook(Library library, Scanner scanner) {
		System.out.print("Enter the name of the book to peek at: ");
		String bookName = scanner.nextLine().trim();
		System.out.println();
		System.out.println("-> " + library.peekReadBook(bookName));
	}

	// Prompts for a book name and puts it back on the shelf.
	private static void putBookBack(Library library, Scanner scanner) {
		System.out.print("Enter the name of the book to put back: ");
		String bookName = scanner.nextLine().trim();
		System.out.println();
		System.out.println("-> " + library.returnBook(bookName));
	}
}