/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aws.codestar.projecttemplates.pojo;

import java.util.List;

/**
 * 个人主页数据
 *
 * @author lian
 */
public class Personnel {

	/**
	 * id
	 */
	private String id;

	/**
	 * 跟随者数量
	 */
	private String followerCount;

	/**
	 * 跟踪者数量
	 */
	private String followingCount;

	/**
	 *
	 */
	private String universallyUniqueIdentifier;

	/**
	 * 发布的文章
	 */
	private List<Storie> userStory;

	/**
	 * 背景
	 */
	private String coverImgUrl;

	/**
	 * 头像
	 */
	private String profileImgUrl;

	/**
	 * 个人简介
	 */
	private String profileText;

	/**
	 * 用户名
	 */
	private String nickname;

	/**
	 * 发布文章数量
	 */
	private String userStoryCount;

	/**
	 * 跟随者数据
	 */
	private List<PersonnelFollo> followers;

	/**
	 * 跟踪者数据
	 */
	private List<PersonnelFollo> following;

	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * @return the followerCount
	 */
	public String getFollowerCount() {
		return followerCount;
	}

	/**
	 * @param followerCount the followerCount to set
	 */
	public void setFollowerCount(String followerCount) {
		this.followerCount = followerCount;
	}

	/**
	 * @return the followingCount
	 */
	public String getFollowingCount() {
		return followingCount;
	}

	/**
	 * @param followingCount the followingCount to set
	 */
	public void setFollowingCount(String followingCount) {
		this.followingCount = followingCount;
	}

	/**
	 * @return the universallyUniqueIdentifier
	 */
	public String getUniversallyUniqueIdentifier() {
		return universallyUniqueIdentifier;
	}

	/**
	 * @param universallyUniqueIdentifier the universallyUniqueIdentifier to set
	 */
	public void setUniversallyUniqueIdentifier(String universallyUniqueIdentifier) {
		this.universallyUniqueIdentifier = universallyUniqueIdentifier;
	}

	/**
	 * @return the userStory
	 */
	public List<Storie> getUserStory() {
		return userStory;
	}

	/**
	 * @param userStory the userStory to set
	 */
	public void setUserStory(List<Storie> userStory) {
		this.userStory = userStory;
	}

	/**
	 * @return the coverImgUrl
	 */
	public String getCoverImgUrl() {
		return coverImgUrl;
	}

	/**
	 * @param coverImgUrl the coverImgUrl to set
	 */
	public void setCoverImgUrl(String coverImgUrl) {
		this.coverImgUrl = coverImgUrl;
	}

	/**
	 * @return the profileImgUrl
	 */
	public String getProfileImgUrl() {
		return profileImgUrl;
	}

	/**
	 * @param profileImgUrl the profileImgUrl to set
	 */
	public void setProfileImgUrl(String profileImgUrl) {
		this.profileImgUrl = profileImgUrl;
	}

	/**
	 * @return the profileText
	 */
	public String getProfileText() {
		return profileText;
	}

	/**
	 * @param profileText the profileText to set
	 */
	public void setProfileText(String profileText) {
		this.profileText = profileText;
	}

	/**
	 * @return the nickname
	 */
	public String getNickname() {
		return nickname;
	}

	/**
	 * @param nickname the nickname to set
	 */
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	/**
	 * @return the userStoryCount
	 */
	public String getUserStoryCount() {
		return userStoryCount;
	}

	/**
	 * @param userStoryCount the userStoryCount to set
	 */
	public void setUserStoryCount(String userStoryCount) {
		this.userStoryCount = userStoryCount;
	}

	/**
	 * @return the followers
	 */
	public List<PersonnelFollo> getFollowers() {
		return followers;
	}

	/**
	 * @param followers the followers to set
	 */
	public void setFollowers(List<PersonnelFollo> followers) {
		this.followers = followers;
	}

	/**
	 * @return the following
	 */
	public List<PersonnelFollo> getFollowing() {
		return following;
	}

	/**
	 * @param following the following to set
	 */
	public void setFollowing(List<PersonnelFollo> following) {
		this.following = following;
	}
	
}
