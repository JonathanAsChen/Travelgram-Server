package com.wonderfour.server.controller;

import com.wonderfour.server.DTO.CommentDTO;
import com.wonderfour.server.DTO.PostDTO;
import com.wonderfour.server.DTO.UserProfileDTO;
import com.wonderfour.server.VO.ResultVO;
import com.wonderfour.server.entity.Comment;
import com.wonderfour.server.entity.Post;
import com.wonderfour.server.entity.UserInfo;
import com.wonderfour.server.enums.ResultEnum;
import com.wonderfour.server.exception.TravelgramException;
import com.wonderfour.server.service.*;
import com.wonderfour.server.utils.KeyUtil;
import com.wonderfour.server.utils.ResultVOUtils;
import io.swagger.v3.oas.annotations.Operation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

import javax.xml.transform.Result;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
@Slf4j
public class PostController {


    @Autowired
    private PostService postService;

    @Autowired
    @Qualifier("userDetailsServiceImpl")
    private UserDetailsService userDetailsService;

    @Autowired
    private UserService userService;

    @Autowired
    private ImageService imageService;

    @Autowired
    private TagService tagService;

    @Autowired
    private LikesService likesService;

    @Autowired
    private FavoriteService favoriteService;

    @Autowired
    private CommentService commentService;

    @Operation(description = "Get post list posted by the specific user.")
    @GetMapping("/{username}/posts")
    public ResultVO listPost(@PathVariable("username") String username) {
        ResultVO<List<PostDTO>> resultVO = ResultVOUtils.success();
        List<PostDTO> postDTOList = new ArrayList<>();
        resultVO.setData(postDTOList);
        UserInfo author = userService.findByUsername(username);
        List<Post> postList = postService.findByAuthor(username);

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserInfo currUser = userService.findByUsername(authentication.getName());

        for (Post post : postList) {
            PostDTO postDTO = postService.convert2DTO(currUser, post);
            postDTOList.add(postDTO);
        }
        return resultVO;
    }

    @Operation(description = "Get post by postId.")
    @GetMapping("/posts/{postId}")
    public ResultVO getPost(@PathVariable("postId") String postId) {
        Post post = postService.findById(postId);
        if (post == null) {
            throw new TravelgramException(ResultEnum.POST_NOT_FOUND);
        }

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserInfo currUser = userService.findByUsername(authentication.getName());
        PostDTO postDTO = postService.convert2DTO(currUser, post);

        ResultVO<PostDTO> result = ResultVOUtils.success();
        result.setData(postDTO);
        return result;
    }

    @Operation(description = "Post an article.")
    @PostMapping("/{username}/posts")
    @PreAuthorize("authentication.name.equals(#username)")
    public ResultVO createPost(@PathVariable("username") String username,
                               PostDTO postDTO) {
        UserInfo author = userService.findByUsername(username);

        Post post = new Post();
        BeanUtils.copyProperties(postDTO, post);

        post.setUserId(author.getId());
        String postId = postService.save(post);


        imageService.insert(postDTO.getImages(), postId);
        tagService.savePostTagRelation(postDTO.getTags(), postId);


        ResultVO<PostDTO> result = ResultVOUtils.success();
        return result;
    }

    @Operation(description = "Delete an article.")
    @DeleteMapping("/{username}/posts/{postId}")
    @PreAuthorize("authentication.name.equals(#username)")
    public ResultVO deletePost(@PathVariable("username") String username,
                               @PathVariable("postId") String postId) {

        UserInfo author = userService.findByUsername(username);
        Post post = postService.findById(postId);
        if (!post.getUserId().equals(author.getId())) {

            throw new TravelgramException(ResultEnum.FORBIDDEN);
        }

        postService.deleteById(postId);

        return ResultVOUtils.success("Post deleted. Post Id : " + postId +
                ", author: " + username);
    }

    @Operation(description = "Edit an article.")
    @PutMapping("/{username}/posts")
    @PreAuthorize("authentication.name.equals(#username)")
    public ResultVO editPost(@PathVariable("username") String username,
                             Post post) {

        UserInfo author = userService.findByUsername(username);
        Post savedPost = postService.findById(post.getId());
        if (!savedPost.getUserId().equals(author.getId())) {
            throw new TravelgramException(ResultEnum.FORBIDDEN);
        }

        postService.update(post);
        return ResultVOUtils.success();

    }

    @Operation(description = "Like a post.")
    @PostMapping("/posts/{postId}/like")
    @PreAuthorize("authentication.authenticated")
    public ResultVO likePost(@PathVariable("postId") String postId) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserInfo currUser = userService.findByUsername(authentication.getName());

        Post post = postService.findById(postId);
        if (post == null) {
            throw new TravelgramException(ResultEnum.POST_NOT_FOUND);
        }

        likesService.likePost(currUser.getId(), postId);

        return ResultVOUtils.success();
    }

    @Operation(description = "Favorite a post.")
    @PostMapping("/posts/{postId}/favorite")
    @PreAuthorize("authentication.authenticated")
    public ResultVO favoritePost(@PathVariable("postId") String postId) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserInfo currUser = userService.findByUsername(authentication.getName());

        Post post = postService.findById(postId);
        if (post == null) {
            throw new TravelgramException(ResultEnum.POST_NOT_FOUND);
        }

        favoriteService.favoritePost(currUser.getId(), postId);

        return ResultVOUtils.success();
    }

    @PostMapping("/posts/{postId}/delike")
    @PreAuthorize("authentication.authenticated")
    public ResultVO delikePost(@PathVariable("postId") String postId) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserInfo currUser = userService.findByUsername(authentication.getName());

        Post post = postService.findById(postId);
        if (post == null) {
            throw new TravelgramException(ResultEnum.POST_NOT_FOUND);
        }

        likesService.delikePost(currUser.getId(), postId);

        return ResultVOUtils.success();
    }


    @PostMapping("/posts/{postId}/defavorite")
    @PreAuthorize("authentication.authenticated")
    public ResultVO defavoritePost(@PathVariable("postId") String postId) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserInfo currUser = userService.findByUsername(authentication.getName());

        Post post = postService.findById(postId);
        if (post == null) {
            throw new TravelgramException(ResultEnum.POST_NOT_FOUND);
        }

        favoriteService.defavoritePost(currUser.getId(), postId);

        return ResultVOUtils.success();
    }

    @PostMapping("/posts/{postId}/comments")
    @PreAuthorize("authentication.authenticated")
    public ResultVO postComment(@PathVariable("postId") String postId, CommentDTO commentDTO) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserInfo currUser = userService.findByUsername(authentication.getName());
        Comment comment = new Comment();
        BeanUtils.copyProperties(commentDTO, comment);
        comment.setPostId(postId);
        comment.setUserId(currUser.getId());
        String commentId = commentService.commentPost(comment);
        return ResultVOUtils.success("post comment success.");
    }


    @GetMapping("/posts/{postId}/comments")
    public ResultVO listComments(@PathVariable("postId") String postId) {
        ResultVO<List<CommentDTO>> resultVO = ResultVOUtils.success();
        List<CommentDTO> list = new ArrayList<>();
        resultVO.setData(list);
        List<Comment> comments = commentService.getByPostId(postId);
        for (Comment comment : comments) {
            CommentDTO commentDTO = new CommentDTO();
            BeanUtils.copyProperties(comment, commentDTO);
            UserInfo userInfo = userService.findById(comment.getUserId());
            UserProfileDTO userProfileDTO = new UserProfileDTO();
            BeanUtils.copyProperties(userInfo, userProfileDTO);
            commentDTO.setUser(userProfileDTO);
            list.add(commentDTO);
        }
        return resultVO;
    }

}
