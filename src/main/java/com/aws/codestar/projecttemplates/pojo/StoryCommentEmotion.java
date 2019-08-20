/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aws.codestar.projecttemplates.pojo;

/**
 * 留言的心情 api
 *
 * @author lian
 */
public class StoryCommentEmotion {

	private String id;

	/**
	 * 心情
	 */
	private Emotion emotion;

	/**
	 * 留言用户
	 */
	private Personnel who;

	/**
	 * 留言
	 */
	private StoryComment storyComment;

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
	 * @return the emotion
	 */
	public Emotion getEmotion() {
		return emotion;
	}

	/**
	 * @param emotion the emotion to set
	 */
	public void setEmotion(Emotion emotion) {
		this.emotion = emotion;
	}

	/**
	 * @return the who
	 */
	public Personnel getWho() {
		return who;
	}

	/**
	 * @param who the who to set
	 */
	public void setWho(Personnel who) {
		this.who = who;
	}

	/**
	 * @return the storyComment
	 */
	public StoryComment getStoryComment() {
		return storyComment;
	}

	/**
	 * @param storyComment the storyComment to set
	 */
	public void setStoryComment(StoryComment storyComment) {
		this.storyComment = storyComment;
	}

}
