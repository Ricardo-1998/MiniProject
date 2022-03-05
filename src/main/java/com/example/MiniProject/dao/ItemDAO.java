package com.example.MiniProject.dao;


import com.example.MiniProject.domain.Item;
import org.springframework.dao.DataAccessException;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ItemDAO extends JpaRepository<Item, Integer> {

    @Query(value = "select * from item i where i.name = ?1", nativeQuery = true)
    public List<Item> finByName(String keyword) throws DataAccessException;

    @Query(value = "select * from item i where i.type = ?1", nativeQuery = true)
    public List<Item> finByType(String keyword) throws DataAccessException;

    @Query(value = "select * from item i where i.location = ?1", nativeQuery = true)
    public List<Item> finByLocation(String keyword) throws DataAccessException;

    @Modifying
    @Query(nativeQuery = true, value = "Update item i set i.name = ?2, i.location = ?3, i.type = ?4 where i.c_id = ?1")
    public List<Item>update(String keyword) throws DataAccessException;

    @Modifying
    @Query(nativeQuery = true, value = "delete from item i where i.c_id = ?1")
    public void delete(Integer id);
}
