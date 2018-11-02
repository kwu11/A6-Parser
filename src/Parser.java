import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

import static java.lang.System.exit;



public class Parser {
    Stack<String>
            symbols, // parser stack
            input; // token list input
    HashMap<String,Integer> rulenumbers; // the rule numbers
    ArrayList<String> rules; // the actual rules
    Set<String> language; // set containing all LHSs and terminals in the language

    public Parser() throws FileNotFoundException {
        Scanner scan;
        String filename;

        filename = "tuples_to_rule_numbers.txt";
        scan = new Scanner(new File(filename));
        rulenumbers = new HashMap<>();
        while (scan.hasNextLine()) {
            String[] pair = scan.nextLine().split("\t");

            if (pair.length != 2) {
                System.out.println(filename + "is formatted incorrectly (expected 'SYMBOL,TERMINAL   RULE#')");
                exit(-1);
            }
            rulenumbers.put(pair[0], Integer.parseInt(pair[1]));
        }
        System.out.println("Loaded rule numbers");

        filename = "rules.txt";
        scan = new Scanner(new File(filename));
        rules = new ArrayList<>();
        rules.add("");
        while (scan.hasNextLine())
            rules.add(scan.nextLine());
        System.out.println("Loaded rules");

        filename = "stack symbols.txt";
        scan = new Scanner(new File(filename));
        language = new HashSet<>();
        while (scan.hasNext())
            language.add(scan.next());
        System.out.println("Loaded stack symbols");

        symbols = new Stack<>();
        symbols.push("Pgm");
        System.out.println("Initialized stack");

        //scan = new Scanner(System.in);
//        System.out.print("What do you want?\n  > ");
//        String inp = scan.nextLine();
        String inp = "kwdprog kwdmain brace1 id equal float semi kprint parens1 string parens2 semi id equal float semi id equal int aster id aster id semi kprint parens1 string comma id parens2 semi brace2";
        input = new Stack<>();
        for (String s : reverse(inp))
            input.push(s);
        System.out.println("Loaded input");

        scan.close();
    }

    public void doThing() {
        TokenTree parseTree = new TokenTree();

        System.out.println("Starting parsing");
        // Parsing
        while (!input.empty() && !symbols.empty()) {
            String FRONT = input.peek(),
                    TOP = symbols.peek();

            // Terminals in front and top
            if ( FRONT.equals(TOP) ) {
                input.pop();
                symbols.pop();
//                createNodeOnTree;
            }

            // Top of symbol stack exists in the language
            else if (language.contains(TOP)) {
                String tuple = TOP + "," + FRONT;

                // Mapping of (symbol, input) to a rule number in the grammar
                if (rulenumbers.containsKey(tuple)) {
                    symbols.pop();
                    int ruleNum = rulenumbers.get(tuple);

                    if (ruleNum > rules.size()) {
                        System.out.println("Rule number" + ruleNum + "does not exist");
                        exit(-1);
                    }

                    // Push the rule in reverse onto symbol stack
                    String RHS = rules.get(ruleNum).split(" -> ")[1];

                    for (String s : reverse(RHS)) {
                        if (s.equals("eps"))
                            break;
                        symbols.push(s);
                    }
                }
            }
            else {
                System.out.println("Error parsing");
                exit(-1);
            }
        }
        System.out.println("How are you here?");
    }

    private static String[] reverse(String str) {
        String[] s = str.split(" ");
        int SIZE = s.length;
        String[] reversed = new String[SIZE];
        for (int i = 0; i < SIZE; i++)
            reversed[i] = s[SIZE - i - 1];

        return reversed;
    }

    public static void main(String[] args) {
        try {
            Parser p = new Parser();
            p.doThing();
        }
        catch(FileNotFoundException e) {
            System.out.println(e.getMessage());
        }
        return;
    }
}
