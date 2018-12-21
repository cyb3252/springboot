package com.cyb.elastic.repository;


import com.cyb.elastic.bean.Book;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.List;


/**
 * @author cyb
 * @date 2018/12/19 - 14:56
 */

public interface BookRepository extends ElasticsearchRepository<Book,Integer> {

    //参照
    // https://docs.spring.io/spring-data/elasticsearch/docs/3.0.6.RELEASE/reference/html/
    public List<Book> findByBookNameLike(String bookName);

}
