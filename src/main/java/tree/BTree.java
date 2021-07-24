package tree;

import tree.exceptions.PageNotFoundException;
import tree.pagestore.HashPageStore;
import tree.pagestore.PageStore;

import java.util.*;

public class BTree implements Tree {
    PageStore pageStore;
    int m;
    Page start;
    int pageNameCounter = 1;

    public BTree(int m) {
        this.pageStore = new HashPageStore();
        this.m = m;
        this.start = null;
    }


    private String generatePageName() {
        return "" + (pageNameCounter++);
    }


    private Page getPage(String pageName) throws PageNotFoundException {
        if (pageName == null) {
            pageName = generatePageName();
        }
        if (pageStore.contains(pageName)) {
            return pageStore.get(pageName);
        } else {

            Page newPage = new Page(pageName, m);
            pageStore.put(pageName, newPage);
            return newPage;
        }
    }

    public Page newPage(List<PageItem> initialItems) {
        String name = generatePageName();
        Page page = new Page(name, m, initialItems);
        pageStore.put(name, page);
        return page;
    }

    public <T> List<T> subList(List<T> list, int start, int end) {
        List<T> data2Return = new ArrayList<>();
        for (int i = start; i < Math.min(end, list.size()); i++) {
            data2Return.add(list.get(i));
        }
        return data2Return;
    }


    public void insert(int value) throws PageNotFoundException {
        if (start == null) {
            start = newPage(Collections.emptyList());
        }

        Page current = start;
        Stack<Page> stack = new Stack<>();

        while (true) {
            if (current.isLeaf()) {
                if (current.isPageFull()) {
                    List<PageItem> items = current.split(value);
                    Page newPage = newPage(subList(items, 1, items.size()));
                    PageItem promotedItem = items.get(0);
                    promotedItem.setRight(newPage.name());
                    promotedItem.setLeft(current.name());

                    boolean isInserted = false;

                    while (!stack.isEmpty()) {
                        Page parent = stack.pop();
                        if (parent.isPageFull()) {
                            items = parent.split(promotedItem);
                            newPage = newPage(subList(items, 1, items.size()));
                            newPage.makeParentNode();
                            promotedItem = items.get(0);
                            promotedItem.setRight(newPage.name());
                            promotedItem.setLeft(parent.name());
                            isInserted = false;
                        } else {
                            parent.add(promotedItem);
                            isInserted = true;
                            break;
                        }
                    }

                    if (!isInserted) {
                        start = newPage(Collections.singletonList(promotedItem));
                        start.makeParentNode();
                    }
                } else {
                    current.add(value);
                }
                break;
            } else {
                stack.push(current);
                PageItem prevItem = null;
                boolean isPageFound = false;
                for (PageItem item : current.getPageItemList()) {
                    if (item.getValue() > value) {
                        break;
                    }
                    prevItem = item;
//                        current = getPage(item.getLeft());
//                        item.setLeft(current.name());
//                        if (prevItem != null) {
//                            prevItem.setRight(current.name());
//                        }
//                        isPageFound = true;
//                    }
//                    prevItem = item;
                }
//                if (!isPageFound) {
                if (prevItem == null) {
                    current = getPage(current.getPageItemList().get(0).getLeft());
                } else {
                    current = getPage(prevItem.getRight());
                }
//                else{
//                    throw new PageNotFoundException("Error while getting required Page");
//                }
//                }
            }
        }


    }

    public void delete(int value) {


    }

    private void print(List<Page> something) throws PageNotFoundException {
        List<Page> page = new ArrayList<>();
        for (Page item : something) {
            if (item == null) {
                System.out.println("   |   ");
                continue;
            }
            for (PageItem pageItem : item.getPageItemList()) {
                System.out.print(pageItem.getValue() + "   ");
                if (!item.isLeaf() && pageStore.contains(pageItem.getLeft())) {
                    page.add(pageStore.get(pageItem.getLeft()));
                }
            }
            if (!item.isLeaf() && pageStore.contains(item.getPageItemList().get(item.getPageItemList().size() - 1).getRight())) {
                page.add(pageStore.get(item.getPageItemList().get(item.getPageItemList().size() - 1).getRight()));
            }

            System.out.print("   |   ");
        }

        if (page.size() != 0) {
            System.out.println("\n");
            print(page);
        }
    }

    public void print() throws PageNotFoundException {
        print(Collections.singletonList(start));

    }

    public void contains() {

    }

    public void nearest() {

    }
}
