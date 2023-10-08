package boot.handler;

import boot.dto.Article;
import boot.dto.Author;
import boot.dto.EosDictType;
import com.beust.ah.A;
import org.redisson.Redisson;
import org.redisson.api.RLock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.http.MediaType;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.*;

import static java.util.Arrays.asList;

@RestController
public class TestHandler {

    @Resource
    private JdbcTemplate t;

    @Autowired
    private Redisson redisson;

    @Autowired
    private StringRedisTemplate springRedisTemplate;

    @RequestMapping(value="/queryDual")
    @ResponseBody
    public List<String> xxx(@RequestParam("departmentId") String departmentId){
        List<String> m  = t.queryForList("select ? from dual", String.class,departmentId);
        return m;
    }

    @RequestMapping(value="/delete")
    @ResponseBody
    public void delete(@RequestParam("feeReimCode") String feeReimCode){
        t.update("delete from ems_ec_fee_reim_h where fee_reim_code=?", String.class,feeReimCode);
    }
    @RequestMapping(value="/insertTestNoTrans")
    @ResponseBody
    public void insertTestNoTrans(@RequestParam("vendorLId") Long vendorLId){
        insert(vendorLId);
        Long t = vendorLId/0;
    }
    @RequestMapping(value="/insertTestHashTrans")
    @ResponseBody
    @Transactional
    public void insertTestHashTrans(@RequestParam("vendorLId") Long vendorLId){
        insert(vendorLId);
        Long t = vendorLId/0;

    }
    public void insert(Long vendorLId){
        Object[] o = {vendorLId};
        t.update("insert into vendor_temp_l (vendor_l_id) values (?)",o);
    }
    @GetMapping("/eosDictType/{dicttypeid}")
    public EosDictType eosDictType(@PathVariable String dicttypeid,@RequestParam(required = false) String dicttypename) {
        EosDictType d = new EosDictType();
        d.setDicttypeid(dicttypeid);
        d.setDicttypename(dicttypename);
        return d;
    }

    @PostMapping(value="/eosDictType/json/to/properties",
          //  consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
    produces = "application/properties+person")
    public EosDictType eosDictTypeJsonToProperties(@RequestBody EosDictType eosDictType) {
        return eosDictType;
    }

    @PostMapping(value="/eosDictType/properties/to/json",
            consumes = "application/properties+person",
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public EosDictType eosDictTypePropertiesToJson(@RequestBody EosDictType eosDictType) {
        return eosDictType;
    }

    @PostMapping(value="/eosDictType/json/to/json",
            consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public EosDictType eosDictTypeJsonToJson(@RequestBody EosDictType eosDictType) {
        return eosDictType;
    }

    @RequestMapping("/redis/reset_stock")
    public String resetStock(Long stock) {
        springRedisTemplate.opsForValue().set("stock",stock+"");
        return "设置库存为："+stock;
    }

    @RequestMapping("/redis/deduct_stock")
    public String deductStock(){
        int stock = Integer.parseInt(springRedisTemplate.opsForValue().get("stock"));
        if(stock>0){
            int realStock = stock -1;
            springRedisTemplate.opsForValue().set("stock",realStock+"");
            return "扣减成功，剩余库存："+ realStock;
        }
        else{
            return "扣减失败，库存不足";
        }
    }

    @RequestMapping("/redis/deduct_stock2")
    public String deductStock2(){
        String lockKey = "product_001";

        RLock redissonLock = redisson.getLock(lockKey);
        try {
            redissonLock.lock();
            int stock = Integer.parseInt(springRedisTemplate.opsForValue().get("stock"));
            if (stock > 0) {
                int realStock = stock - 1;
                springRedisTemplate.opsForValue().set("stock", realStock + "");
                return "扣减成功，剩余库存：" + realStock;
            } else {
                return "扣减失败，库存不足";
            }
        }
        finally {
            redissonLock.unlock();

        }
    }

    @Autowired
    private ArticleRepository  articleRepository;
    @RequestMapping("/elasticsearch/testSave")
    public void testSave(){
        Article a = new Article("Spring Data Elasticsearch");
        a.setAuthors(asList(new Author("a"),new Author("a2")));
        articleRepository.save(a);
        Article b = new Article("Spring Data Elasticsearch2");
        b.setAuthors(asList(new Author("a"),new Author("b2")));
        articleRepository.save(b);
        Article c = new Article("Spring Data Elasticsearch3");
        c.setAuthors(asList(new Author("a"),new Author("c2")));
        articleRepository.save(c);
    }
    @RequestMapping("/elasticsearch/queryAuthorName")
    public void queryAuthorName(String authorName){
        Page<Article> articles = articleRepository.findByAuthorsName(authorName, PageRequest.of(0,10));
        for(Article a:articles.getContent()){
            System.out.println(a);
            for(Author author:a.getAuthors()){
                System.out.println(author);
            }
        }

    }
    @RequestMapping("/elasticsearch/update")
    public void update(String authorName){
        Page<Article> articles = articleRepository.findByAuthorsName("a", PageRequest.of(0,10));
        Article a = articles.getContent().get(0);
        Author author = new Author(authorName);
        a.setAuthors(Arrays.asList(author));
        articleRepository.save(a);
    }

    @RequestMapping("/testOptional")
    public String testOptional(Long flag){
        Article a = null;
        if(flag==1){
            a = new Article();
            a.setTitle("notNull");
        }

        return Optional.ofNullable(a)
                .map(o -> o.getTitle())
                .orElseGet(() -> { return "isNull";});


    }
}
