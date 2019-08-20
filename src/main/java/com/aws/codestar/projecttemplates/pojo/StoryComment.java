/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aws.codestar.projecttemplates.pojo;

/**
 * 留言数据 api
 *
 * @author lian
 */
public class StoryComment {

	/**
	 * 留言用户
	 */
	private String who;

	/**
	 * 留言时间
	 */
	private String repliedAt;

	/**
	 * 留言者id
	 */
	private String whoId;

	/**
	 * 留言id
	 */
	private String id;

	/**
	 *
	 */
	private String universallyUniqueIdentifier;

	/**
	 * 留言内容
	 */
	private String content;

	/**
	 * @return the repliedAt
	 */
	public String getRepliedAt() {
		return repliedAt;
	}

	/**
	 * @param repliedAt the repliedAt to set
	 */
	public void setRepliedAt(String repliedAt) {
		this.repliedAt = repliedAt;
	}

	/**
	 * @return the whoId
	 */
	public String getWhoId() {
		return whoId;
	}

	/**
	 * @param whoId the whoId to set
	 */
	public void setWhoId(String whoId) {
		this.whoId = whoId;
	}

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
	 * @return the content
	 */
	public String getContent() {
		return content;
	}

	/**
	 * @param content the content to set
	 */
	public void setContent(String content) {
		this.content = content;
	}

	/**
	 * @return the who
	 */
	public String getWho() {
		return who;
	}

	/**
	 * @param who the who to set
	 */
	public void setWho(String who) {
		this.who = who;
	}

}
