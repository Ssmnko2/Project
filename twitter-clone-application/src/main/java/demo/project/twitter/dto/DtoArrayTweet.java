package demo.project.twitter.dto;

import lombok.Data;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Data
public class DtoArrayTweet {


    private Long[] arrTweetId;
}
