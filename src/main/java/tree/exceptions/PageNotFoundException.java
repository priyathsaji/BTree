package tree.exceptions;

public class PageNotFoundException extends Exception{
    public PageNotFoundException(String errorMessage) {
        super(errorMessage);
    }
}
