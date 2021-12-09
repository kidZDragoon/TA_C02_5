package com.ta.c02_5.repository;

import com.ta.c02_5.model.RequestUpdateItemModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface RequestUpdateItemDB extends JpaRepository<RequestUpdateItemModel, Long> {

}
