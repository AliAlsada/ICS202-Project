import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class test {

    public static void main(String[] args) throws FileNotFoundException {

        Scanner input = new Scanner(System.in);
        Scanner input2 = new Scanner(System.in);


        Scanner scanner = new Scanner(System.in);
        System.out.println("TRIE PROJECT: Enter your choice? ");
        System.out.println("1) Create an empty trie");
        System.out.println("2) Create a trie with initial letters ");
        System.out.println("3) Insert a word ");
        System.out.println("4) Delete a word ");
        System.out.println("5) List all words that begin with a prefix ");
        System.out.println("6) Size of the trie ");
        System.out.println("7) End ");

        //promote the user to enter the choice
        int choice;
        Trie trie = null;
        do {
            System.out.print("Enter a number (1-7)> ");
            choice = scanner.nextInt();
            System.out.print("\n");
            switch (choice) {

                case 1:
                    trie = new Trie();
                    System.out.println("Empty trie created\n");
                    break;


                case 2:
                    System.out.print("Enter your list of letters> ");
                    String[] letters = input.nextLine().split("");

                    //create a trie and insert the word in it
                    trie = new Trie();

                    //make it a string
                    String word = "";
                    for (String i : letters) {
                        if (!(i.equals(" ")))
                            word = word + i;
                    }
                    readDic("dictionary.txt", word.toLowerCase(), trie);
                    break;


                case 3:
                    if (trie == null) {
                        System.out.println("Create a trie first\n");
                        break;
                    }
                    System.out.print("Write a word to insert> ");
                    String userWord = input2.nextLine().toLowerCase();
                    trie.insert(userWord);
                    System.out.println("inserted\n");
                    System.out.println("===============================================");
                    break;


                case 4:
                    if (trie == null) {
                        System.out.println("Create a trie first\n");
                        break;
                    }
                    System.out.print("Enter a word to delete> ");
                    String deleteWord = input2.nextLine().toLowerCase();
                    if (trie.contains(deleteWord)){
                        trie.delete(deleteWord);
                        System.out.println("The word has deleted successfully\n");
                        System.out.println("===============================================");
                        break;
                    }
                    System.out.println("This word is not in the trie");
                    System.out.println("===============================================");
                    break;

                case 5:

                    if (trie == null) {
                        System.out.println("Create a trie first\n");
                        break;
                    }
                    System.out.print("Enter a prefix> ");
                    String preFix = input2.nextLine().toLowerCase();
                    if (!(trie.isPrefix(preFix)))
                        break;
                    System.out.println("Found the following words: " + trie.allWordsPrefix(preFix) + "\n");
                    System.out.println("===============================================");
                    break;


                case 6:
                    if (trie == null){
                        System.out.println("Create a trie first");
                        break;
                    }
                    System.out.println("The size of the trie is: " + trie.size() + "\n");
                    System.out.println("===============================================");
                    break;

            }
        }while (choice != 7);
    }

    public static void readDic(String filename, String letters, Trie trie) throws FileNotFoundException {
        //count the occurrence of the letters
        int []countLetters = new int[26];
        for(char c : letters.toCharArray()){
            int index = c - 'a';
            countLetters[index]++;
        }

        //read dictionary
        Scanner scanner = new Scanner(new File(filename));
        while(scanner.hasNext()){
            //read the words line by line
            String dicWord = scanner.nextLine().toLowerCase();

            //count the occurrence of a character the dictionary word
            int[] countDicWord = new int[26];
            boolean valid = true;

            //loop through each character and count the occurrence
            for(char c : dicWord.toCharArray()){
                //the index of the character in the array
                int index = c - 'a';
                countDicWord[index]++;
                //check if the dictionary word is substring of the word (letters)
                if(countDicWord[index] > countLetters[index]){
                    valid = false;
                    break;
                }
            }
            //if it is a valid word, insert it to the trie
            if (valid){
                trie.insert(dicWord);
            }
        }
        System.out.println("Trie with initial letters created.\n");
        System.out.println("===============================================");
    }
}