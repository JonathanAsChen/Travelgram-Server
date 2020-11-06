package com.wonderfour.server.service.impl;

import com.wonderfour.server.dao.CommentMapper;
import com.wonderfour.server.entity.Comment;
import com.wonderfour.server.entity.CommentExample;
import com.wonderfour.server.service.CommentService;
import com.wonderfour.server.utils.KeyUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Yifan Chen
 * @version 1.0.0
 * @since 10/31/2020 3:12 PM
 */
@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private CommentMapper commentMapper;


    @Override
    public List<Comment> getByPostId(String postId) {
        CommentExample commentExample = new CommentExample();
        commentExample.createCriteria().andPostIdEqualTo(postId);
        List<Comment> list = commentMapper.selectByExample(commentExample);
        return list;
    }

    @Override
    public Comment getById(String id) {
        return commentMapper.selectByPrimaryKey(id);
    }

    @Override
    public String commentPost(Comment comment) {
        String commentId = KeyUtil.genUniqueKey();
        comment.setId(commentId);

        commentMapper.insert(comment);
        return commentId;
    }
}
