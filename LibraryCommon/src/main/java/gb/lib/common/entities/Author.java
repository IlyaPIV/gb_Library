package gb.lib.common.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.Instant;

@Entity
@Table(name = "authors")
@NoArgsConstructor
@Getter
@Setter
public class Author extends IdBasedEntity{
    @Column(name = "first_name", nullable = false, length = 45)
    private String firstName;
    @Column(name = "last_name", nullable = false, length = 60)
    private String lastName;

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