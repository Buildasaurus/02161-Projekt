package dtu.library.acceptance_tests;

import java.util.ArrayList;
import java.util.List;

import dtu.library.app.Book;
import dtu.library.app.LibraryApp;
import dtu.library.app.OperationNotAllowedException;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static org.junit.Assert.*;

public class BookSteps {

    private LibraryApp libraryApp;
    private ErrorMessageHolder errorMessageHolder;

    private ArrayList<Book> booksfound;

    private Book tempbook;

    /*
     * Note that the constructor is apparently never called, but there are no null
     * pointer exceptions regarding that libraryApp is not set. When creating the
     * BookSteps object, the Cucumber libraries are using that constructor with an
     * object of class LibraryApp as the default.
     *
     * This also holds for all other step classes that have a similar constructor.
     * In this case, the <b>same</b> object of class LibraryApp is used as an
     * argument. This provides an easy way of sharing the same object, in this case
     * the object of class LibraryApp and the errorMessage Holder, among all step classes.
     *
     * This principle is called <em>dependency injection</em>. More information can
     * be found in the "Cucumber for Java" book available online from the DTU Library.
     */
    public BookSteps(LibraryApp libraryApp, ErrorMessageHolder errorMessageHolder) {
        this.libraryApp = libraryApp;
        this.errorMessageHolder = errorMessageHolder;
    }

    @Given("there is a book with title {string}, author {string}, and signature {string}")
    public void thereIsABookWithTitleAuthorAndSignature(String title, String author, String signature) throws Exception {
        tempbook = new Book(title, author, signature);

    }

    @Given("the book is not in the library")
    public void theBookIsNotInTheLibrary() {
        libraryApp.removeBook(tempbook);
    }


    @Given("these books are contained in the library")
    public void theseBooksAreContainedInTheLibrary(List<List<String>> books) throws Exception {
        for (List<String> bookInfo : books) {
            libraryApp.addBook(new Book(bookInfo.get(0), bookInfo.get(1), bookInfo.get(2)));
        }
    }
}