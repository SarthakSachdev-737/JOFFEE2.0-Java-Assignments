import java.util.*;

class Document {
    private StringBuffer text;

    public Document(String inputText) {
        this.text = new StringBuffer(inputText);
    }

    public int getLength() {
        return text.length();
    }

    public void removeUnwantedWords(String unwantedWord) {
        String cleanedText = text.toString().replaceAll("\\b" + unwantedWord + "\\b", "").replaceAll("\\s+", " ").trim();
        text = new StringBuffer(cleanedText);
    }

    public String[] splitIntoWords() {
        return text.toString().split("\\s+");
    }

    public void insertWord(int position, String word) {
        text.insert(position, word + " ");
    }

    public void deleteWord(String word) {
        removeUnwantedWords(word);
    }

    public void toLowerCase() {
        text = new StringBuffer(text.toString().toLowerCase());
    }

    public boolean isSimilar(Document other) {
        return text.toString().equalsIgnoreCase(other.text.toString());
    }

    public String getText() {
        return text.toString();
    }

    public int getWordCount() {
        return splitIntoWords().length;
    }
}

public class DocumentProcessor {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter your document: ");
        String inputText = scanner.nextLine();
        Document document = new Document(inputText);

        System.out.println("Original document length: " + document.getLength());

        System.out.print("Enter unwanted word to remove: ");
        String unwantedWord = scanner.nextLine();
        document.removeUnwantedWords(unwantedWord);

        System.out.println("Cleaned document: " + document.getText());
        System.out.println("Word count after cleanup: " + document.getWordCount());

        document.toLowerCase();
        System.out.println("Modified document in lowercase: " + document.getText());

        scanner.close();
    }
}
