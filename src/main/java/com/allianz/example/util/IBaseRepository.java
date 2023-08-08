package com.allianz.example.util;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;
@NoRepositoryBean
public interface IBaseRepository<T, ID> extends JpaRepository<T, ID> {
    Optional<T> findByUuid(UUID uuid);

    int deleteByUuid(UUID uuid);
}
