package com.ta.c02_5.repository;

import com.ta.c02_5.model.RequestUpdateItemModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RequestUpdateItemDB extends JpaRepository<RequestUpdateItemModel, Integer> {
    Optional<RequestUpdateItemModel> findByIdRequestUpdateItem(Integer idRequestUpdateItem);
}
