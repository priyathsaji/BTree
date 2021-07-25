package tree;

import tree.pagestore.PageStore;

import java.util.List;
import java.util.Stack;


public class BTreeIterator {
    PageStore pageStore;
    Page start;

    Stack<Page> iteratorPageStack = new Stack<>();

    public BTreeIterator(Page start, PageStore pageStore) {
        this.pageStore = pageStore;
        this.start = start;

    }

    public void print() {
        List<PageItem> pageItemList = start.getPageItemList();
        for (int i = 0; i < pageItemList.size(); i++) {
            print(pageItemList.get(i), i == pageItemList.size() - 1);
        }
    }

    private void print(PageItem pageItem,boolean isLast) {
        if(pageStore.contains(pageItem.getLeft())){
            List<PageItem> pageItemList = pageStore.get(pageItem.getLeft()).getPageItemList();
            for(int i=0;i<pageItemList.size();i++){
                print(pageItemList.get(i), i == pageItemList.size() - 1);
            }
        }
        System.out.print(pageItem.getValue() +"   |  ");

        if(isLast){
            if(pageStore.contains(pageItem.getRight())){
                List<PageItem> pageItemList = pageStore.get(pageItem.getRight()).getPageItemList();
                for(int i=0;i<pageItemList.size();i++){
                    print(pageItemList.get(i), i == pageItemList.size() - 1);
                }
            }
        }

    }

}
