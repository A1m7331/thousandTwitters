package com.thousandtwitters.controller.rest;

import com.thousandtwitters.model.dao.IFollowsDAO;
import com.thousandtwitters.model.dao.ITweetDAO;
import com.thousandtwitters.model.dao.IUserDAO;
import com.thousandtwitters.model.entities.Tweet;
import com.thousandtwitters.model.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/{userId}/newsfeed")
public class NewsfeedController {

    @Autowired
    private IUserDAO userDAO;

    @Autowired
    private ITweetDAO tweetDAO;

    @Autowired
    private IFollowsDAO followsDAO;

    @RequestMapping
    List<Tweet> getNewsfeed(@PathVariable String userId, @RequestParam Optional<String> search) {
        User user = userDAO.getUser(Integer.valueOf(userId));
        List<User> followed = followsDAO.getFollowed(user);
        followed.add(user);
        return tweetDAO.getNewsfeed(followed, search.orElse(""));
    }
}
