package com.cyb.elastic;

import com.cyb.elastic.bean.Article;
import com.cyb.elastic.bean.Book;
import com.cyb.elastic.repository.BookRepository;
import io.searchbox.client.JestClient;
import io.searchbox.core.Index;
import io.searchbox.core.Search;
import io.searchbox.core.SearchResult;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;

@RunWith(SpringRunner.class)
@SpringBootTest
public class Springboot02ElasticApplicationTests {

    @Autowired
    private JestClient jestClient;

    @Autowired
    BookRepository bookRepository;

    @Test
    public void testEsSearch () {
        for (Book book : bookRepository.findByBookNameLike("游")) {
            System.out.println(book);
        }
    }

    @Test
    public void testIndex () {
        Book book = new Book(1, "西游记", "吴承恩");
        bookRepository.index(book);
    }

    /**
     * 测试Jest 索引
     */
    @Test
    public void contextLoads () {
        //给es中索引(保存)一个文档
        Article article = new Article(1, "haoxiaoxi", "zhansan", "helloworld");
        //构建一个索引功能
        Index index = new Index.Builder(article).index("hello").type("news").build();


        try {
            //执行
            jestClient.execute(index);
        } catch (IOException e) {
            e.printStackTrace();
        }
        //浏览器输入http://192.168.1.5:9200/hello/news/1 可以得到文章的json串
    }

    /**
     * 测试Jest 查询
     */
    @Test
    public void testSearch () {
        //查询表达式
        String json = "{\n" +
                "    \"query\": {\n" +
                "        \"match\": {\n" +
                "            \"content\": \"helloworld\"\n" +
                "        }\n" +
                "    }\n" +
                "}";
        //构建搜索功能
        Search build = new Search.Builder(json).addIndex("hello").addType("news").build();

        try {
            SearchResult result = jestClient.execute(build);
            System.out.println(result.getJsonString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}

