package gb.library.admin.books.wishlist;

import gb.library.admin.utils.paging.SearchRepository;
import gb.library.common.entities.Author;
import gb.library.common.entities.BooksWishlist;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface WishedBooksRepository extends SearchRepository<BooksWishlist, Integer> {
    @Query("SELECT b FROM BooksWishlist b WHERE b.book.title LIKE %?1% or b.book.author.firstName LIKE %?1%"
            + " OR b.book.author.lastName LIKE %?1% OR b.book.genre.name LIKE %?1%")
    Page<BooksWishlist> findAll(String keyword, Pageable pageable);

    @Query("select b from BooksWishlist b where b.book.title = ?1 and b.book.author = ?2")
    BooksWishlist findByTitleAndAuthor(String title, Author author);

}
