package com.shangchao.service;

import com.alibaba.fastjson.JSONObject;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;
import com.shangchao.entity.Topic;

import java.util.List;

public interface TopicService {

  DeleteResult deleteTopicById(String topicId);

  Topic saveOrUpdateTopic(JSONObject json);

  List<Topic> getAllTopic();

  List<Topic> getTopicWithProduct();

  JSONObject getTopicWithProduct(String topicId);

  UpdateResult setTopic(JSONObject json);
}
