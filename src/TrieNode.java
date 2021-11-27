public class TrieNode {

    protected char el;
    protected boolean isWord;
    protected TrieNode[] children;

    public TrieNode(char el){
        this.el = el;
        isWord = false;
        children = new TrieNode[26];
    }
}
