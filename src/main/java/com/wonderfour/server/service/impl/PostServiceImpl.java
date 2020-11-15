package com.wonderfour.server.service.impl;

import com.wonderfour.server.DTO.PostDTO;
import com.wonderfour.server.DTO.UserProfileDTO;
import com.wonderfour.server.dao.FavoriteMapper;
import com.wonderfour.server.dao.LikesMapper;
import com.wonderfour.server.dao.PostMapper;
import com.wonderfour.server.dao.PostTagMapper;
import com.wonderfour.server.entity.*;
import com.wonderfour.server.service.ImageService;
import com.wonderfour.server.service.PostService;
import com.wonderfour.server.service.TagService;
import com.wonderfour.server.service.UserService;
import com.wonderfour.server.utils.KeyUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Yifan Chen
 * @version 1.0.0
 * @since 10/11/2020 10:04 AM
 */
@Service
public class PostServiceImpl implements PostService {

    @Autowired
    private PostMapper postMapper;

    @Autowired
    private PostTagMapper postTagMapper;

    @Autowired
    private FavoriteMapper favoriteMapper;

    @Autowired
    private LikesMapper likesMapper;

    @Autowired
    private UserService userService;

    @Autowired
    private TagService tagService;

    @Autowired
    private ImageService imageService;

    @Override
    public List<Post> findFavoriteByUserName(String username) {
        return null;
    }

    @Override
    public String save(Post post) {
        String postId = KeyUtil.genUniqueKey();
        post.setId(postId);

        int result = postMapper.insert(post);
        return postId;
    }

    @Override
    public int update(Post post) {
        int result = postMapper.updateByPrimaryKey(post);
        return result;
    }

    @Override
    public void deleteById(String id) {
        postMapper.deleteByPrimaryKey(id);
    }

    @Override
    public boolean isSavedByUser(String username, String postId) {
        UserInfo userInfo = userService.findByUsername(username);

        FavoriteExample favoriteExample = new FavoriteExample();
        favoriteExample.createCriteria().andPostIdEqualTo(postId).andUserIdEqualTo(userInfo.getId());

        return favoriteMapper.countByExample(favoriteExample) > 0;
    }

    @Override
    public boolean isSavedByUser(UserInfo userInfo, String postId) {
        if (userInfo == null) {
            return false;
        }

        FavoriteExample favoriteExample = new FavoriteExample();
        favoriteExample.createCriteria().andPostIdEqualTo(postId).andUserIdEqualTo(userInfo.getId());

        return favoriteMapper.countByExample(favoriteExample) > 0;
    }

    @Override
    public boolean isLikedByUser(String username, String postId) {
        UserInfo userInfo = userService.findByUsername(username);

        LikesExample likesExample = new LikesExample();
        likesExample.createCriteria().andPostIdEqualTo(postId).andUserIdEqualTo(userInfo.getId());

        return likesMapper.countByExample(likesExample) > 0;
    }

    @Override
    public boolean isLikedByUser(UserInfo userInfo, String postId) {
        if (userInfo == null) {
            return false;
        }
        LikesExample likesExample = new LikesExample();
        likesExample.createCriteria().andPostIdEqualTo(postId).andUserIdEqualTo(userInfo.getId());

        return likesMapper.countByExample(likesExample) > 0;
    }

    @Override
    public List<Post> findByAuthor(String author) {
        UserInfo userInfo = userService.findByUsername(author);
        PostExample example = new PostExample();
        example.createCriteria().andUserIdEqualTo(userInfo.getId());
        List<Post> posts = postMapper.selectByExample(example);
        return posts;
    }

    @Override
    public List<Post> findByTag(String tag) {
        Tag tagObject = tagService.findByTagName(tag);
        PostTagExample postTagExample = new PostTagExample();
        postTagExample.createCriteria().andTagIdEqualTo(tagObject.getId());
        List<PostTag> postTagList = postTagMapper.selectByExample(postTagExample);


        PostExample postExample = new PostExample();
        postExample.createCriteria().andIdIn(postTagList.stream().map(PostTag::getPostId).collect(Collectors.toList()));


        return postMapper.selectByExample(postExample);
    }

    @Override
    public Post findById(String id) {
        return postMapper.selectByPrimaryKey(id);
    }

    @Override
    public PostDTO convert2DTO(UserInfo user, UserInfo author, Post post) {
        UserProfileDTO userProfileDTO = new UserProfileDTO();
        BeanUtils.copyProperties(author, userProfileDTO);

        PostDTO postDTO = new PostDTO();
        BeanUtils.copyProperties(post, postDTO);
        postDTO.setUser(userProfileDTO);
        postDTO.setSaved(isSavedByUser(user, post.getId()));
        postDTO.setLiked(isLikedByUser(user, post.getId()));

        postDTO.setImages(imageService.findUrlByPostId(post.getId()));

        postDTO.setLikes(countLikes(post.getId()));
        postDTO.setFavorites(countFavorites(post.getId()));

        postDTO.setTags(tagService.findTagNameListByPostId(post.getId()));

        // TODO: comments


        return postDTO;
    }

    @Override
    public PostDTO convert2DTO(UserInfo user, Post post) {
        UserInfo author = userService.findById(post.getUserId());
        return convert2DTO(user, author, post);
    }


    @Override
    public Long countFavorites(String postId) {
        FavoriteExample favoriteExample = new FavoriteExample();
        favoriteExample.createCriteria().andPostIdEqualTo(postId);
        Long result = favoriteMapper.countByExample(favoriteExample);
        return result;
    }

    @Override
    public Long countLikes(String postId) {
        LikesExample likesExample = new LikesExample();
        likesExample.createCriteria().andPostIdEqualTo(postId);
        Long result = likesMapper.countByExample(likesExample);
        return result;
    }

    @Override
    public List<Post> searchByContent(String content) {
        PostExample postExample = new PostExample();
        postExample.createCriteria().andArticleLike("%" + content + "%");
        List<Post> posts = postMapper.selectByExample(postExample);
        return posts;
    }

    @Override
    public List<Post> searchByTagAndArticle(String tag, String content) {
        Tag tagObject = tagService.findByTagName(tag);
        PostTagExample postTagExample = new PostTagExample();
        postTagExample.createCriteria().andTagIdEqualTo(tagObject.getId());
        List<PostTag> postTagList = postTagMapper.selectByExample(postTagExample);
        PostExample postExample = new PostExample();
        postExample.createCriteria().andIdIn(postTagList.stream().map(PostTag::getPostId).collect(Collectors.toList())).andArticleLike("%" + content + "%");
        return postMapper.selectByExample(postExample);
    }
}
