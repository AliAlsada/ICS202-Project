import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class test {

    public static void main(String[] args) throws FileNotFoundException {

        Scanner input = new Scanner(System.in);

        //promote the user to enter the list of letters or a word
        System.out.print("Enter your list of letters> ");
        String[] letters = input.nextLine().split("");
        Arrays.sort(letters);

        //create a trie and insert the word in it
        Trie trie = new Trie();
        for (String letter : letters)
            if (!(letter.equals(" ")))
                trie.insert(letter.toLowerCase());

        //make it a string
        String word = "";
        for (String i : letters){
            if (!(i.equals(" ")))
                word = word + i;
        }

        //insert the permutations in the trie
        readDic("dictionary.txt", word.toLowerCase(), trie);


        System.out.println(trie.search("stop"));


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
    }
}