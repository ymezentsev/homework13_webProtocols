package dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Post {
    private int userId;
    private int id;
    private String title;
    private String body;
}
