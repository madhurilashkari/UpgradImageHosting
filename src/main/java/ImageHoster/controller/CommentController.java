package ImageHoster.controller;

import ImageHoster.model.Comment;
import ImageHoster.model.Image;
import ImageHoster.model.User;
import ImageHoster.service.CommentService;
import ImageHoster.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
// This is controller class which handles all the request related to the comments.
@Controller
public class CommentController {
    @Autowired
    private CommentService commentService;

    @Autowired
    private ImageService imageService;

    /*
    This method is called when the request pattern is image/{imageId}/{imageTitle} and also the incoming method type is POST.
    The method receives the id of an image, the image title, the comment of the image
    along with the session .
    The method uses the image service to get the image details and comment service to add the comments in the database.
    After persisting data this method redirects to the request handling method with request mapping of type '/images/{imageId}/{title}'
     */

    @RequestMapping(value ="image/{imageId}/{imageTitle}/comments", method = RequestMethod.POST)
    public String createNewComment(@RequestParam("comment") String comment, @PathVariable("imageTitle") String imageTitle, @PathVariable("imageId") Integer imageId, HttpSession session){
        User user = (User) session.getAttribute("loggeduser");
        Image image = imageService.getImage(imageId);
        Comment newcomment = new Comment(comment,user,image);
        commentService.addComment(newcomment);
        return "redirect:/images/" + imageId + "/" + imageTitle ;

        }
}
