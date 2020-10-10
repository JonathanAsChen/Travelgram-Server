package com.wonderfour.server.controller;

import com.wonderfour.server.DTO.PostDTO;
import com.wonderfour.server.DTO.UserProfileDTO;
import com.wonderfour.server.VO.ResultVO;
import com.wonderfour.server.entity.Article;
import com.wonderfour.server.entity.Avatar;
import com.wonderfour.server.entity.Post;
import com.wonderfour.server.utils.ResultVOUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Arrays;
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
//        mockPost.setPreview("https://images.unsplash.com/photo-1507501336603-6e31db2be093?auto=format&fit=crop&w=800&q=80");
//        List<String> images = Arrays.asList("https://images.unsplash.com/photo-1507501336603-6e31db2be093?auto=format&fit=crop&w=800&q=80",
//                "https://images.unsplash.com/photo-1507501336603-6e31db2be093?auto=format&fit=crop&w=800&q=80");
//        mockPost.setImages(images);
//        mockPost.setArticle(new Article("content"));
//    }


    @GetMapping("/api/getmockdata")
    public ResultVO<List<PostDTO>> getMockData() {

        List<PostDTO> list = new ArrayList<>();
        for (int i = 1; i < 3; i++) {
            list.add(mock(i));
        }

        ResultVO<List<PostDTO>> result = ResultVOUtils.success(list);
        return result;
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

        mockPost.setPreview("https://images.unsplash.com/photo-1507501336603-6e31db2be093?auto=format&fit=crop&w=800&q=80");
        List<String> images = Arrays.asList("https://images.unsplash.com/photo-1507501336603-6e31db2be093?auto=format&fit=crop&w=800&q=80",
                "https://images.unsplash.com/photo-1507501336603-6e31db2be093?auto=format&fit=crop&w=800&q=80");
        mockPost.setImages(images);
        mockPost.setArticle("mock content mock content mock content mock content.");
        return mockPost;
    }
}
