package App;

import tree.BTree;
import tree.Tree;
import tree.exceptions.PageNotFoundException;

public class App {

    public static void main(String args[]) throws PageNotFoundException {
        Tree tree = new BTree(3);

        int n = 11;
        for (int i = 0; i < n; i++) {
            tree.insert(i);
            tree.insert(n - (i + 1));
        }

        tree.print();

    }
}
