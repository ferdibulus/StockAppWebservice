package com.stock.stockapp.service;

import com.stock.stockapp.entity.Products;
import com.stock.stockapp.exceptions.ErrorStatusConstants;
import com.stock.stockapp.repository.ProductDao;
import com.stock.stockapp.responses.BaseErrorResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

@Service
public class ProductService {
    @Autowired
    ProductDao productDao;

    @Autowired
    private EntityManager entityManager;

    public List<Products> getListOfProducts() {
        return productDao.findAll();
    }

    public Products save(Products products) {
        Optional<Products> product = productDao.findById(products.getId());
        if(product.isPresent()){
            throw new BaseErrorResponse(ErrorStatusConstants.PRODUCT_IS_EXIST,ErrorStatusConstants.PRODUCT_IS_EXIST_MESSAGE);
        }else{
            productDao.save(products);
            return products;
        }
    }


    public static void main(String[] args) {

        int intArray[] = new int[]{  8,6,18, 4, 7};
        System.out.println(sumFourDivisors(intArray));
    }


    public static int sumFourDivisors(int[] nums) {
        // System uses exact matching for the evaluation. Please do not leave any debug log or
        // your exam point may decrease.

        if(nums.length == 0){
            return 0;
        }

        Map<Integer,ArrayList<Integer>> list = new HashMap<>();
        ArrayList<Integer>  indexArray = new ArrayList<>();
        ArrayList<Integer> sumArray = new ArrayList<>();
        for(int j=0; j<nums.length; j++){
            ArrayList<Integer> arr = new ArrayList<>();
            for(int i=1; i<=nums[j]; i++){
                if(nums[j]%i==0){
                    arr.add(i);
                }
            }
            list.put(j, arr);
        }

        list.forEach((index, item)->{
            if(item.size() == 4){
                indexArray.add(index);            }
        });
        indexArray.forEach(el->{
            AtomicInteger sum = new AtomicInteger();
            list.get(Integer.parseInt(el.toString())).forEach(element->{
                sum.addAndGet(element);
            });
            sumArray.add(Integer.parseInt(sum.toString()));
        });

        int result = sumArray.stream().mapToInt(v->v).max().orElseThrow(NoSuchElementException::new);
        return result;
    }
    public Products update(Products item, Integer id){
        Optional<Products> product = productDao.findById(id);
        if(product.isPresent()){
            product.map(temp->{
                temp.setName(item.getName());
                temp.setBarcode(item.getBarcode());
                temp.setAmount(item.getAmount());
                temp.setPrice(item.getPrice());
                productDao.save(temp);
                item.setId(id);
                return item;
            });
        }else{
            throw new BaseErrorResponse(ErrorStatusConstants.PRODUCT_IS_NOT_FOUND,ErrorStatusConstants.PRODUCT_IS_NOT_FOUND_MESSAGE);
        }
        return item;
    }

    public String deleteById(Integer id) {
        Optional<Products> product = productDao.findById(id);
        if(product.isPresent()){
            productDao.deleteById(id);
        }else{
            throw new BaseErrorResponse(ErrorStatusConstants.PRODUCT_IS_NOT_FOUND,ErrorStatusConstants.PRODUCT_IS_NOT_FOUND_MESSAGE);
        }
        return "Success";
    }
}

