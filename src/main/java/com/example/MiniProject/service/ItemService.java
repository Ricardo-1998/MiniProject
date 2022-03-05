package com.example.MiniProject.service;


import com.example.MiniProject.domain.Item;
import org.springframework.dao.DataAccessException;
import org.springframework.data.jpa.repository.Query;
import org.springframework.web.bind.annotation.RequestParam;


import java.util.List;

public interface ItemService {
    public List<Item> findAll() throws DataAccessException;

    public Item finOne(Integer c_id) throws DataAccessException;

    public List<Item> finByName(String keyword) throws DataAccessException;

    public List<Item> finByType(String keyword) throws DataAccessException;

    public List<Item> finByLocation(String keyword) throws DataAccessException;

    void update(Item item) throws DataAccessException;

    void saveItem(Item item) throws DataAccessException;

    void delete(Integer id) throws DataAccessException;
}
