import java.util.ArrayList;

public class TokenNode {
    Token thisToken;
    ArrayList<TokenNode> children = new ArrayList<>();

    public TokenNode(Token t) {
        thisToken = t;
    }

    public void addChild(Token t) {
        children.add( new TokenNode(t) );
    }

    public ArrayList<TokenNode> getChildren() {
        return children;
    }

    public int getNumChildren() {
        return children.size();
    }

    public void printNode() {
        System.out.print(thisToken);
    }

    public static void main(String[] args) {
        TokenNode tn = new TokenNode(new StringToken("First Node", 1));
        tn.addChild(new IntToken(5, 1));
        tn.addChild(new IntToken(6, 1));
        tn.addChild(new IntToken(3, 1));

        return;
    }
}
