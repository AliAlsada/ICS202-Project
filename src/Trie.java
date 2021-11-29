public class Trie {
    private TrieNode root;

    public Trie(){
        root = new TrieNode('\0');
    }

    public void insert(String s){
        TrieNode p = root;
        for (int i = 0; i < s.length(); i++){
            char c  = s.charAt(i);
            if (p.children[c - 'a'] == null)
                p.children[c - 'a'] = new TrieNode(c);
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

    public boolean isPrefix(String prefix){
        return getNode(prefix) != null;
    }

    public boolean contains(String word){
        TrieNode node = getNode(word);
        return node != null && node.isWord;
    }

    public void delete(String s){
        TrieNode node = getNode(s);
        if(node != null && node.isWord)
            node.isWord = false;
    }

    public boolean isEmpty(){
        return false;
    }

    public void clear(){

    }
    public void allWordsPrefix(String p){
        //return list
    }

    public int size(){
        return 1;
    }




}
