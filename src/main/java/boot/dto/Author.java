package boot.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Author {
    private  String name;
    public Author(String name){
        this.name= name;
    }
}
