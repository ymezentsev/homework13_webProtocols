package dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Todos {
    private int userId;
    private int id;
    private String title;
    private boolean completed;
}
