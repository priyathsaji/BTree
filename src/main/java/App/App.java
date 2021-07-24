package App;

import tree.BTree;
import tree.Tree;
import tree.exceptions.PageNotFoundException;

public class App {

    public static void main(String args[]) throws PageNotFoundException {
        Tree tree = new BTree(2);

        for(int i=0;i<10;i++){
            tree.insert(i);
        }

    }
}
