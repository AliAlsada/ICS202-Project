import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Test {

    public static void main(String[] args) throws FileNotFoundException {

        Scanner input = new Scanner(System.in);

        //promote the user to enter the list of letters or a word
        System.out.print("Enter your list of letters> ");
        String[] letters = input.nextLine().split("");
        Arrays.sort(letters);

        //combine the letters in a word
        String word = "";
        for (int i = 0; i < letters.length; i++){
            if (!(letters[i].equals(" ")))
                word = word + letters[i];
        }

        //create a trie and insert the word in it
        Trie trie = new Trie();
        trie.initialInsert(word.toLowerCase());

        //insert the permutations in the trie
        readDic("dictionary.txt", word, trie);


    }

    public static void readDic(String filename, String str, Trie trie) throws FileNotFoundException {
        Scanner scanner = new Scanner(new File(filename));
        while(scanner.hasNext()){
            //read the word from the dictionary
            String dicWord = scanner.nextLine().toLowerCase();

            //split the word, to sort it
            String[] s = dicWord.split("");
            Arrays.sort(s);
            String sortedWord = String.join("", s).toLowerCase();

            //check if the word from the dictionary is permutation to the letters entered by the user
            if(str.equalsIgnoreCase(sortedWord))
                trie.insert(dicWord);  //insert to the trie

        }
    }

}
