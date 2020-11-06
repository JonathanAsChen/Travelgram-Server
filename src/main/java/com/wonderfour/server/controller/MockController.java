package com.wonderfour.server.controller;

import com.wonderfour.server.DTO.CommentDTO;
import com.wonderfour.server.DTO.MockPostDTO;
import com.wonderfour.server.DTO.PostDTO;
import com.wonderfour.server.DTO.UserProfileDTO;
import com.wonderfour.server.VO.ResultVO;
import com.wonderfour.server.utils.ResultVOUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * @author Yifan Chen
 * @version 1.0.0
 * @since 10/10/2020 2:27 PM
 */
@RestController
public class MockController {

//    {
//        mockPost.setId("1");
//        Avatar avatar = new Avatar();
//        avatar.setUrl("https://randomuser.me/api/portraits/women/44.jpg");
//        mockPost.setUser(new UserProfileDTO("username", avatar));
//        mockPost.setSaved(true);
//        mockPost.setLocation("Santorini, Greece");
//        mockPost.setTitle("Santorini");
//        mockPost.setDescription("Santorini is one of the Cyclades islands in the Aegean Sea. It was devastated by a volcanic");
//        mockPost.setLikes(43l);
//        mockPost.setFavorites(50l);
//        mockPost.setComments(3212l);
//        List<String> images = Arrays.asList("https://images.unsplash.com/photo-1507501336603-6e31db2be093?auto=format&fit=crop&w=800&q=80",
//                "https://images.unsplash.com/photo-1507501336603-6e31db2be093?auto=format&fit=crop&w=800&q=80");
//        mockPost.setImages(images);
//        mockPost.setArticle(new Article("content"));
//    }


    @GetMapping("/api/getmockdata-2")
    public ResultVO<List<PostDTO>> getMockData2() {

        List<PostDTO> list = new ArrayList<>();
        for (int i = 1; i < 3; i++) {
            list.add(mock(i));
        }

        ResultVO<List<PostDTO>> result = ResultVOUtils.success(list);
        return result;
    }

    @GetMapping("/api/getmockdata")
    public MockPostDTO getMockData() {
        MockPostDTO mockPost = new MockPostDTO();
        mockPost.setId("5");
//        Avatar avatar = new Avatar();
//        avatar.setUrl("https://randomuser.me/api/portraits/women/44.jpg");
        mockPost.setUsername("Mary");
        mockPost.setAvatar("https://randomuser.me/api/portraits/women/44.jpg");
        mockPost.setSaved(true);
        mockPost.setLocation("Santorini, Greece");
        mockPost.setTitle("Santorini");
        mockPost.setDescription("Santorini is one of the Cyclades islands in the Aegean Sea. It was devastated by a volcanic");
        mockPost.setLikes(43l);
        mockPost.setFavorites(50l);
        mockPost.setComments(3212l);
        mockPost.setTags("tag1");

        mockPost.setPreview("https://images.unsplash.com/photo-1507501336603-6e31db2be093?auto=format&fit=crop&w=800&q=80");
        mockPost.setImages("https://images.unsplash.com/photo-1507501336603-6e31db2be093?auto=format&fit=crop&w=800&q=80");
        mockPost.setArticle("mock content mock content mock content mock content.");
        return mockPost;

    }



    private PostDTO mock(Integer i) {

        PostDTO mockPost = new PostDTO();
        mockPost.setId(i.toString());
//        Avatar avatar = new Avatar();
//        avatar.setUrl("https://randomuser.me/api/portraits/women/44.jpg");
        mockPost.setUser(new UserProfileDTO("username", "https://randomuser.me/api/portraits/women/44.jpg"));
        mockPost.setSaved(true);
        mockPost.setLocation("Santorini, Greece");
        mockPost.setTitle("Santorini");
        mockPost.setDescription("Santorini is one of the Cyclades islands in the Aegean Sea. It was devastated by a volcanic");
        mockPost.setLikes(43l);
        mockPost.setFavorites(50l);
        mockPost.setComments(3212l);
        List<String> tags = Arrays.asList("tag1", "tag2");
        mockPost.setTags(tags);

        List<String> images = Arrays.asList("https://images.unsplash.com/photo-1507501336603-6e31db2be093?auto=format&fit=crop&w=800&q=80",
                "https://images.unsplash.com/photo-1507501336603-6e31db2be093?auto=format&fit=crop&w=800&q=80");
        mockPost.setImages(images);
        mockPost.setArticle("mock content mock content mock content mock content.");
        return mockPost;
    }

    private CommentDTO mockComment(Integer i) {
        CommentDTO commentDTO = new CommentDTO();
        commentDTO.setUser(new UserProfileDTO("username", "https://randomuser.me/api/portraits/women/44.jpg"));
        commentDTO.setContent("Santorini is one of the Cyclades islands in the Aegean Sea. It was devastated by a volcanic");
        commentDTO.setId(i.toString());
        Date date = new Date();
        date.setTime(System.currentTimeMillis());
        commentDTO.setCreateTime(date);
        commentDTO.setPostId("1047451209");
        return commentDTO;
    }

    @GetMapping("/api/getMockComments")
    public ResultVO<List<CommentDTO>> getMockComments() {
        ResultVO resultVO = ResultVOUtils.success();
        List<CommentDTO> commentDTOS = new ArrayList<>();
        resultVO.setData(commentDTOS);
        for (int i = 0; i < 10; i++) {
            CommentDTO commentDTO = mockComment(i);
            commentDTOS.add(commentDTO);
        }
        return resultVO;
    }
}
