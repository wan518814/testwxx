package boot.handler;

import boot.dto.Article;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;


@Repository
@Component
public interface ArticleRepository extends ElasticsearchRepository<Article,String> {
    Page<Article> findByAuthorsName(String name, Pageable pageable);
    Page<Article> findByTitleIsContaining(String word, Pageable pageable);
    Page<Article> findByTitle(String title, Pageable pageable);

}
