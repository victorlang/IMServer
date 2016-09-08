//created by zhang jian xin 2016 @IBM
package com.ibm.cloud.im.test;

import java.io.ByteArrayInputStream;
import java.io.ObjectInputStream;

import org.springframework.context.annotation.Bean;

import redis.clients.jedis.JedisPubSub;

public class SubScriber extends JedisPubSub {
    @Override
    public void onUnsubscribe(String channel, int subscribedChannels) {
    }

    @Override
    public void onSubscribe(String channel, int subscribedChannels) {
    }

    @Override
    public void onPUnsubscribe(String pattern, int subscribedChannels) {
    }

    @Override
    public void onPSubscribe(String pattern, int subscribedChannels) {
    }

    @Override
    public void onPMessage(String pattern, String channel,
        String message) {
    }

    @Override
    public void onMessage(String channel, String message) {
      try {
        ByteArrayInputStream bis = new ByteArrayInputStream(
            message.getBytes("ISO-8859-1"));//此处指定字符集将字符串编码成字节数组，此处的字符集需要与发布时的字符集保持一致
        ObjectInputStream ois = new ObjectInputStream(bis);
        Bean bean = (Bean) ois.readObject();
        System.out.println(bean.name());
      } catch (Exception e) {
        e.printStackTrace();
      } finally {

      }
    }
}
