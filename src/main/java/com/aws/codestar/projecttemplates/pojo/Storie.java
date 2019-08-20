/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aws.codestar.projecttemplates.pojo;

import java.util.List;

/**
 *
 * 文章数据
 *
 * @author lian
 */
public class Storie {

	private String id;

	/**
	 * 文章发布人
	 */
	private Personnel author;

	/**
	 * 发布时间
	 */
	private String postedAt;

	/**
	 * 留言
	 */
	private List<StoryComment> storyComment;

	/**
	 * 留言条数
	 */
	private String storyCommentCount;

	/**
	 * 图片
	 */
	private List<StoryImage> storyImage;

	/**
	 * 内容
	 */
	private String content;

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
	 * @return the author
	 */
	public Personnel getAuthor() {
		return author;
	}

	/**
	 * @param author the author to set
	 */
	public void setAuthor(Personnel author) {
		this.author = author;
	}

	/**
	 * @return the postedAt
	 */
	public String getPostedAt() {
		return postedAt;
	}

	/**
	 * @param postedAt the postedAt to set
	 */
	public void setPostedAt(String postedAt) {
		this.postedAt = postedAt;
	}

	/**
	 * @return the storyComment
	 */
	public List<StoryComment> getStoryComment() {
		return storyComment;
	}

	/**
	 * @param storyComment the storyComment to set
	 */
	public void setStoryComment(List<StoryComment> storyComment) {
		this.storyComment = storyComment;
	}

	/**
	 * @return the storyImage
	 */
	public List<StoryImage> getStoryImage() {
		return storyImage;
	}

	/**
	 * @param storyImage the storyImage to set
	 */
	public void setStoryImage(List<StoryImage> storyImage) {
		this.storyImage = storyImage;
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
	 * @return the storyCommentCount
	 */
	public String getStoryCommentCount() {
		return storyCommentCount;
	}

	/**
	 * @param storyCommentCount the storyCommentCount to set
	 */
	public void setStoryCommentCount(String storyCommentCount) {
		this.storyCommentCount = storyCommentCount;
	}

}
