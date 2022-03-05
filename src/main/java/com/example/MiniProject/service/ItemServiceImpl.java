package com.example.MiniProject.service;


import com.example.MiniProject.dao.ItemDAO;
import com.example.MiniProject.domain.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ItemServiceImpl implements ItemService{

    @Autowired
    ItemDAO itemDAO;


    @Override
    public List<Item> findAll() throws DataAccessException {
        List<Item> items = new ArrayList<>();
        Iterable<Item> tmp = itemDAO.findAll();
        tmp.forEach(items::add);
        return items;
    }

    @Override
    public Item finOne(Integer c_id) throws DataAccessException {
        return itemDAO.getById(c_id);
    }

    @Override
    public List<Item> finByName(String keyword) throws DataAccessException{
        List<Item> items = new ArrayList<>();
        Iterable<Item> tmp = itemDAO.finByName(keyword);
        tmp.forEach(items::add);
        return items;
    }

    @Override
    public List<Item> finByType(String keyword) throws DataAccessException {
        List<Item> items = new ArrayList<>();
        Iterable<Item> tmp = itemDAO.finByType(keyword);
        tmp.forEach(items::add);
        return items;
    }

    @Override
    public List<Item> finByLocation(String keyword) throws DataAccessException {
        List<Item> items = new ArrayList<>();
        Iterable<Item> tmp = itemDAO.finByLocation(keyword);
        tmp.forEach(items::add);
        return items;
    }


    @Modifying
    public void update(Item item) throws DataAccessException {
        itemDAO.save(item);
    }

    @Override
    public void saveItem(Item item) throws DataAccessException{
        itemDAO.save(item);
    }

    @Transactional
    public void delete(Integer id) throws DataAccessException {
        itemDAO.delete(id);
    }


}
