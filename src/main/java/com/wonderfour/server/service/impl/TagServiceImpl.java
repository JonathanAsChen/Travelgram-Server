package com.wonderfour.server.service.impl;

import com.wonderfour.server.dao.PostTagMapper;
import com.wonderfour.server.dao.TagMapper;
import com.wonderfour.server.entity.PostTag;
import com.wonderfour.server.entity.PostTagExample;
import com.wonderfour.server.entity.Tag;
import com.wonderfour.server.entity.TagExample;
import com.wonderfour.server.enums.ResultEnum;
import com.wonderfour.server.exception.TravelgramException;
import com.wonderfour.server.service.TagService;
import com.wonderfour.server.utils.KeyUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;

import java.sql.SQLDataException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Yifan Chen
 * @version 1.0.0
 * @since 10/24/2020 5:18 PM
 */
@Service
public class TagServiceImpl implements TagService {

    @Autowired
    private TagMapper tagMapper;

    @Autowired
    private PostTagMapper postTagMapper;


    @Override
    public Tag findByTagName(String tagName) {
        TagExample tagExample = new TagExample();
        tagExample.createCriteria().andNameEqualTo(tagName);
        List<Tag> tagList = tagMapper.selectByExample(tagExample);
        return tagList.isEmpty() ? null : tagList.get(0);
    }

    @Override
    public Tag findById(String id) {
        return tagMapper.selectByPrimaryKey(id);


    }

    @Override
    public String insert(String tagName) {
        Tag tag = new Tag();
        String tagId = KeyUtil.genUniqueKey();
        tag.setId(tagId);
        tag.setName(tagName);
        int result = tagMapper.insert(tag);
        return tagId;
    }

    @Override
    public List<String> findTagNameListByPostId(String postId) {
        PostTagExample postTagExample = new PostTagExample();
        postTagExample.createCriteria().andPostIdEqualTo(postId);
        List<PostTag> postTagList = postTagMapper.selectByExample(postTagExample);
        if (postTagList.isEmpty()) {
            return new ArrayList<>();
        }


        TagExample tagExample = new TagExample();
        tagExample.createCriteria().andIdIn(postTagList.stream().map(PostTag::getTagId).collect(Collectors.toList()));

        List<String> result = tagMapper.selectByExample(tagExample)
                                .stream().map(Tag::getName).collect(Collectors.toList());
        return result;
    }

    @Override
    public void savePostTagRelation(List<String> tagNames, String postId) {
        for (String tagName: tagNames) {
            Tag tag = findByTagName(tagName);
            String tagId;
            if (tag == null) {
                tagId = insert(tagName);
            } else {
                tagId = tag.getId();
            }
            PostTag postTag = new PostTag();
            postTag.setPostId(postId);
            postTag.setTagId(tagId);
            postTagMapper.insert(postTag);
        }
    }
}
