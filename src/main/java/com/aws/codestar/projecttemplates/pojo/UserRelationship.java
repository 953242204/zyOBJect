/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aws.codestar.projecttemplates.pojo;

/**
 * 好友申请 api
 *
 * @author lian
 */
public class UserRelationship {

	/**
	 * 状态 0-发送申请
	 */
	private String status;

	/**
	 * 申请时间
	 */
	private String relatingAt;

	/**
	 * 同意时间
	 */
	private String relatedAt;

	/**
	 * 申请人
	 */
	private User relatedUser;

	/**
	 * 被申请人
	 */
	private User relatingUser;

	/**
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}

	/**
	 * @param status the status to set
	 */
	public void setStatus(String status) {
		this.status = status;
	}

	/**
	 * @return the relatingAt
	 */
	public String getRelatingAt() {
		return relatingAt;
	}

	/**
	 * @param relatingAt the relatingAt to set
	 */
	public void setRelatingAt(String relatingAt) {
		this.relatingAt = relatingAt;
	}

	/**
	 * @return the relatedAt
	 */
	public String getRelatedAt() {
		return relatedAt;
	}

	/**
	 * @param relatedAt the relatedAt to set
	 */
	public void setRelatedAt(String relatedAt) {
		this.relatedAt = relatedAt;
	}

	/**
	 * @return the relatedUser
	 */
	public User getRelatedUser() {
		return relatedUser;
	}

	/**
	 * @param relatedUser the relatedUser to set
	 */
	public void setRelatedUser(User relatedUser) {
		this.relatedUser = relatedUser;
	}

	/**
	 * @return the relatingUser
	 */
	public User getRelatingUser() {
		return relatingUser;
	}

	/**
	 * @param relatingUser the relatingUser to set
	 */
	public void setRelatingUser(User relatingUser) {
		this.relatingUser = relatingUser;
	}
}
