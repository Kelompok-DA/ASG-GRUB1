import java.util.Scanner;
import java.util.Stack;
import java.util.HashMap;
import java.util.Map;

public class Main {
    private static Stack<Integer> undoStack = new Stack<>();
    private static Stack<Integer> redoStack = new Stack<>();
    private static Map<Integer, String> history = new HashMap<>();

    private static int stateId = 0;

    private static String currentText = "";
    private static String currentWord = "";

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        long startTime = System.nanoTime();

        history.put(stateId, currentText);

        System.out.println("Teks Editor (penerapan undo dan redo)");
        System.out.println("Perintah: :undo | :redo | :exit");

        while (true) {
            tampilkanPreview();

            System.out.print("Input: ");
            String input = scanner.nextLine();

            if (input.equals(":exit")) break;

            if (input.equals(":undo")) {
                undo();
            } else if (input.equals(":redo")) {
                redo();
            } else {
                prosesInput(input);
            }
        }

        long endTime = System.nanoTime();
        System.out.println("\nExecution Time (ns): " + (endTime - startTime));
        scanner.close();
    }

    private static void prosesInput(String input) {
        for (char c : input.toCharArray()) {
            if (c == ' ') {
                commitWord();
            } else {
                currentWord += c;
            }
        }
        commitWord();
    }

    private static void commitWord() {
        if (currentWord.isEmpty()) return;

        undoStack.push(stateId);
        redoStack.clear();

        stateId++;
        currentText += currentWord + " ";
        history.put(stateId, currentText);

        currentWord = "";
    }

    private static void undo() {
        if (!currentWord.isEmpty()) {
            currentWord = currentWord.substring(0, currentWord.length() - 1);
            return;
        }

        if (undoStack.isEmpty()) return;

        redoStack.push(stateId);
        stateId = undoStack.pop();
        currentText = history.get(stateId);
    }

    private static void redo() {
        if (redoStack.isEmpty()) return;

        undoStack.push(stateId);
        stateId = redoStack.pop();
        currentText = history.get(stateId);
    }

    private static void tampilkanPreview() {
        String displayText = currentText + currentWord;

        System.out.println("\nTeks saat ini: \"" + displayText + "\"");

        if (!undoStack.isEmpty())
            System.out.println("Undo: \"" + history.get(undoStack.peek()) + "\"");
        else
            System.out.println("Undo: \"-\"");

        if (!redoStack.isEmpty())
            System.out.println("Redo: \"" + history.get(redoStack.peek()) + "\"");
        else
            System.out.println("Redo: \"-\"");
    }
}

