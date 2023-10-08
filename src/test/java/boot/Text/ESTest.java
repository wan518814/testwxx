package boot.Text;

import boot.dto.Article;
import boot.dto.Author;
import boot.handler.ArticleRepository;
import com.beust.ah.A;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;

import static java.util.Arrays.asList;

@SpringBootTest
@RunWith(SpringRunner.class)
public class ESTest {
    @Autowired
    private ArticleRepository  articleRepository;
    @Test
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
    @Test
    public void queryAuthorName(){
        Page<Article> articles = articleRepository.findByAuthorsName("a", PageRequest.of(0,10));
        for(Article a:articles.getContent()){
            System.out.println(a);
            for(Author author:a.getAuthors()){
                System.out.println(author);
            }
        }

    }

}
