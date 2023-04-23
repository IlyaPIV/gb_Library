package gb.lib.common.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.Instant;
import java.time.LocalDate;

@Entity
@Table(name = "books_on_hands")
@NoArgsConstructor
@Getter
@Setter
public class BookOnHands extends IdBasedEntity{
    @ManyToOne()
    @JoinColumn(name = "book_id", nullable = false)
    private LibraryBook book;
    @ManyToOne()
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
    @Column(name = "taken_at", nullable = false)
    private LocalDate takenAt;
    private boolean returned;

    @Column(name = "created_at")
    @CreationTimestamp
    private Instant createdAt;
    @Column(name = "updated_at")
    @UpdateTimestamp
    private Instant updatedAt;

    /*
     * CONSTRUCTORS
     */

    /*
     * TRANSIENT FIELDS
     */

    /*
     * OVERWRITE METHODS
     */

    /*
     * METHODS
     */

}
