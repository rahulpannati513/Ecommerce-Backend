package org.rahul.ecommercebackend.Repository;

import org.rahul.ecommercebackend.Model.CategoryProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CategoryRepository extends JpaRepository<CategoryProduct,Long> {

  public CategoryProduct findByName(String name);

  @Query("Select c from CategoryProduct c where c.name=:name AND c.parentCategory.name=:parentCategoryName")
  public CategoryProduct findByNameAndParent(@Param("name") String name,@Param("parentCategoryName")String parentCategoryName);

}
