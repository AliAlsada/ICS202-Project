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
        //change the is word to false
        TrieNode down = getNode(s);
        if(down != null && down.isWord)
            down.isWord = false;

        TrieNode up;
        for (int i = 0; i < s.length(); i++){
            //tmp nodes
            down = getNode(s.substring(0, s.length() - i));
            if (i <= 2)
                up = getNode(s.substring(0, s.length() - i - 1));
            else
                up = root;

            //if the node is a word, stop
            if (down.isWord)
                return;
            //if node has children, stop
            for (int j = 0; j < 26; j++){
                if (down.children[j] != null)
                    return;
            }

            //delete the node
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
        count = 0;
    }

    public ArrayList<String> allWordsPrefix(String p){

        ArrayList<String> words = new ArrayList<>();

        //last node of the prefix
        TrieNode current = getNode(p);

        //if it has children, iterate through all children of the last node
        if(current.children != null) {
            AllWordsPrefixRecursively(p, current, words);
        }
        //if not, and the prefix is a word, insert it to the list
        else {
            if(current.isWord)
                words.add(p.toLowerCase());
        }

        return words;
    }


    public void AllWordsPrefixRecursively(String prefix, TrieNode node, ArrayList<String> words) {

        if(node.isWord)
            words.add(prefix.toUpperCase()); //insert the word to the list

        //base case
        else if(node.children == null)
            return;

        //go to the children to find all the prefix words.
        for (int i = 0; i < 26; i++)
            if (node.children[i] != null) {
                char c = node.children[i].el;
                AllWordsPrefixRecursively(prefix + c, node.children[c - 'a'], words);
            }
    }

    public boolean isPrefix(String prefix){
        return getNode(prefix) != null;
    }

    public int size(){
        return count;
    }




}
