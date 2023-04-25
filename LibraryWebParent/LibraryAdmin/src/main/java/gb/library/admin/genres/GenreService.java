package gb.library.admin.genres;

import gb.library.admin.utils.paging.PagingAndSortingHelper;
import gb.library.common.AbstractDaoService;
import gb.library.common.entities.Genre;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class GenreService implements AbstractDaoService<Genre, Integer> {

    private static final int GENRES_PER_PAGE = 8;

    private final GenreRepository repository;

    @Override
    public List<Genre> getAllList() {
        return repository.findAll();
    }

    @Override
    public Genre getById(Integer id) throws NoSuchElementException {
        return repository.findById(id).orElseThrow(); // добавить exception
    }

    @Override
    public Genre create(Genre entity) {
        return repository.save(entity);
    }

    @Override
    public Genre update(Genre entity) {
        Genre existedGenre = repository.findById(entity.getId()).orElseThrow(); // добавить exception
        existedGenre.setName(entity.getName());
        existedGenre.setDescription(entity.getDescription());
        return repository.save(existedGenre);
    }

    @Override
    public void delete(Integer id) {
        // добавить exception
        if (repository.existsById(id)){
            repository.deleteById(id);
        }

    }

    public void listByPage(int pageNum, PagingAndSortingHelper helper) {
        helper.listEntities(pageNum, GENRES_PER_PAGE, repository);
    }

    public String checkUnique(Integer id, String name) {

        Genre genre = repository.findByName(name);
        if (genre != null) {
            if (!Objects.equals(genre.getId(), id)) {
                return "Duplicate";
            }
        }
        return "OK";
    }

    public void save(Genre genre) {
        if (genre.getId() == null) {
            create(genre);
        } else {
            update(genre);
        }
    }
}
