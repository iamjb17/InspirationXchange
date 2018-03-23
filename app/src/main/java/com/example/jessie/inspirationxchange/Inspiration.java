package com.example.jessie.inspirationxchange;

/**
 * Created by Jessie on 1/31/2018.
 */

public class Inspiration {

    private String mTitle;
    private String mBody;
    //private List<String> mCategoriesChose;
    private String mAuthor;

    // CategoriesChose
    private boolean mAttitude;
    private boolean mCharacter;
    private boolean mCourage;
    private boolean mHappiness;
    private boolean mLove;
    private boolean mMotivational;
    private boolean mOpportunity;
    private boolean mPerseverance;

    public Inspiration() {
    }

    public Inspiration(String title, String body, String author, boolean attitude,
                       boolean character, boolean courage, boolean happiness, boolean love,
                       boolean motivational, boolean opportunity, boolean perseverance) {
        mTitle = title;
        mBody = body;
        mAuthor = author;
        mAttitude = attitude;
        mCharacter = character;
        mCourage = courage;
        mHappiness = happiness;
        mLove = love;
        mMotivational = motivational;
        mOpportunity = opportunity;
        mPerseverance = perseverance;
    }

    public boolean isAttitude() {
        return mAttitude;
    }

    public void setAttitude(boolean attitude) {
        mAttitude = attitude;
    }

    public boolean isCharacter() {
        return mCharacter;
    }

    public void setCharacter(boolean character) {
        mCharacter = character;
    }

    public boolean isCourage() {
        return mCourage;
    }

    public void setCourage(boolean courage) {
        mCourage = courage;
    }

    public boolean isHappiness() {
        return mHappiness;
    }

    public void setHappiness(boolean happiness) {
        mHappiness = happiness;
    }

    public boolean isLove() {
        return mLove;
    }

    public void setLove(boolean love) {
        mLove = love;
    }

    public boolean isMotivational() {
        return mMotivational;
    }

    public void setMotivational(boolean motivational) {
        mMotivational = motivational;
    }

    public boolean isOpportunity() {
        return mOpportunity;
    }

    public void setOpportunity(boolean opportunity) {
        mOpportunity = opportunity;
    }

    public boolean isPerseverance() {
        return mPerseverance;
    }

    public void setPerseverance(boolean perseverance) {
        mPerseverance = perseverance;
    }

    //    public Inspiration(String title, String body, List categories) {
//        mTitle = title;
//        mBody = body;
//        mCategoriesChose = categories;
//    }
//
//    public Inspiration(String title, String body, List<String> categoriesChose, String author) {
//        mTitle = title;
//        mBody = body;
//        mCategoriesChose = categoriesChose;
//        mAuthor = author;
//    }

    public String getAuthor() {
        return mAuthor;
    }

    public void setAuthor(String author) {
        mAuthor = author;
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String title) {
        mTitle = title;
    }

    public String getBody() {
        return mBody;
    }

    public void setBody(String body) {
        mBody = body;
    }

    @Override
    public String toString() {
        return "Inspiration{" +
                "mTitle='" + mTitle + '\'' +
                ", mBody='" + mBody + '\'' +
                ", mAuthor='" + mAuthor + '\'' +
                ", mAttitude=" + mAttitude +
                ", mCharacter=" + mCharacter +
                ", mCourage=" + mCourage +
                ", mHappiness=" + mHappiness +
                ", mLove=" + mLove +
                ", mMotivational=" + mMotivational +
                ", mOpportunity=" + mOpportunity +
                ", mPerseverance=" + mPerseverance +
                '}';
    }

    //    public List<String> getCategoriesChose() {
//        return mCategoriesChose;
//    }
//
//    public void setCategoriesChose(List categoriesChose) {
//        mCategoriesChose = categoriesChose;
//    }
//
//    @Override
//    public String toString() {
//        return "Inspiration{" +
//                "mTitle='" + mTitle + '\'' +
//                ", mBody='" + mBody + '\'' +
//                ", mCategoriesChose=" + mCategoriesChose.toString() +
//                '}';
//    }

}
