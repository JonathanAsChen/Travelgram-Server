package com.wonderfour.server.controller;

import com.wonderfour.server.DTO.PostDTO;
import com.wonderfour.server.VO.ResultVO;
import com.wonderfour.server.entity.Post;
import com.wonderfour.server.entity.UserInfo;
import com.wonderfour.server.enums.ResultEnum;
import com.wonderfour.server.exception.TravelgramException;
import com.wonderfour.server.service.PostService;
import com.wonderfour.server.service.UserService;
import com.wonderfour.server.utils.KeyUtil;
import com.wonderfour.server.utils.ResultVOUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

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


    @GetMapping("/{username}/posts")
    public ResultVO listPost(@PathVariable("username") String username) {
        ResultVO<List<PostDTO>> resultVO = ResultVOUtils.success();
        List<PostDTO> postDTOList = new ArrayList<>();
        resultVO.setData(postDTOList);
        UserInfo author = userService.findByUsername(username);
        List<Post> postList = postService.findByAuthor(username);
        for (Post post : postList) {
            PostDTO postDTO = postService.convert2DTO(author, post);
            postDTOList.add(postDTO);
        }
        return resultVO;
    }

    @GetMapping("/posts/{postId}")
    public ResultVO getPost(@PathVariable("postId") String postId) {
        Post post = postService.findById(postId);
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserInfo currUser = userService.findByUsername(authentication.getName());
        PostDTO postDTO = postService.convert2DTO(currUser, post);

        ResultVO<PostDTO> result = ResultVOUtils.success();
        result.setData(postDTO);
        return result;
    }


    @PostMapping("/{username}/posts")
    @PreAuthorize("authentication.name.equals(#username)")
    public ResultVO createPost(@PathVariable("username") String username,
                         Post post) {
        UserInfo author = userService.findByUsername(username);

        post.setUserId(author.getId());
        String postId = postService.save(post);


        //TODO: tags, images, ...



        ResultVO<PostDTO> result = ResultVOUtils.success();
        return result;
    }


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

}
