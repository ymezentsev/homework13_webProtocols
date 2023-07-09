package dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Comment {
    private int postId;
    private int id;
    private String name;
    private String email;
    private String body;
}
