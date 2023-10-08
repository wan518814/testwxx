package boot.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.util.List;

@Data
@Document(indexName = "blog")
@NoArgsConstructor
public class Article {

    @Id
    private  String id;
    private String title;
    @Field(type = FieldType.Nested,includeInParent = true)
    private List<Author> authors;

    public Article(String title){
        this.title = title;
    }
}
