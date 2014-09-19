package com.yotayota.hearthstoneapp.database;


public class Card {

    public int getId() {
        return mId;
    }

    public void setId(int id) {
        mId = id;
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }

    public String getType() {
        return mType;
    }

    public void setType(String type) {
        mType = type;
    }

    public int getDurability() {
        return mDurability;
    }

    public void setDurability(int durability) {
        mDurability = durability;
    }

    public String getRace() {
        return mRace;
    }

    public void setRace(String race) {
        mRace = race;
    }

    public int getElite() {
        return mElite;
    }

    public void setElite(int elite) {
        mElite = elite;
    }

    public String getQuality() {
        return mQuality;
    }

    public void setQuality(String quality) {
        mQuality = quality;
    }

    public String getHSClass() {
        return mHSClass;
    }

    public void setHSClass(String hsClass) {
        mHSClass = hsClass;
    }

    public int getCost() {
        return mCost;
    }

    public void setCost(int cost) {
        mCost = cost;
    }

    public int getAttack() {
        return mAttack;
    }

    public void setAttack(int attack) {
        mAttack = attack;
    }

    public int getHealth() {
        return mHealth;
    }

    public void setHealth(int health) {
        mHealth = health;
    }

    public String getDescription() {
        return mDescription;
    }

    public void setDescription(String description) {
        mDescription = description;
    }

    public String getFlavour() {
        return mFlavour;
    }

    public void setFlavour(String flavour) {
        mFlavour = flavour;
    }

    public String getFaction() {
        return mFaction;
    }

    public void setFaction(String faction) {
        mFaction = faction;
    }

    public String getImgUrl() {
        return mImgUrl;
    }

    public void setImgUrl(String imgUrl) {
        mImgUrl = imgUrl;
    }

    private int mId = -1;
    private String mName = "Bacon";
    private String mType;
    private int mDurability;
    private String mRace;
    private int mElite;
    private String mQuality;
    private String mHSClass;
    private int mCost;
    private int mAttack;
    private int mHealth;
    private String mDescription;
    private String mFlavour;
    private String mFaction;
    private String mImgUrl;


    // Called by ArrayAdapter
    @Override
    public String toString() {
        return mId + " " + mName;
    }
}