public class TrieNode {

    protected char el;
    protected TrieNode[] children;

    public TrieNode(char el){
        this.el = el;
        children = new TrieNode[26];
    }
}
