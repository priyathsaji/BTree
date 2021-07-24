package tree;

import tree.exceptions.PageNotFoundException;

public interface Tree {

    void insert(int value) throws PageNotFoundException;

    void delete(int value);

    void print() throws PageNotFoundException;

    void contains();

    void nearest();

}
