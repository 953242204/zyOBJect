/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aws.codestar.projecttemplates.pojo;

import java.util.List;

/**
 * 邮箱登入返回数据
 *
 * @author lian
 */
public class EmailUser {

	private String id;

	/**
	 *
	 */
	private String googleId;

	/**
	 * 个人简介
	 */
	private String profileText;

	/**
	 * 名
	 */
	private String firstname;

	/**
	 * 脸谱网id
	 */
	private String facebookId;

	/**
	 *
	 */
	private String gender;

	/**
	 *
	 */
	private String lineId;

	/**
	 *
	 */
	private String birth;

	/**
	 *
	 */
	private String universallyUniqueIdentifier;

	/**
	 * 创建时间
	 */
	private String openedAt;

	/**
	 * 姓
	 */
	private String lastname;

	/**
	 * 背景
	 */
	private String coverImgUrl;

	/**
	 * 头像
	 */
	private String profileImgUrl;

	/**
	 * 用户名
	 */
	private String nickname;

	/**
	 *
	 */
	private String storeName;

	/**
	 * 邮箱
	 */
	private String email;

	/**
	 * 发布的文章
	 */
	private List<Storie> userStory;

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
	 * @return the googleId
	 */
	public String getGoogleId() {
		return googleId;
	}

	/**
	 * @param googleId the googleId to set
	 */
	public void setGoogleId(String googleId) {
		this.googleId = googleId;
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
	 * @return the firstname
	 */
	public String getFirstname() {
		return firstname;
	}

	/**
	 * @param firstname the firstname to set
	 */
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	/**
	 * @return the facebookId
	 */
	public String getFacebookId() {
		return facebookId;
	}

	/**
	 * @param facebookId the facebookId to set
	 */
	public void setFacebookId(String facebookId) {
		this.facebookId = facebookId;
	}

	/**
	 * @return the gender
	 */
	public String getGender() {
		return gender;
	}

	/**
	 * @param gender the gender to set
	 */
	public void setGender(String gender) {
		this.gender = gender;
	}

	/**
	 * @return the lineId
	 */
	public String getLineId() {
		return lineId;
	}

	/**
	 * @param lineId the lineId to set
	 */
	public void setLineId(String lineId) {
		this.lineId = lineId;
	}

	/**
	 * @return the birth
	 */
	public String getBirth() {
		return birth;
	}

	/**
	 * @param birth the birth to set
	 */
	public void setBirth(String birth) {
		this.birth = birth;
	}

	/**
	 * @return the universallyUniqueIdentifier
	 */
	public String getUniversallyUniqueIdentifier() {
		return universallyUniqueIdentifier;
	}

	/**
	 * @param universallyUniqueIdentifier the universallyUniqueIdentifier to
	 * set
	 */
	public void setUniversallyUniqueIdentifier(String universallyUniqueIdentifier) {
		this.universallyUniqueIdentifier = universallyUniqueIdentifier;
	}

	/**
	 * @return the openedAt
	 */
	public String getOpenedAt() {
		return openedAt;
	}

	/**
	 * @param openedAt the openedAt to set
	 */
	public void setOpenedAt(String openedAt) {
		this.openedAt = openedAt;
	}

	/**
	 * @return the lastname
	 */
	public String getLastname() {
		return lastname;
	}

	/**
	 * @param lastname the lastname to set
	 */
	public void setLastname(String lastname) {
		this.lastname = lastname;
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
	 * @return the storeName
	 */
	public String getStoreName() {
		return storeName;
	}

	/**
	 * @param storeName the storeName to set
	 */
	public void setStoreName(String storeName) {
		this.storeName = storeName;
	}

	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
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
}
