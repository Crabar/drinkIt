package guru.drinkit.controller;

import guru.drinkit.domain.UserBar;
import guru.drinkit.repository.UserBarRepository;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;

/**
 * Created by pkolmykov on 12/11/2014.
 */
@Controller
@RequestMapping("userbar")
public class UserBarController {

    @Autowired
    UserBarRepository userBarRepository;
    private static final Log logger = LogFactory.getLog(UserBarController.class);

    @RequestMapping(value = "{userId}", method = RequestMethod.PUT)
    @ResponseStatus(value = HttpStatus.NO_CONTENT, reason = "Saved")
    public void saveUserBar(@RequestBody UserBar userBar, @PathVariable String userId) {
        Assert.isTrue(userId.equals(userBar.getUserId()), "id from uri and id from json should be identical");
        userBarRepository.save(userBar);
    }

    @RequestMapping(value = "/{userId}", method = RequestMethod.GET)
    @ResponseBody
    public UserBar getUserBar(@PathVariable ObjectId userId) {
        return userBarRepository.findOne(userId);
    }

//    @ExceptionHandler
//    @ResponseStatus(HttpStatus.BAD_REQUEST)
//    public void handle(HttpMessageNotReadableException e) {
//        logger.warn("Returning HTTP 400 Bad Request", e);
//    }

}
