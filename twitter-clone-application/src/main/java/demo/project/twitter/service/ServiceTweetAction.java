package demo.project.twitter.service;



import demo.project.twitter.model.TweetAction;
import demo.project.twitter.model.tweet.Tweet;
import demo.project.twitter.repository.TweetActionRepository;
import demo.project.twitter.repository.TweetRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@org.springframework.stereotype.Service
@RequiredArgsConstructor
@Log4j2
public class ServiceTweetAction {
    private final TweetActionRepository repo;

    public Integer countAction(Long tweetId, String actionType) {
        return repo.countAction(tweetId, actionType);
    }

    public Integer marker(Long tweeId, Long profileId, String actionType){
        return repo.marker(tweeId, profileId, actionType);
    }

    public void saveTweetAction(TweetAction tweetAction){
        tweetAction.setCreatedDate(new Date());
        repo.save(tweetAction);
    }

    public Optional<TweetAction> getTweetAction(Long tweetId, Long profileId, String actionType){
        return repo.getTweetAction(tweetId, profileId, actionType);
    }

    public void delTweetAction(TweetAction tweetAction){
        repo.delete(tweetAction);
    }



}
