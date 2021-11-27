import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Test {

    public static void main(String[] args) throws FileNotFoundException {

        Scanner scanner = new Scanner(new File("dictionary.txt"));
        Scanner input = new Scanner(System.in);

        //promote the user to enter the list of letters or a word
        System.out.print("Enter your list of letters> ");
        String[] letters = input.nextLine().split("");
        Arrays.sort(letters);

        String str = String.join("", letters).toLowerCase();

        Trie trie = new Trie();
        trie.initialInsert(str);

        while(scanner.hasNext()){
            String s1 = scanner.nextLine().toLowerCase();

            String[] s = s1.split("");
            Arrays.sort(s);
            String word = String.join("", s).toLowerCase();

            if(str.equalsIgnoreCase(word))
                trie.insert(s1);
        }


    }
    public static List<String> permutations(String s) {

        List<String>combinations=new ArrayList<String>();
        if(s.length()==1){
            combinations.add(s);
        }
        else{
            for(int i=0;i<s.length();i++){
                List<String>temp=permutations(s.substring(0, i)+s.substring(i+1));
                for (String string : temp) {
                    combinations.add(s.charAt(i)+string);
                }
            }
        }
        return combinations;
    }

}
