package mr.tips.ReviewService.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserImage {
    private long id;
    private String email;
    private String phoneNumber;
}
