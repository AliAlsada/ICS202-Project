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

    public void initialInsert(String s){
        TrieNode p = root;
        for (int i = 0; i < s.length(); i++){
            char c  = s.charAt(i);
            if (p.children[c - 'a'] == null)
                p.children[c - 'a'] = new TrieNode(c);
        }
    }



}
