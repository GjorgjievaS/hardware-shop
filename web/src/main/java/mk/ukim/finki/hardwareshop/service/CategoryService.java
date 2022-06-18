package mk.ukim.finki.hardwareshop.service;



import mk.ukim.finki.hardwareshop.model.Category;

import java.util.List;

public interface CategoryService {

    Category create(String name, String description);

    Category update(String name, String description);

   // void delete(String name);

    List<Category> listCategories();

    Category save(Category category);

    Category findByName(String name);

   // List<Category> searchCategories(String searchText);

}
