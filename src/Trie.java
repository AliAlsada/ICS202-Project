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

    public boolean search(String word){
        TrieNode node = getNode(word);
        return node != null && node.isWord;
    }

    public void search(TrieNode p){

    }

    public boolean startsWith(String prefix){
        return getNode(prefix) != null;
    }



}
