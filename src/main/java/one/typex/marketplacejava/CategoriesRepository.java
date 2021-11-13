package one.typex.marketplacejava;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoriesRepository extends CrudRepository<Category, Long> {

    List<Category> findAll();

}
