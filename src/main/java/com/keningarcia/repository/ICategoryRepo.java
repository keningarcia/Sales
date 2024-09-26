package com.keningarcia.repository;

import com.keningarcia.model.Category;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ICategoryRepo extends IGenericRepo<Category, Integer> {

    //DerivedQueries
    //SELECT * FROM Category c WHERE c.name = ''
    List<Category> findByName(String name);

    List<Category> findByNameLike(String name);
    //%XYZ% -> findByNameContains
    //%XYZ -> findByNameStarsWith
    //XYZ% -> findByNameEndsWith
    List<Category> findByNameGreaterThanEqual(String name);
    List<Category> findByIdCategoryBetween(Integer id1, Integer id2);
    List<Category> findByNameOrderByDescription(String name);
    List<Category> findByNameOrderByDescriptionAsc(String name);


    //JPQL: JAVA PERSISTENCE QUERY LANGUAGE
    @Query("FROM Category c WHERE c.name = :name AND c.description LIKE %:description%")
    //@Query("FROM Product p WHERE p.category.name = :name")
    List<Category> getNameAndDescription1(@Param("name") String name,@Param("description") String description);

    @Query("SELECT new Category(c.name, c.enabled) FROM Category c WHERE c.name = :name AND c.description LIKE %:description%")
    List<Category> getNameAndDescription2(@Param("name") String name,@Param("description") String description);


    //SQL: NATIVE QUERY

    @Query(value = "SELECT * FROM category c WHERE c.name = :name", nativeQuery = true)
    List<Category> getNameSQL(@Param("name") String name);

    @Modifying
    @Query(value = "UPDATE category SET name = :name", nativeQuery = true)
    Integer updateNames(@Param("name") String name);

}
