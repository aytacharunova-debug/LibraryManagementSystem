package 2LibraryApp;

import com.library.model.*;
import com.library.service.*;
import java.util.Scanner;
import java.util.List;

public class Main {
    // Bütün servisləri və skaneri statik olaraq yaradırıq
    private static AuthorService authorService = new AuthorService();
    private static BookService bookService = new BookService();
    private static BorrowsService borrowsService = new BorrowsService();
    private static FineService fineService = new FineService();
    private static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("***********************************************");
        System.out.println("*   KİTABXANA İDARƏETMƏ SİSTEMİNƏ XOŞ GƏLDİNİZ *");
        System.out.println("***********************************************");

        while (true) {
            System.out.println("\n========= ƏSAS MENYU =========");
            System.out.println("1. Müəllif Əməliyyatları");
            System.out.println("2. Kitab Əməliyyatları");
            System.out.println("3. Kitab Ödünc Vermək (Borrow)");
            System.out.println("4. Kitab Qaytarmaq (Return)");
            System.out.println("0. Proqramdan Çıx");
            System.out.print("Seçiminiz: ");

            int choice = sc.nextInt();
            sc.nextLine(); // Boşluq (enter) problemini həll edir

            switch (choice) {
                case 1:
                    authorMenu();
                    break;
                case 2:
                    bookMenu();
                    break;
                case 3:
                    borrowBookFlow();
                    break;
                case 4:
                    returnBookFlow();
                    break;
                case 0:
                    System.out.println("Sistem bağlanır. Sağ olun!");
                    System.exit(0);
                default:
                    System.out.println("Xəta: Yanlış seçim etdiniz!");
            }
        }
    }

    // --- MÜƏLLİF MENYUSU ---
    private static void authorMenu() {
        System.out.println("\n[1] Müəllifləri Siyahıla | [2] Yeni Müəllif Əlavə Et");
        int subChoice = sc.nextInt();
        sc.nextLine();

        if (subChoice == 1) {
            List<Author> authors = authorService.getAllAuthors();
            System.out.println("\n--- Müəlliflərin Siyahısı ---");
            for (Author a : authors) {
                System.out.println("ID: " + a.getAuthorId() + " | Ad: " + a.getName() + " " + a.getSurname());
            }
        } else if (subChoice == 2) {
            System.out.print("Müəllif adı: "); String name = sc.nextLine();
            System.out.print("Müəllif soyadı: "); String surname = sc.nextLine();
            System.out.print("Ölkəsi: "); String country = sc.nextLine();
            
            authorService.addAuthor(new Author(0, name, surname, country));
        }
    }

    // --- KİTAB MENYUSU ---
    private static void bookMenu() {
        System.out.println("\n[1] Bütün Kitablar | [2] Yeni Kitab Əlavə Et");
        int subChoice = sc.nextInt();
        sc.nextLine();

        if (subChoice == 1) {
            List<Book> books = bookService.getAllBooks();
            System.out.println("\n--- Kitab Siyahısı ---");
            for (Book b : books) {
                System.out.println("ID: " + b.getBookId() + " | Başlıq: " + b.getTitle() + " | Status: " + b.getStatus());
            }
        } else if (subChoice == 2) {
            Book b = new Book();
            System.out.print("Kitabın adı: "); b.setTitle(sc.nextLine());
            System.out.print("Müəllif ID-si: "); b.setAuthorId(sc.nextInt());
            System.out.print("Kateqoriya ID-si: "); b.setCategoryId(sc.nextInt());
            System.out.print("Nəşriyyat ID-si: "); b.setPublisherId(sc.nextInt());
            sc.nextLine(); // buffer clear
            System.out.print("ISBN: "); b.setIsbn(sc.nextLine());
            System.out.print("Nəşr ili: "); b.setPublishYear(sc.nextInt());
            
            bookService.addBook(b);
        }
    }

    // --- KİTAB VERMƏ ---
    private static void borrowBookFlow() {
        System.out.print("Vermək istədiyiniz Kitab ID: ");
        int bookId = sc.nextInt();
        System.out.print("Götürən Üzv (Member) ID: ");
        int memberId = sc.nextInt();
        
        borrowsService.lendBook(bookId, memberId);
    }

    // --- KİTAB QAYTARMA VƏ CƏRİMƏ ---
    private static void returnBookFlow() {
        System.out.print("Geri alınan Borrow (Götürmə) ID-si: ");
        int borrowId = sc.nextInt();
        
        // Kitabı qaytarırıq
        borrowsService.returnBook(borrowId);
        
        // Əgər gecikmə varsa avtomatik cərimə yoxlayırıq (Service-dəki məntiqlə)
        // Bunun üçün BorrowsService daxilində FineService-i çağırmaq daha yaxşıdır, 
        // amma sadəlik üçün burada returnBook işini görmək kifayətdir.
    }
}