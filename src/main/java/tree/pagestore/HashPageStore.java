package tree.pagestore;

import tree.Page;
import tree.exceptions.PageNotFoundException;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class HashPageStore implements PageStore {

    Map<String, Page> pageStore = new HashMap<>();

    public Page get(String pageName) throws PageNotFoundException {
        if (pageStore.containsKey(pageName)) {
            return pageStore.get(pageName);
        }
        throw new PageNotFoundException("Unable to find the page by name :" + pageName);
    }

    @Override
    public Page getOrDefault(String pageName, Page page) {
        if(pageStore.containsKey(pageName)){
            return pageStore.get(pageName);
        }

        return page;
    }

    @Override
    public boolean contains(String pageName) {
        return pageStore.containsKey(pageName);
    }

    public void remove(String pageName) {
        pageStore.remove(pageName);
    }

    @Override
    public void put(String pageName, Page page) {
        pageStore.put(pageName,page);
    }
}
