package net.codejava;

import java.util.List;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.Query;

public class BookManager {
	static EntityManagerFactory factory;
	static EntityManager entityManager;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		begin();
//		create();
//		update();
//		find();
//		query();
		remove();
		end();

	}

	private static void remove() {
		Integer primaryKey = 1;
		Book reference = entityManager.getReference(Book.class, primaryKey);
		entityManager.remove(reference);
	}

	private static void query() {
		String jpql = "Select b From Book b Where b.price > 30";
		Query query = entityManager.createQuery(jpql);
		List<Book> listBooks = query.getResultList();
		listBooks.stream().forEach(book -> System.out.println(book.getTitle() + " " + book.getAuthor()));
	}

	private static void find() {
		Integer primaryKey = 2;
		Book book = entityManager.find(Book.class, primaryKey);
		System.out.println(book.getTitle());
		System.out.println(book.getAuthor());
		System.out.println(book.getPrice());
	}

	private static void update() {
		Book existBook = new Book();
		existBook.setBookId(1);
		existBook.setAuthor("Effective Java 2nd edition");
		existBook.setTitle("joshua bosh");
		existBook.setPrice(19);
		entityManager.merge(existBook);
	}

	private static void begin() {
		factory = Persistence.createEntityManagerFactory("BookUnit");
		entityManager = factory.createEntityManager();
		entityManager.getTransaction().begin();
	}

	private static void end() {
		entityManager.getTransaction().commit();
		entityManager.close();
		factory.close();
	}

	private static void create() {
		Book newBook = new Book();
		newBook.setAuthor("Thinking in java");
		newBook.setTitle("Bruce Eckel");
		newBook.setPrice(45);
		entityManager.persist(newBook);
	}
}
