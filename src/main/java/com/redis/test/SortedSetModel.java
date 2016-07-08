package com.redis.test;

public class SortedSetModel implements Comparable<SortedSetModel> {

    private Long rank;
    private String member;
    private Double score;
    
    public SortedSetModel() {
        super();
    }

    public SortedSetModel(Long rank, String member, Double score) {
        super();
        this.rank = rank;
        this.member = member;
        this.score = score;
    }

    public Long getRank() {
        return rank;
    }

    public void setRank(Long rank) {
        this.rank = rank;
    }

    public String getMember() {
        return member;
    }

    public void setMember(String member) {
        this.member = member;
    }

    public Double getScore() {
        return score;
    }

    public void setScore(Double score) {
        this.score = score;
    }

    @Override
    public String toString() {
        return "SortedSetModel [rank=" + rank + ", member=" + member + ", score=" + score + "]";
    }

   // @Override
    public int compareTo(SortedSetModel o) {
        return o.getScore().compareTo(this.score);
    }

}
