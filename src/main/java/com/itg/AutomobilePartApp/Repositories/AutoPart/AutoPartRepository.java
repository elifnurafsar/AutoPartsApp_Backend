package com.itg.AutomobilePartApp.Repositories.AutoPart;

import com.itg.AutomobilePartApp.DTO.AutoPart.AutoPartDTO;
import com.itg.AutomobilePartApp.DTO.AutoPart.PurchaseInfoDTO;
import com.itg.AutomobilePartApp.Entities.AutoParts.AutoPart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;


@Repository
public interface AutoPartRepository extends JpaRepository<AutoPart, UUID> {

    /*@Transactional
    @Modifying(clearAutomatically = true)
    @Query(value = "INSERT INTO autopart (name, brand, description, stock, category, price, code) VALUES\n" +
            "(:#{#autoPartDTO.name}, :#{#autoPartDTO.brand}, :#{#autoPartDTO.description}, :#{#autoPartDTO.stock}, " +
            ":#{#autoPartDTO.category}, :#{#autoPartDTO.price}, :#{#autoPartDTO.code}", nativeQuery = true)
    int insertProduct(@Param("AutoPartDTO") AutoPartDTO autoPartDTO);
*/
    @Transactional
    @Modifying(clearAutomatically = true)
    @Query(value = "INSERT INTO autopart (name, brand, description, stock, category, price, code) VALUES\n" +
            "(?1, ?2, ?3, ?4, ?5, ?6, ?7);", nativeQuery = true)
    int insertProduct( String name, String brand, String description, int stock, String category, double price, String code);

    @Transactional
    @Modifying(clearAutomatically = true)
    @Query(value = "DELETE FROM \"autopart\" WHERE \"code\" = ?1", nativeQuery = true)
    int deleteProduct(String code);

    @Transactional
    @Modifying(clearAutomatically = true)
    @Query(value = "UPDATE \"autopart\" SET " +
            "name = :#{#autoPartDTO.name}," +
            "brand = :#{#autoPartDTO.brand}, " +
            "category = :#{#autoPartDTO.category}, " +
            "stock = :#{#autoPartDTO.stock}, " +
            "description = :#{#autoPartDTO.description}, " +
            "price = :#{#autoPartDTO.price} " +
            "WHERE code = :#{#autoPartDTO.code} ", nativeQuery = true)
    int updateProduct(@Param("autoPartDTO") AutoPartDTO autoPartDTO);

    @Transactional
    @Modifying(clearAutomatically = true)
    @Query(value = "UPDATE \"autopart\" SET " +
            "stock = stock - :#{#purchaseInfoDTO.number}" +
            "WHERE code = :#{#purchaseInfoDTO.code}", nativeQuery = true)
    int purchaseProduct(@Param("purchaseInfoDTO") PurchaseInfoDTO purchaseInfoDTO);

    @Query(value = "SELECT * FROM \"autopart\" ", nativeQuery = true)
    List<AutoPart> getProducts();

    @Query(value = "SELECT * FROM \"autopart\" ORDER BY id DESC LIMIT 20", nativeQuery = true)
    List<AutoPart> getRecentProducts();

    @Query(value = "SELECT * FROM \"autopart\" WHERE name ilike %?1% or category ilike %?1%", nativeQuery = true)
    List<AutoPart> findProductByCategoryOrName(String name_or_category);

    @Query(value = "SELECT * FROM \"autopart\" WHERE \"code\" = ?1", nativeQuery = true)
    Optional<AutoPart> findProductByCode(String code);


}