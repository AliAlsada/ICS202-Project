import java.util.ArrayList;
import java.util.Locale;

public class Trie {
    protected TrieNode root;
    int count = 0;

    public Trie(){
        root = new TrieNode('\0');
    }

    public void insert(String s){
        TrieNode p = root;
        for (int i = 0; i < s.length(); i++){
            char c  = s.charAt(i);
            if (p.children[c - 'a'] == null) {
                p.children[c - 'a'] = new TrieNode(c);
                count++;
            }
            p = p.children[c - 'a'];
        } p.isWord = true;
    }

    public void initialInsert(String s){
        TrieNode p = root;
        for (int i = 0; i < s.length(); i++){
            char c  = s.charAt(i);
            if (p.children[c - 'a'] == null) {
                p.children[c - 'a'] = new TrieNode(c);
                count++;
            }
            p = p.children[c - 'a'];
        }
    }


    public TrieNode getNode(String word){
        TrieNode p = root;
        for (int i = 0; i<word.length(); i++) {
            char c = word.charAt(i);
            if (p.children[c - 'a'] == null)
                return null;
            p = p.children[c - 'a'];
        }return p;
    }


    public boolean contains(String word){
        TrieNode node = getNode(word);
        return node != null && node.isWord;
    }

    public void delete(String s){
        TrieNode node = getNode(s);
        if(node != null && node.isWord)
            node.isWord = false;

        TrieNode up;
        for (int i = 0; i < s.length(); i++){

            TrieNode down = getNode(s.substring(0, s.length() - i));

            if (i <= 2)
                up = getNode(s.substring(0, s.length() - i - 1));
            else
                up = root;

            if (down.isWord)
                return;

            for (int j = 0; j < 26; j++){
                if (down.children[j] != null)
                    return;
            }

            up.children[down.el - 'a'] = null;
            count--;
        }

    }



    public boolean isEmpty(){
        for (int i = 0; i < 25; i++)
            if(root.children[i] != null)
                return false;
            return true;
    }

    public void clear(){

        for (int i = 0; i < 25; i++)
            root.children[i] = null;
    }

    public ArrayList<String> allWordsPrefix(String p){
        return allWordsPrefix(root,p);
    }

    public ArrayList<String> allWordsPrefix(TrieNode node, String p){
        ArrayList<String> words = new ArrayList<>();

        TrieNode current = node;
        //go to the last node of the prefix
        for(Character c: p.toCharArray()) {
            TrieNode nextNode = current.children[c - 'a'];
            if(nextNode == null)
                return words;
            current = nextNode; //last node
        }
        //if it has children, iterate through all children of the last node
        if(current.children != null) {
            findAllWordsForPrefixRecursively(p, current, words);
        }
        //if not, and the prefix is a word, insert it to the list
        else {
            if(current.isWord)
                words.add(p.toLowerCase());
        }
        return words;
    }

    static void findAllWordsForPrefixRecursively(String prefix, TrieNode node, ArrayList<String> words) {

        if(node.isWord)
            words.add(prefix.toUpperCase()); //insert the word to the list

        //base case
        else if(node.children == null) {
            return;
        }

        //go to the children to find all the prefix words.
        for (int i = 0; i < 26; i++)
            if (node.children[i] != null) {
                char c = node.children[i].el;
                findAllWordsForPrefixRecursively(prefix + c, node.children[c - 'a'], words);
            }

    }

    public boolean isPrefix(String prefix){
        return getNode(prefix) != null;
    }

    public int size(){
        return count;
    }




}
