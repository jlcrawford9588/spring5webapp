package guru.springframework.spring5webapp.bootstrap;

import guru.springframework.spring5webapp.model.Author;
import guru.springframework.spring5webapp.model.Book;
import guru.springframework.spring5webapp.model.Publisher;
import guru.springframework.spring5webapp.repositories.AuthorRepository;
import guru.springframework.spring5webapp.repositories.BookRepository;
import guru.springframework.spring5webapp.repositories.PublisherRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class BootStrapData implements CommandLineRunner {

    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;
    private final PublisherRepository publisherRepository;

    public BootStrapData(AuthorRepository authorRepository, BookRepository bookRepository, PublisherRepository publisherRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
        this.publisherRepository = publisherRepository;
    }

    @Override
    public void run(String... args) throws Exception {

        Publisher johnnyPublishing = new Publisher();
        johnnyPublishing.setName("Johnny Publishing");
        johnnyPublishing.setAddressLine1("123 N 4th Ave");
        johnnyPublishing.setCity("Awesometown");
        johnnyPublishing.setState("SD");
        johnnyPublishing.setZip("55201");
        publisherRepository.save(johnnyPublishing);

        Author eric = new Author("Eric", "Evans");
        Book ddd = new Book("Domain Driven Design", "123123");
        eric.addBook(ddd);
        ddd.addAuthor(eric);
        ddd.setPublisher(johnnyPublishing);
        johnnyPublishing.addBook(ddd);

        authorRepository.save(eric);
        bookRepository.save(ddd);
        publisherRepository.save(johnnyPublishing);

        Author rod = new Author("Rod", "Johnson");
        Book noEjb = new Book("J2EE Development without EJB", "55554444");
        rod.addBook(noEjb);
        noEjb.addAuthor(rod);
        noEjb.setPublisher(johnnyPublishing);
        johnnyPublishing.addBook(noEjb);

        authorRepository.save(eric);
        bookRepository.save(ddd);
        authorRepository.save(rod);
        bookRepository.save(noEjb);
        publisherRepository.save(johnnyPublishing);

        System.out.println("JC TEST PUBLISHERS: " + publisherRepository.count());
        System.out.println("JC TEST AUTHORS: " + authorRepository.count());
        System.out.println("JC TEST BOOKS: " + bookRepository.count());
    }
}
