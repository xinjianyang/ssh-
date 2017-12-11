package com.kaishengit.service;

import com.kaishengit.dao.ProductDao;
import com.kaishengit.pojo.Product;
import com.kaishengit.util.RequestQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author NativeBoy
 */

@Service
@Transactional(rollbackFor = RuntimeException.class)
public class ProductService {

    @Autowired
    private ProductDao productDao;


    public List<Product> findAll() {
        return productDao.page(0,100);
    }

    public void save(Product product) {
        productDao.save(product);
    }

    public Product findById(Integer id) {
        return productDao.findById(id);
    }

    public void update(Product product) {
        productDao.save(product);
    }

    public void delete(Integer id) {
        productDao.deleteById(id);
    }

    public List<Product> findByRequestQuery(List<RequestQuery> requestQueryList) {

        return productDao.findByRequestQueryList(requestQueryList);
    }
}
