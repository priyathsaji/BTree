package tree;

import java.util.*;

public class Page {

    String name;
    List<PageItem> pageItems = new LinkedList<>();
    boolean isLeafPage;
    int maxSize;


    public Page(String name, int maxSize) {
        this.maxSize = maxSize;
        this.name = name;
        this.isLeafPage = true;
    }

    public Page(String name, int maxSize, List<PageItem> items) {
        this.maxSize = maxSize;
        this.name = name;
        this.isLeafPage = true;
        pageItems.addAll(items);
    }

    boolean isLeaf() {
        return isLeafPage;
    }

    void size() {

    }

    boolean isPageFull() {
        if (pageItems.size() == maxSize) {
            return true;
        }
        return false;
    }

    List<PageItem> getPageItemList() {
        return pageItems;
    }

    public String name() {
        return name;
    }

    public void add(int value) {
        int index = -1;
        for (int i = 0; i < pageItems.size(); i++) {
            if (value < pageItems.get(i).getValue()) {
                break;
            }
            index = i;
        }
        pageItems.add(index + 1, new PageItem(value));
    }

    public List<PageItem> split(int value) {
        add(value);
        int startIndex = pageItems.size() / 2;

        List<PageItem> pageItems2Return = new LinkedList<>();
        for (int i = startIndex; i < pageItems.size(); i++) {
            pageItems2Return.add(pageItems.get(i));
        }
        pageItems.subList(startIndex, pageItems.size()).clear();
        return pageItems2Return;
    }

    public void add(PageItem promotedItem) {
        int index = -1;
        for (int i = 0; i < pageItems.size(); i++) {
            if (promotedItem.getValue() < pageItems.get(i).getValue()) {
                break;
            }
            index = i;
        }
        if (index >= 0) {
            pageItems.get(index).setRight(promotedItem.getLeft());
        }
        if (pageItems.size() > index + 1) {
            pageItems.get(index + 1).setLeft(promotedItem.getRight());
        }
        pageItems.add(index + 1, promotedItem);
    }

    public List<PageItem> split(PageItem promotedItem) {
        add(promotedItem);
        int startIndex = pageItems.size() / 2;
        List<PageItem> pageItems2Return = new LinkedList<>();
        for (int i = startIndex; i < pageItems.size(); i++) {
            pageItems2Return.add(pageItems.get(i));
        }
        pageItems.subList(startIndex, pageItems.size()).clear();
        return pageItems2Return;
    }

    public void makeParentNode() {
        this.isLeafPage = false;
    }


}
