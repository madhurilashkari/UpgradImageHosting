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

@Controller
public class CommentController {
    @Autowired
    private CommentService commentService;

    @Autowired
    private ImageService imageService;

    @RequestMapping(value ="image/{imageId}/{imageTitle}/comments", method = RequestMethod.POST)
    public String createNewCommnet(@RequestParam("comment") String comment, @PathVariable("imageTitle") String imageTitle, @PathVariable("imageId") Integer imageId, HttpSession session){
        User user = (User) session.getAttribute("loggeduser");
        Image image = imageService.getImage(imageId);

        Comment newcomment = new Comment(comment,user,image);
        System.out.println("Inside controller");
        commentService.saveCommnet(newcomment);
        return "redirect:/images/" + imageId + "/" + imageTitle ;

        }
}
