package com.ordercustomer.ordercustomerproject.repo;

import com.ordercustomer.ordercustomerproject.entities.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

@Repository
public interface OrderRepo extends JpaRepository<Order,Long> {
    @Query("select o from Order o where o.createdDate >= :creationDate")
    List<Order> findAllWithCreatedDateAfter(
            @Param("creationDate") Date creationDate);
}
