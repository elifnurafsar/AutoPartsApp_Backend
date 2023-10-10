package com.itg.AutomobilePartApp.Repositories.Util;


import com.itg.AutomobilePartApp.DTO.AutoPart.PurchaseInfoDTO;
import com.itg.AutomobilePartApp.Entities.Util.Orders;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.List;
import java.util.UUID;

@Repository
public interface OrdersRepository extends JpaRepository<Orders, UUID> {

    @Transactional
    @Modifying(clearAutomatically = true)
    @Query(value = "BEGIN TRANSACTION;" +
            "UPDATE autopart SET stock = stock -:#{#purchaseInfoDTO.count} WHERE id IN (SELECT id from autopart where code = :#{#purchaseInfoDTO.code}) ; "+
            "INSERT INTO \"orders\" (username, code, address, count, created_at) VALUES" +
            "(:#{#purchaseInfoDTO.username}, :#{#purchaseInfoDTO.code}, :#{#purchaseInfoDTO.address}, :#{#purchaseInfoDTO.count}, NOW()); " +
            "END;", nativeQuery = true)
    int purchaseProduct(@Param("purchaseInfoDTO") PurchaseInfoDTO purchaseInfoDTO);


    @Query(value = "SELECT * FROM \"orders\" WHERE username ilike %?1%", nativeQuery = true)
    List<Orders> getMyOrders(String username);

    @Query(value = "SELECT * FROM \"orders\"", nativeQuery = true)
    List<Orders> getAllOrders();

    @Transactional
    @Modifying(clearAutomatically = true)
    @Query(value = "DELETE FROM \"orders\" WHERE \"username\" = ?1 and created_at = ?2", nativeQuery = true)
    int deleteOrder(String username, Timestamp t);

}
