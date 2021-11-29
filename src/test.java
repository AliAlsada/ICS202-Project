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
            System.out.println("Enter a number (1-7)> ");
            choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    trie = new Trie();
                    System.out.println("Empty trie created");
                    break;

                case 2:
                    System.out.print("Enter your list of letters> ");
                    String[] letters = input.nextLine().split("");

                    //create a trie and insert the word in it
                    trie = new Trie();
                    for (String letter : letters)
                        if (!(letter.equals(" ")))
                            trie.insert(letter.toLowerCase());

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
                        System.out.println("Create a trie first");
                        break;
                    }
                    System.out.println("Write a word to insert> ");
                    String userWord = input2.nextLine();
                    trie.insert(userWord);
                    System.out.println("inserted");
                    break;

            }
        }while (choice != 7);
    }

    public static void readDic(String filename, String letters, Trie trie) throws FileNotFoundException {
        //count the occurrence of the letters
        int []avail = new int[26];
        for(char c : letters.toCharArray()){
            int index = c - 'a';
            avail[index]++;
        }

        //read dictionary
        Scanner scanner = new Scanner(new File(filename));
        while(scanner.hasNext()){
            //read the words line by line
            String dicWord = scanner.nextLine().toLowerCase();

            //count the occurrence of a character the dictionary word
            int[] count = new int[26];
            boolean validWord = true;

            //loop through each character and count the occurrence
            for(char c : dicWord.toCharArray()){
                //the index of the character in the array
                int index = c - 'a';
                count[index]++;
                //check if the dictionary word is substring of the word (letters)
                if(count[index] > avail[index]){
                    validWord = false;
                    break;
                }
            }
            //if it is a valid word, insert it to the trie
            if (validWord){
                trie.insert(dicWord);
            }
        }
        System.out.println("Trie with initial letters created.");
    }
}