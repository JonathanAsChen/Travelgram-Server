package com.wonderfour.server.controller;

import com.alibaba.fastjson.JSONObject;
import com.wonderfour.server.VO.ResultVO;
import com.wonderfour.server.entity.Avatar;
import com.wonderfour.server.entity.Post;
import com.wonderfour.server.entity.User;
import com.wonderfour.server.service.UserService;
import org.bson.types.Binary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/posts")
public class PostController {

    @Autowired
    private MongoTemplate mongoTemplate;

    public JSONObject uploadImage(@RequestParam(value = "image") MultipartFile file) {
        if (file.isEmpty()) {
            JSONObject result = new JSONObject();
            result.put("code", 200);
            result.put("message", "please choose an image to upload.");
            return result;
        }

        // 返回的 JSON 对象，这种类可自己封装
        JSONObject jsonResult = new JSONObject();
        ;
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
}
