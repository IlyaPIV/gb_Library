package gb.library.reader.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.Instant;

@AllArgsConstructor
@Data
public class ReservedBookDto {
    private int id;
    private int userId;
    private int libraryBookId;
    private int worldBookId;
    private String bookTitle;
    private String inventoryNumber;
    private String username;
    private String author;
    private Instant createdAt;
}