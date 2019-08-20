/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aws.codestar.projecttemplates.pojo;

/**
 * 文章的心情
 *
 * @author lian
 */
public class StoryEmotion {

	/**
	 *
	 */
	private String id;

	/**
	 * 发布者
	 */
	private Personnel who;

	/**
	 * 心情
	 */
	private Emotion emotion;

	/**
	 * 文章
	 */
	private Storie story;

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
	 * @return the story
	 */
	public Storie getStory() {
		return story;
	}

	/**
	 * @param story the story to set
	 */
	public void setStory(Storie story) {
		this.story = story;
	}

}
