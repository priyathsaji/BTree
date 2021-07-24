package tree.pagestore;

import tree.Page;
import tree.exceptions.PageNotFoundException;

import java.util.Optional;

public interface PageStore {
    Page get(String pageName);

    Page getOrDefault(String pageName, Page page);

    boolean contains(String pageName);

    void remove(String name);

    void put(String pageName,Page page);

}
