/**
 *  Author: Kyle Wu
 *  This class represents the different types of tokens that are represented in the A4 language.
 *  Reserved words, integers, floats, strings, and identifiers are recognized.
 */

class Token {
    protected int ID;
    protected String Meaning;
    protected int lineNum;

    public Token() {
        ID = 0;
        Meaning = "None";
        lineNum = 0;
    }

    public Token(final int id, final String meaning) {
        ID = id;
        Meaning = meaning;
        lineNum = -1;
    }

    public Token(final int id, final String meaning, final int line) {
        ID = id;
        Meaning = meaning;
        lineNum = line;
    }

    public int getID() { return ID; }
    public String getMeaning() { return Meaning; }
    public int getLine() { return lineNum; }

    public String toString() { return String.format("(Tok: %3d line= %3d str= \"%s\")", ID, lineNum, Meaning); }
}


/**
 *  Integer, Float, and String Tokens
 */
class IntToken extends Token {
    final private int integer;

    public IntToken(final int i, final int line) {
        ID = 3;
        Meaning = "int";
        lineNum = line;
        integer = i;
    }

    @Override
    public String toString() { return String.format("(Tok: %3d line= %3d str = \"%d\" int= %d)", ID, lineNum, integer, integer); }
}

class FloatToken extends Token {
    final private float fl;

    public FloatToken(final float f, final int line) {
        ID = 4;
        Meaning = "float";
        lineNum = line;
        fl = f;
    }

    @Override
    public String toString() { return String.format("(Tok: %3d line= %3d str = \"%.6f\" float = %.6f)", ID, lineNum, fl, fl); }
}

class StringToken extends Token {
    final private String contents;

    public StringToken(final String str, final int line) {
        ID = 5;
        Meaning = "string";
        lineNum = line;
        contents = str;
    }

    @Override
    public String toString() { return String.format("(Tok: %3d line= %3d str= \"%s\")", ID, lineNum, contents); }
}