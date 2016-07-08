package com.redis.test;


import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ZSetOperations.TypedTuple;

import java.util.*;


public class RedisBaseCacheDao {

    private StringRedisTemplate strRedisTemplate;

    public RedisBaseCacheDao(StringRedisTemplate strRedisTemplate) {
        this.strRedisTemplate = strRedisTemplate;
    }

    public Double getScore(String key, String member) {
        Double score = strRedisTemplate.opsForZSet().score(key, member);
        return score;
    }

    //一个属性值score累加
    public void addScore(String key, String member, Double score) {
        strRedisTemplate.opsForZSet().incrementScore(key, member, score);
    }

    public Map<String, Double> reverseRangeWithScores(String key, Long from, Long to) {
        Set<TypedTuple<String>> result = strRedisTemplate.opsForZSet()
                .reverseRangeWithScores(key, from, to);
        Map<String, Double> resultMap = new HashMap<String, Double>();
        for (TypedTuple<String> t : result) {
            resultMap.put(t.getValue(), t.getScore());
        }
        return resultMap;
    }

    public List<SortedSetModel> getListByReverseRangeWithScore(String key, Long from, Long to) {
        Set<TypedTuple<String>> result = strRedisTemplate.opsForZSet().reverseRangeWithScores(key,
                from, to);
        List<SortedSetModel> list = new ArrayList<SortedSetModel>();
        Long rank = from + 1;
        for (TypedTuple<String> t : result) {
            list.add(new SortedSetModel(rank, t.getValue(), t.getScore()));
            rank++;
        }
        return list;
    }

    public List<?> getListByRangeWithScore(String key, Long from, Long to) {
        Set<TypedTuple<String>> result = strRedisTemplate.opsForZSet().rangeWithScores(key,
                from, to);
        List<SortedSetModel> list = new ArrayList<SortedSetModel>();
        Long rank = from + 1;
        for (TypedTuple<String> t : result) {
            list.add(new SortedSetModel(rank, t.getValue(), t.getScore()));
            rank++;
        }
        return list;
    }


    public void deleteScore(String key, String member) {
        strRedisTemplate.opsForZSet().remove(key, member);
    }


    public void addStrValue(String key, String value) {
        strRedisTemplate.opsForValue().set(key, value);
    }


    public String getStrValue(String key) {
        return strRedisTemplate.opsForValue().get(key);
    }


    public void incStrValue(String key, Long value) {
        strRedisTemplate.opsForValue().increment(key, value);
    }


    //按顺序获取某一个数据范围的成员
    public List<String> getMembersWithScore(String key, Double from, Double to) {
        Set<String> result = strRedisTemplate.opsForZSet().rangeByScore(key, from, to);
        if (result == null) {
            return null;
        }

        List<String> list = new ArrayList<String>();
        list.addAll(result);
        return list;
    }

    //只拿规定的前几个
    public Map<String, Double> getTopMembers(String key, Long topNum) {
        Set<TypedTuple<String>> result = strRedisTemplate.opsForZSet()
                .rangeWithScores(key, 0l, topNum - 1);
        Map<String, Double> resultMap = new HashMap<String, Double>();
        if (result != null)
            for (TypedTuple<String> t : result) {
                resultMap.put(t.getValue(), t.getScore());
            }
        return resultMap;
    }


    //统计在某一个数据范围内数据的个数
    public Long count(String key, Double from, Double to) {
        return strRedisTemplate.opsForZSet().count(key, from, to);
    }


    public void setScore(String key, String member, Double score) {
        strRedisTemplate.opsForZSet().add(key, member, score);
    }
}
