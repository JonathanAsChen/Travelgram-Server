package com.wonderfour.server.controller;

import com.alibaba.fastjson.JSONObject;
import com.wonderfour.server.DTO.PostDTO;
import com.wonderfour.server.DTO.UserProfileDTO;
import com.wonderfour.server.VO.ResultVO;
import com.wonderfour.server.entity.Avatar;
import com.wonderfour.server.entity.Post;
import com.wonderfour.server.entity.User;
import com.wonderfour.server.repository.PostRepository;
import com.wonderfour.server.service.PostService;
import com.wonderfour.server.service.UserService;
import com.wonderfour.server.utils.Post2PostDTOConverter;
import com.wonderfour.server.utils.ResultVOUtils;
import lombok.extern.slf4j.Slf4j;
import org.bson.types.Binary;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api")
@Slf4j
public class PostController {

    @Autowired
    private MongoTemplate mongoTemplate;

    @Autowired
    private PostService postService;

    @Autowired
    @Qualifier("userDetailsServiceImpl")
    private UserDetailsService userDetailsService;

    @Autowired
    private UserService userService;

    public JSONObject uploadImage(@RequestParam(value = "image") MultipartFile file) {
        if (file.isEmpty()) {
            JSONObject result = new JSONObject();
            result.put("code", 200);
            result.put("message", "please choose an image to upload.");
            return result;
        }

        // 返回的 JSON 对象，这种类可自己封装
        JSONObject jsonResult = new JSONObject();
        String fileName = file.getOriginalFilename();
        try {
            Avatar avatar = new Avatar();
            avatar.setName(fileName);
            avatar.setCreatedTime(new Date());
            avatar.setContent(new Binary(file.getBytes()));
            avatar.setContentType(file.getContentType());
            avatar.setSize(file.getSize());

            Avatar savedFile = mongoTemplate.save(avatar);
            String url = "http://localhost:8080/file/image/" + savedFile.getId();
            jsonResult.put("code", 200);
            jsonResult.put("message", "upload success");
        } catch (IOException e) {
            e.printStackTrace();
            jsonResult.put("code", 500);
            jsonResult.put("message", "upload fail");
        }
        return jsonResult;

    }

    @GetMapping("/{username}/posts")
    public ResultVO listPost(@PathVariable("username") String username) {
        ResultVO<List<PostDTO>> resultVO = ResultVOUtils.success();
        List<PostDTO> postDTOList = new ArrayList<>();
        resultVO.setData(postDTOList);
        List<Post> postList = postService.findByAuthor(username);
        for (Post post : postList) {
            PostDTO postDTO = new PostDTO();
            BeanUtils.copyProperties(post, postDTO);
            UserProfileDTO userProfileDTO = new UserProfileDTO();
            userProfileDTO.setUsername(username);
            userProfileDTO.setAvatar(post.getAvatar());
            postDTO.setUser(userProfileDTO);

            //TODO: save logic


            postDTOList.add(postDTO);
        }
        return resultVO;
    }


    @PostMapping("/{username}/posts")
    @PreAuthorize("authentication.name.equals(#username)")
    public ResultVO createPost(@PathVariable("username") String username,
                         Post post) {
        User author = userService.findByUsername(username);
        post.setAuthor(author.getUsername());
        post.setAvatar(author.getAvatar());
        List<Post> list = author.getPosts();
        Post savedPost = postService.save(post);
        list.add(savedPost);
        userService.create(author);


        ResultVO<PostDTO> result = ResultVOUtils.success();
        result.setData(Post2PostDTOConverter.convert(savedPost));
        return result;
    }


    @DeleteMapping("/{username}/posts/{postId}")
    @PreAuthorize("authentication.name.equals(#username)")
    public ResultVO deletePost(@PathVariable("username") String username,
                               @PathVariable("postId") String postId) {

        Post post = postService.findById(postId);
        if (!post.getAuthor().equals(username)) {

            //TODO: replace with self defined server exception
            return ResultVOUtils.error(403, "Access denied.");
        }

        postService.deleteById(postId);

        return ResultVOUtils.success("Post deleted. Post Id : " + postId +
                ", author: " + username);
    }

    @PutMapping("/{username}/posts")
    @PreAuthorize("authentication.name.equals(#username)")
    public ResultVO editPost(@PathVariable("username") String username,
                             Post post) {

        Post savedPost = postService.findById(post.getId());
        if (!savedPost.getAuthor().equals(username)) {
            return ResultVOUtils.error(403, "Access denied.");
        }

        postService.save(post);
        return ResultVOUtils.success();

    }


}
