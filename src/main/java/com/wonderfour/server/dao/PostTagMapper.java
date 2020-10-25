package com.wonderfour.server.dao;

import com.wonderfour.server.entity.PostTag;
import com.wonderfour.server.entity.PostTagExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface PostTagMapper {
    long countByExample(PostTagExample example);

    int deleteByExample(PostTagExample example);

    int deleteByPrimaryKey(Integer ptId);

    int insert(PostTag record);

    int insertSelective(PostTag record);

    List<PostTag> selectByExample(PostTagExample example);

    PostTag selectByPrimaryKey(Integer ptId);

    int updateByExampleSelective(@Param("record") PostTag record, @Param("example") PostTagExample example);

    int updateByExample(@Param("record") PostTag record, @Param("example") PostTagExample example);

    int updateByPrimaryKeySelective(PostTag record);

    int updateByPrimaryKey(PostTag record);
}
