import java.util.*;

class Book {
    int bookCode;
    String bookTitle, bookAuthor, bookCategory;
    boolean issued;

    Book(int code, String title, String author, String category) {
        bookCode = code; bookTitle = title; bookAuthor = author; bookCategory = category; issued = false;
    }

    void showBookInfo() {
        System.out.println("Code: " + bookCode + ", Title: " + bookTitle + ", Author: " + bookAuthor + ", Category: " + bookCategory + ", Issued: " + issued);
    }

    void issue() { issued = true; }
    void returned() { issued = false; }
}

class Member {
    int memberCode;
    String memberName, memberEmail;
    List<Integer> borrowedBooks = new ArrayList<>();

    Member(int code, String name, String email) {
        memberCode = code; memberName = name; memberEmail = email;
    }

    void showMemberInfo() {
        System.out.println("Code: " + memberCode + ", Name: " + memberName + ", Email: " + memberEmail + ", Borrowed Books: " + borrowedBooks);
    }

    void borrowBook(int code) {
        borrowedBooks.add(code);
    }

    void returnBook(int code) {
        borrowedBooks.remove(Integer.valueOf(code));
    }
}

class Library {
    Map<Integer, Book> bookCollection = new HashMap<>();
    Map<Integer, Member> memberList = new HashMap<>();
    int nextBookCode = 100, nextMemberCode = 100;

    void addNewBook(String title, String author, String category) {
        Book newBook = new Book(nextBookCode++, title, author, category);
        bookCollection.put(newBook.bookCode, newBook);
        System.out.println("New book added with Code: " + newBook.bookCode);
    }

    void addNewMember(String name, String email) {
        Member newMember = new Member(nextMemberCode++, name, email);
        memberList.put(newMember.memberCode, newMember);
        System.out.println("New member added with Code: " + newMember.memberCode);
    }

    void lendBook(int bookCode, int memberCode) {
        Book book = bookCollection.get(bookCode);
        Member member = memberList.get(memberCode);
        if (book != null && member != null && !book.issued) {
            book.issue();
            member.borrowBook(bookCode);
            System.out.println("Book lent successfully.");
        } else {
            System.out.println("Failed to lend the book.");
        }
    }

    void acceptReturn(int bookCode, int memberCode) {
        Book book = bookCollection.get(bookCode);
        Member member = memberList.get(memberCode);
        if (book != null && member != null && book.issued) {
            book.returned();
            member.returnBook(bookCode);
            System.out.println("Book returned successfully.");
        } else {
            System.out.println("Failed to return the book.");
        }
    }

    void showAllBooks() {
        System.out.println("List of all books:");
        for (Book b : bookCollection.values()) {
            b.showBookInfo();
        }
    }

    void showAllMembers() {
        System.out.println("List of all members:");
        for (Member m : memberList.values()) {
            m.showMemberInfo();
        }
    }
}

class CityLibrary {
    public static void main(String[] args) {
        Library lib = new Library();

        lib.addNewBook("Python Basics", "Jane Doe", "Programming");
        lib.addNewMember("Bob", "bob@example.com");

        lib.lendBook(100, 100);
        lib.acceptReturn(100, 100);

        lib.showAllBooks();
        lib.showAllMembers();
    }
}