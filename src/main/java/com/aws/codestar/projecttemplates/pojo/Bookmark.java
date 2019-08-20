/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aws.codestar.projecttemplates.pojo;

/**
 * 收藏
 *
 * @author lian
 */
public class Bookmark {

	private String id;

	/**
	 * 收藏时间
	 */
	private String markDate;

	/**
	 *
	 */
	private String universallyUniqueIdentifier;

	/**
	 * 收藏者
	 */
	private Personnel mackeeper;

	/**
	 * 收藏文章
	 */
	private Storie targetStory;

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
	 * @return the markDate
	 */
	public String getMarkDate() {
		return markDate;
	}

	/**
	 * @param markDate the markDate to set
	 */
	public void setMarkDate(String markDate) {
		this.markDate = markDate;
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
	 * @return the mackeeper
	 */
	public Personnel getMackeeper() {
		return mackeeper;
	}

	/**
	 * @param mackeeper the mackeeper to set
	 */
	public void setMackeeper(Personnel mackeeper) {
		this.mackeeper = mackeeper;
	}

	/**
	 * @return the targetStory
	 */
	public Storie getTargetStory() {
		return targetStory;
	}

	/**
	 * @param targetStory the targetStory to set
	 */
	public void setTargetStory(Storie targetStory) {
		this.targetStory = targetStory;
	}
}
