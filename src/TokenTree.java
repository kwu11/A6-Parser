public class TokenTree {
    static TokenNode root;

    public TokenTree() {
    }

    public void insertNode(TokenNode node) {
        if (root == null) {
            root = node;
            return;
        }


    }

    public void print(TokenNode tn) {
        if (tn == null) return;

        tn.printNode();
        System.out.print("\n\t");
        for (TokenNode t : tn.getChildren()) {
            print(t);
        }
    }

    public static void main(String[] args) {
        TokenTree tree = new TokenTree();

        tree.root = new TokenNode( new Token(999, "Pgm") );
        String production = "kwdprog Vargroup Fcndefs Main";
        for (String word : production.split(" "))
            root.addChild(new Token(-75, word));

        tree.print(root);
    }
}
