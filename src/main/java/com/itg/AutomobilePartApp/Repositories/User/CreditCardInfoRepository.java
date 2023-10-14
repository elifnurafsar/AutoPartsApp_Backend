package com.itg.AutomobilePartApp.Repositories.User;

import com.itg.AutomobilePartApp.DTO.User.CreditCardInfoDTO;
import com.itg.AutomobilePartApp.Entities.Util.CreditCardInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Repository
public interface CreditCardInfoRepository extends JpaRepository<CreditCardInfo, UUID> {
    @Query(value = "SELECT * FROM \"creditcardinfo\" WHERE \"username\" = ?1" , nativeQuery = true)
    List<CreditCardInfo> getAllCreditCards(String username);

    @Transactional
    @Modifying(clearAutomatically = true)
    @Query(value =  "BEGIN TRANSACTION; "+
            "INSERT INTO \"creditcardinfo\" (cardnumber, cardholder, cvv2, expirationdate, username) VALUES " +
            "(:#{#creditCardInfoDTO.cardnumber}, :#{#creditCardInfoDTO.cardholder}, :#{#creditCardInfoDTO.cvv2}, :#{#creditCardInfoDTO.expirationdate}, :#{#creditCardInfoDTO.username}); "+
            "END;", nativeQuery = true)
    int addNewCreditCard(@Param("creditCardInfoDTO") CreditCardInfoDTO creditCardInfoDTO);

    @Query(value =
            "SELECT * FROM \"creditcardinfo\" WHERE \"cardnumber\" = ?1"
            , nativeQuery = true)
    CreditCardInfo getRecentCreditCardInfo(String cardnumber);
}

