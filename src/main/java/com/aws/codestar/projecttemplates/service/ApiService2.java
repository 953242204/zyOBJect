package com.aws.codestar.projecttemplates.service;

import java.io.IOException;
import java.io.StringWriter;
import java.net.URISyntaxException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Arrays;
import javax.servlet.http.HttpSession;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Component;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import com.aws.codestar.projecttemplates.configuration.AuthHttpClient;
import com.aws.codestar.projecttemplates.pojo.EmailUser;
import com.aws.codestar.projecttemplates.pojo.Personnel;
import com.aws.codestar.projecttemplates.pojo.Storie;
import com.aws.codestar.projecttemplates.pojo.StoryComment;
import com.aws.codestar.projecttemplates.pojo.StoryImage;
import com.aws.codestar.projecttemplates.pojo.ThirdPartyUser;
import com.aws.codestar.projecttemplates.pojo.User;
import java.util.List;

@Component
public class ApiService2 {

	public Document getStory(HttpSession httpSession) throws ParserConfigurationException, ClientProtocolException, IOException, TransformerConfigurationException, TransformerException {
		HttpGet httpGet = new AuthHttpClient().bulidHttpGet("stories/");

		DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
		Document document = documentBuilder.newDocument();

		CloseableHttpResponse closeableHttpResponse = HttpClients.createDefault().execute(httpGet);
		try {
			HttpEntity httpEntity = closeableHttpResponse.getEntity();
			if (httpEntity != null) {
				String stringOfEntity = EntityUtils.toString(httpEntity, "UTF-8");
				JSONObject jSONObjectOfEntity = new JSONObject(stringOfEntity);
				String stringOfEmbedded = jSONObjectOfEntity.get("_embedded").toString();

				Element elementOfDocument = document.createElement("document");
				document.appendChild(elementOfDocument);

				//將目前已登入的 user 資訊(from httpSession)寫進 document.attribute
				if (httpSession.getAttribute("me") != null) {
					elementOfDocument.setAttribute("me", httpSession.getAttribute("me").toString());
				}
				if (httpSession.getAttribute("id") != null) {
					elementOfDocument.setAttribute("id", httpSession.getAttribute("id").toString());
				}
				if (httpSession.getAttribute("personnelHref") != null) {
					elementOfDocument.setAttribute("personnelHref", httpSession.getAttribute("personnelHref").toString());
				}
				if (httpSession.getAttribute("thirdParty") != null) {
					elementOfDocument.setAttribute("thirdParty", httpSession.getAttribute("thirdParty").toString());
				}
				if (httpSession.getAttribute("nickname") != null) {
					elementOfDocument.setAttribute("nickname", httpSession.getAttribute("nickname").toString());
				}

				//rank 0
				//create stories---start
				Element elementOfStories = document.createElement("stories");
//				elementOfStories.setAttribute("status", new JSONObject(stringOfEntity).get("status").toString());
				elementOfDocument.appendChild(elementOfStories);

				JSONArray jSONArrayOfStories = new JSONObject(stringOfEmbedded).getJSONArray("stories");
				for (int i = 0; i < jSONArrayOfStories.length(); i++) {
					JSONObject jSONObjectOfStory = jSONArrayOfStories.getJSONObject(i);

					//rank 1
					//create story---start
					Element elementOfStory = document.createElement("story");
					elementOfStory.setAttribute("id", jSONObjectOfStory.get("id").toString());
//					elementOfStory.setAttribute("emotions", jSONObjectOfStory.get("emotions").toString());
					elementOfStory.setAttribute("postedAt", jSONObjectOfStory.get("postedAt").toString());

					//rank 2
					//create story/author, story/content---start
					Element elementOfAuthor = document.createElement("author");
					elementOfAuthor.setAttribute("id", jSONObjectOfStory.getJSONObject("author").get("id").toString());
					elementOfAuthor.setAttribute("nickname", jSONObjectOfStory.getJSONObject("author").get("nickname").toString());
					if (!"null".equals(jSONObjectOfStory.getJSONObject("author").get("profileImgUrl").toString())) {
						elementOfAuthor.setAttribute("profileImgUrl", jSONObjectOfStory.getJSONObject("author").get("profileImgUrl").toString());
					}
					elementOfStory.appendChild(elementOfAuthor);

					Element elementOfContent = document.createElement("content");
					elementOfContent.appendChild(document.createTextNode(jSONObjectOfStory.get("content").toString()));
					elementOfStory.appendChild(elementOfContent);

					String mode = "";
					Element elementOfStoryImages = document.createElement("storyImages");
					JSONArray jSONArrayOfStoryImages = jSONObjectOfStory.getJSONArray("storyImage");
					for (int j = 0; j < jSONArrayOfStoryImages.length(); j++) {
						JSONObject jSONObjectOfStoryImage = jSONArrayOfStoryImages.getJSONObject(j);

						Element elementOfUrl = document.createElement("url");
						String decodedURL = URLDecoder.decode(jSONObjectOfStoryImage.get("imgUrl").toString(), "UTF-8");
						elementOfUrl.appendChild(document.createTextNode(decodedURL));

						Element elementOfStoryImage = document.createElement("storyImage");

						if (j == 0) {
							mode = "carousel-item active";
						} else {
							mode = "carousel-item";
						}
						elementOfStoryImage.setAttribute("mode", mode);

						String count = String.valueOf(j);
						elementOfStoryImage.setAttribute("count", count);
						elementOfStoryImage.appendChild(elementOfUrl);
						elementOfStoryImages.appendChild(elementOfStoryImage);
					}
					elementOfStory.appendChild(elementOfStoryImages);

					Element elementOfHref = document.createElement("href");
					elementOfHref.appendChild(document.createTextNode(jSONObjectOfStory.getJSONObject("_links").getJSONObject("self").get("href").toString()));
					elementOfStory.appendChild(elementOfHref);
					//create story/author, story/content---end

					//rank 3
					//create comments---start
					Element elementOfComments = document.createElement("comments");
					JSONArray jSONArrayOfComments = jSONObjectOfStory.getJSONArray("storyComment");
					for (int j = 0; j < jSONArrayOfComments.length(); j++) {
						JSONObject jSONObjectOfComment = jSONArrayOfComments.getJSONObject(j);
						//rank 4
						//create comment---start
						Element elementOfComment = document.createElement("comment");
						elementOfComment.setAttribute("id", jSONObjectOfComment.get("id").toString());

						//rank 5
						//create comment/content, comment/who---start
						Element contentOfComment = document.createElement("content");
						contentOfComment.appendChild(document.createTextNode(jSONObjectOfComment.get("content").toString()));
						elementOfComment.appendChild(contentOfComment);

						Element whoOfComment = document.createElement("who");
						whoOfComment.setAttribute("id", jSONObjectOfComment.get("whoId").toString());
						whoOfComment.setAttribute("nickname", jSONObjectOfComment.get("who").toString());
						elementOfComment.appendChild(whoOfComment);
						//create comment/content, comment/who---end

						elementOfComments.appendChild(elementOfComment);
						//create comment---end
					}
					elementOfStory.appendChild(elementOfComments);
					//create comments---end

					elementOfStories.appendChild(elementOfStory);
					//create story---end
				}
				//create stories---end
			}
		} finally {
			closeableHttpResponse.close();
		}

		DOMSource domSource = new DOMSource(document);
		StringWriter writer = new StringWriter();
		StreamResult result = new StreamResult(writer);
		TransformerFactory tf = TransformerFactory.newInstance();
		Transformer transformer = tf.newTransformer();
		transformer.transform(domSource, result);
		System.out.println(writer.toString());
		return document;
	}

	/**
	 * 获取个人主页数据 personnels/search/findOneById 路径
	 *
	 * @param id 参数
	 * @return
	 */
	public Document getPersonnels(String id, HttpSession httpSession) throws ParserConfigurationException, IOException {

		//获取文档对象
		DocumentBuilderFactory newInstance = DocumentBuilderFactory.newInstance();
		DocumentBuilder newDocumentBuilder = newInstance.newDocumentBuilder();
		Document doc = newDocumentBuilder.newDocument();

		//创建根节点
		Element documentElement = doc.createElement("document");
		doc.appendChild(documentElement);

		HttpGet httpGet = new AuthHttpClient().bulidHttpGet("personnels/search/findOneById?id=" + id);
		//获取请求体
		CloseableHttpResponse execute = HttpClients.createDefault().execute(httpGet);
		HttpEntity entity = execute.getEntity();

		if (entity != null) {
			String string = EntityUtils.toString(entity, "UTF-8");

			//获取所有数据
			JSONObject object = new JSONObject(string);

			//获取非array的数据
			//id-唯一标识
			Element homepageIdElement = doc.createElement("homepageId");
			homepageIdElement.setAttribute("id", object.get("id").toString());
			documentElement.appendChild(homepageIdElement);

			//coverImgUrl-背景图
			if (!"null".equals(object.get("coverImgUrl").toString())) {
				System.out.println("coverImgUrl-背景图--" + object.get("coverImgUrl").toString());
				Element coverImgUrlElement = doc.createElement("coverImgUrl");
				coverImgUrlElement.setAttribute("src", object.get("coverImgUrl").toString());
				documentElement.appendChild(coverImgUrlElement);
			}

			//profileImgUrl-头像
			if (!"null".equals(object.get("profileImgUrl").toString())) {
				Element profileImgUrlElement = doc.createElement("profileImgUrl");
				profileImgUrlElement.setAttribute("src", object.get("profileImgUrl").toString());
				documentElement.appendChild(profileImgUrlElement);
			}

			//profileText-简介
			Element profileTextElement = doc.createElement("profileText");
			profileTextElement.appendChild(doc.createTextNode(object.get("profileText").toString()));
			documentElement.appendChild(profileTextElement);

			//nickname-用户名
			Element nicknameElement = doc.createElement("nickname");
			nicknameElement.appendChild(doc.createTextNode(object.get("nickname").toString()));
			documentElement.appendChild(nicknameElement);

			//userStoryCount-发表文章数量
			Element userStoryCountElement = doc.createElement("userStoryCount");
			userStoryCountElement.appendChild(doc.createTextNode(object.get("userStoryCount").toString()));
			documentElement.appendChild(userStoryCountElement);

			//followerCount-追随者数量
			Element followerCountElement = doc.createElement("followerCount");
			followerCountElement.appendChild(doc.createTextNode(object.get("followerCount").toString()));
			documentElement.appendChild(followerCountElement);

			//followingCount-跟踪者数量
			Element followingCountElement = doc.createElement("followingCount");
			followingCountElement.appendChild(doc.createTextNode(object.get("followingCount").toString()));
			documentElement.appendChild(followingCountElement);

			//userStory-发表的文章
			Element userStoryElement = doc.createElement("userStory");
			documentElement.appendChild(userStoryElement);
			//userStoryArray
			JSONArray userStoryArray = object.getJSONArray("userStory");
			Element storyImagesElement = null;
			for (int i = 0; i < userStoryArray.length(); i++) {
				//初始化每行数据条数
				if (i == 0 || i % 3 == 0) {
					storyImagesElement = doc.createElement("storyImages");
					userStoryElement.appendChild(storyImagesElement);
				}
				JSONObject object2 = userStoryArray.getJSONObject(i);
				//storyImage-文章信息
				Element storyImageElement = doc.createElement("storyImage");
				storyImagesElement.appendChild(storyImageElement);
				//storyImage/imgUrl-图片信息
				JSONObject storyImageObject = object2.getJSONObject("storyImage");
				storyImageElement.setAttribute("imgUrl", storyImageObject.get("imgUrl").toString());
			}

			if (httpSession.getAttribute("id") != null) {
				//判断该主页是否属于该用户
				if (id.equals(httpSession.getAttribute("id").toString())) {
					//按键置灰
					Element disabledElement = doc.createElement("disabled");
					disabledElement.setAttribute("name", "true");
					documentElement.appendChild(disabledElement);

					//显示好友列表
					Element friendsListElement = doc.createElement("friendsList");
					friendsListElement.setAttribute("existence", "");
					documentElement.appendChild(friendsListElement);

					//查询所有好友
					List<Personnel> findFriendAll = findFriendAll(httpSession.getAttribute("id").toString());

					//添加好友数量
					friendsListElement.setAttribute("followerCount", findFriendAll.size() + "");

					//获取好友数据
					for (int i = 0; i < findFriendAll.size(); i++) {
						Element followersElement = doc.createElement("followers");
						friendsListElement.appendChild(followersElement);

						Personnel personnel = findFriendAll.get(i);

						//添加头像
						if (!"null".equals(personnel.getProfileImgUrl())) {
							followersElement.setAttribute("profileImgUrl", personnel.getProfileImgUrl());
						}

						//添加id
						followersElement.setAttribute("id", personnel.getId());

						//添加姓名
						Element relatingUserNicknameElement = doc.createElement("nickname");
						relatingUserNicknameElement.appendChild(doc.createTextNode(personnel.getNickname()));
						followersElement.appendChild(relatingUserNicknameElement);
					}

				} else {
					//判断是否已经添加过该用户
					JSONArray followersArray = object.getJSONArray("following");
					for (int i = 0; i < followersArray.length(); i++) {
						//获取已添加好友id
						String idString = followersArray.getJSONObject(i).getJSONObject("relatedUser").get("id").toString();
						if (idString.equals(httpSession.getAttribute("id"))) {
							//已添加按键置灰
							Element disabledElement = doc.createElement("disabled");
							disabledElement.setAttribute("name", "true");
							documentElement.appendChild(disabledElement);
						}
					}
				}
			}

		}
		return doc;
	}

	/**
	 * 查询该用户所有好友
	 *
	 * @param id
	 */
	public List<Personnel> findFriendAll(String id) throws IOException {
		//生成路径
		HttpGet httpGet = new AuthHttpClient().bulidHttpGet("personnels/search/findOneById?id=" + id);
		//获取请求体
		CloseableHttpResponse execute = HttpClients.createDefault().execute(httpGet);
		HttpEntity entity = execute.getEntity();

		if (entity != null) {
			//获取数据
			String string = EntityUtils.toString(entity, "UTF-8");

			//返回数据
			List<Personnel> personnels = new ArrayList<>();

			//所有数据
			JSONObject personalObject = new JSONObject(string);

			//获取所有追随者数据
			JSONArray followersArray = personalObject.getJSONArray("followers");

			if (followersArray.length() > 0) {

				for (int i = 0; i < followersArray.length(); i++) {
					Personnel personnel = new Personnel();

					//获取用户数据
					JSONObject relatedUserObject = followersArray.getJSONObject(i).getJSONObject("relatedUser");

					personnel.setProfileImgUrl(relatedUserObject.get("profileImgUrl").toString());
					personnel.setNickname(relatedUserObject.get("nickname").toString());
					personnel.setId(relatedUserObject.get("id").toString());

					personnels.add(personnel);
				}
				return personnels;
			}
		}
		return null;
	}

	//	/**
	//	 * 呼叫 ../storyComment/.. 新增留言
	//	 *
	//	 * @param storyid
	//	 * @param whoid
	//	 * @param content
	//	 * @throws IOException
	//	 * @throws ParserConfigurationException
	//	 */
	//	public void storyComment(String storyid, String whoid, String content)
	//		throws IOException, ParserConfigurationException {
	//		HttpPut httpPut = new AuthHttpClient().bulidHttpPut("storyComment/");
	//		ArrayList<NameValuePair> pairList = new ArrayList();
	//		pairList.add(new BasicNameValuePair("story", storyid));
	//		pairList.add(new BasicNameValuePair("who", whoid));
	//		pairList.add(new BasicNameValuePair("content", content));
	//
	//		httpPut.setEntity(new UrlEncodedFormEntity(pairList, "UTF-8"));
	//
	//		CloseableHttpResponse response1 = HttpClients.createDefault().execute(httpPut);
	//		HttpEntity Entity = response1.getEntity();
	//		System.out.println(EntityUtils.toString(Entity));
	//	}
	/**
	 * 呼叫 ../stories/" + id + "/storyComment/.. 取得某故事之最新留言
	 *
	 * @param storyId
	 * @return DOM 字串
	 * @throws ParserConfigurationException
	 * @throws IOException
	 * @throws TransformerConfigurationException
	 * @throws TransformerException
	 */
	public List<StoryComment> getStoryComment(String storyId) throws ParserConfigurationException, IOException, TransformerConfigurationException, TransformerException {
		String stringUri = "stories/" + storyId + "/storyComment/";
		HttpGet httpGet = new AuthHttpClient().bulidHttpGet(stringUri);

		CloseableHttpResponse closeableHttpResponse = HttpClients.createDefault().execute(httpGet);
		try {
			HttpEntity httpEntity = closeableHttpResponse.getEntity();
			if (httpEntity != null) {
				String stringOfEntity = EntityUtils.toString(httpEntity, "UTF-8");
				JSONObject jSONObjectOfEntity = new JSONObject(stringOfEntity);
				String stringOfEmbedded = jSONObjectOfEntity.get("_embedded").toString();
				JSONArray jSONArrayOfstoryComments = new JSONObject(stringOfEmbedded).getJSONArray("storyComments");
				List<StoryComment> storyComments = new ArrayList<>();

				for (int i = 0; i < jSONArrayOfstoryComments.length(); i++) {
					//返回数据
					StoryComment storyComment = new StoryComment();

					//获取数据
					JSONObject storyCommentObject = jSONArrayOfstoryComments.getJSONObject(i);

					storyComment.setRepliedAt(storyCommentObject.get("repliedAt").toString());//留言时间
					storyComment.setWhoId(storyCommentObject.get("whoId").toString());//留言用户id
					storyComment.setId(storyCommentObject.get("id").toString());//留言id
					storyComment.setUniversallyUniqueIdentifier(storyCommentObject.get("universallyUniqueIdentifier").toString());
					storyComment.setContent(storyCommentObject.get("content").toString());//留言内容
					storyComment.setWho(storyCommentObject.get("who").toString());//留言用户名

					storyComments.add(storyComment);
				}
				return storyComments;
			}
		} finally {
			closeableHttpResponse.close();
		}
		return null;
	}

	/**
	 * 呼叫 ../personnels/search/.. 尋找帳號是否存在, 存在傳回 JSONObject; 反之回傳null
	 *
	 * @param thirdParty
	 * @param userId
	 * @return 帳號相關資訊之JSONObject
	 * @throws ClientProtocolException
	 * @throws IOException
	 * @throws URISyntaxException
	 */
	public ThirdPartyUser findOneByThirdPartyId(String thirdParty, String userId) throws ClientProtocolException, IOException, URISyntaxException {
		String key = thirdParty + "Id";
		String url = "";
		switch (thirdParty) {
			case ("facebook"):
				url = "findOneByFacebookId";
				break;
			case ("google"):
				url = "findOneByGoogleId";
				break;
			default:
				url = "findOneByLineId";
				break;
		}

		ArrayList<NameValuePair> pairList = new ArrayList();
		pairList.add(new BasicNameValuePair(key, userId));

		URIBuilder builder = new URIBuilder(new AuthHttpClient().getHost() + "personnels/search/" + url);
		builder.setParameters(pairList);
		HttpGet httpGet = new AuthHttpClient().bulidHttpViaURI(builder.build());

		CloseableHttpResponse closeableHttpResponse = HttpClients.createDefault().execute(httpGet);
		HttpEntity httpEntity = closeableHttpResponse.getEntity();
		if (httpEntity == null) {
//			Logger.getGlobal().info("發生錯誤, Method: ApiService." + url);
			System.out.println("發生錯誤, Method: ApiService." + url + "()");
			return null;
		}

		String result = EntityUtils.toString(httpEntity, "UTF-8");

		if (result != null && result.length() > 0) {

			System.out.println("httpEntity: " + result);
			JSONObject jSONObjectOfResult = new JSONObject(result);

			//返回数据
			ThirdPartyUser thirdPartyUser = new ThirdPartyUser();

			thirdPartyUser.setId(jSONObjectOfResult.get("id").toString());
			thirdPartyUser.setUniversallyUniqueIdentifier(jSONObjectOfResult.get("universallyUniqueIdentifier").toString());
			thirdPartyUser.setNickname(jSONObjectOfResult.get("nickname").toString());
			thirdPartyUser.setFacebookId(jSONObjectOfResult.get("facebookId").toString());
			thirdPartyUser.setGoogleId(jSONObjectOfResult.get("googleId").toString());
			thirdPartyUser.setLineId(jSONObjectOfResult.get("lineId").toString());
			thirdPartyUser.setEmail(jSONObjectOfResult.get("email").toString());
			thirdPartyUser.setLastname(jSONObjectOfResult.get("lastname").toString());
			thirdPartyUser.setFirstname(jSONObjectOfResult.get("firstname").toString());
			thirdPartyUser.setBirth(jSONObjectOfResult.get("birth").toString());
			thirdPartyUser.setGender(jSONObjectOfResult.get("gender").toString());
			thirdPartyUser.setStoreName(jSONObjectOfResult.get("storeName").toString());
			thirdPartyUser.setOpenedAt(jSONObjectOfResult.get("openedAt").toString());
			thirdPartyUser.setCoverImgUrl(jSONObjectOfResult.get("coverImgUrl").toString());
			thirdPartyUser.setProfileImgUrl(jSONObjectOfResult.get("profileImgUrl").toString());
			thirdPartyUser.setProfileText(jSONObjectOfResult.get("profileText").toString());

			return thirdPartyUser;
		}

		return null;
	}

	/**
	 * 呼叫 ../personnels/search/findOneByEmail 尋找帳號是否存在, 存在傳回 JSONObject;
	 * 反之回傳null
	 *
	 * @param email
	 * @return
	 * @throws URISyntaxException
	 * @throws IOException
	 */
	public EmailUser findOneByEmail(String email) throws URISyntaxException, IOException {
		ArrayList<NameValuePair> pairList = new ArrayList();
		pairList.add(new BasicNameValuePair("email", email));

		URIBuilder uRIBuilder = new URIBuilder(new AuthHttpClient().getHost() + "personnels/search/findOneByEmail");
		uRIBuilder.setParameters(pairList);
		HttpGet httpGet = new AuthHttpClient().bulidHttpViaURI(uRIBuilder.build());

		CloseableHttpResponse closeableHttpResponse = HttpClients.createDefault().execute(httpGet);
		HttpEntity httpEntity = closeableHttpResponse.getEntity();
		if (httpEntity == null) {
//			Logger.getGlobal().info("發生錯誤, Method: ApiService." + url);
			System.out.println("發生錯誤, Method: ApiService.findOneByEmail()");
			return null;
		}

		String result = EntityUtils.toString(httpEntity, "UTF-8");
		if (result != null && result.length() > 0) {

			System.out.println("httpEntity: " + result);
			JSONObject jSONObjectOfResult = new JSONObject(result);

			//创建EmailUser对象
			EmailUser emailUser = new EmailUser();

			emailUser.setGoogleId(jSONObjectOfResult.get("googleId").toString());
			emailUser.setProfileText(jSONObjectOfResult.get("profileText").toString());
			emailUser.setFirstname(jSONObjectOfResult.get("firstname").toString());
			emailUser.setGender(jSONObjectOfResult.get("gender").toString());
			emailUser.setFacebookId(jSONObjectOfResult.get("facebookId").toString());
			emailUser.setLineId(jSONObjectOfResult.get("lineId").toString());
			emailUser.setBirth(jSONObjectOfResult.get("birth").toString());
			emailUser.setUniversallyUniqueIdentifier(jSONObjectOfResult.get("universallyUniqueIdentifier").toString());
			emailUser.setLastname(jSONObjectOfResult.get("lastname").toString());
			emailUser.setOpenedAt(jSONObjectOfResult.get("openedAt").toString());
			emailUser.setCoverImgUrl(jSONObjectOfResult.get("coverImgUrl").toString());
			emailUser.setProfileImgUrl(jSONObjectOfResult.get("profileImgUrl").toString());
			emailUser.setNickname(jSONObjectOfResult.get("nickname").toString());
			emailUser.setStoreName(jSONObjectOfResult.get("storeName").toString());
			emailUser.setId(jSONObjectOfResult.get("id").toString());
			emailUser.setEmail(jSONObjectOfResult.get("email").toString());
			//获取该用户的所有文章
			JSONArray userStoryArray = jSONObjectOfResult.getJSONObject("_embedded").getJSONArray("userStory");
			emailUser.setUserStory(findOneByEmailStorie(userStoryArray));

			return emailUser;
		}
		return null;
	}

	//封装用户所有文章
	public List<Storie> findOneByEmailStorie(JSONArray userStoryArray) {
		List<Storie> storys = new ArrayList<>();
		for (int i = 0; i < userStoryArray.length(); i++) {
			//获取每一篇文章
			JSONObject storyObject = userStoryArray.getJSONObject(i);
			Storie storie = new Storie();

			storie.setId(storyObject.get("id").toString());
			storie.setStoryCommentCount(storyObject.get("storyCommentCount").toString());
			storie.setPostedAt(storyObject.get("postedAt").toString());
			storie.setContent(storyObject.get("content").toString());
			//获取每一篇文章的作者
			storie.setAuthor(findOneByEmailPersonnel(storyObject.getJSONObject("author")));
			//获取每一篇文章的图片
			storie.setStoryImage(findOneByEmailStoryImages(storyObject.getJSONArray("storyImage")));
			//获取每一篇文章留言
			storie.setStoryComment(findOneByEmailStoryComments(storyObject.getJSONArray("storyComment")));

			storys.add(storie);
		}
		return storys;
	}

	//封装文章留言
	public List<StoryComment> findOneByEmailStoryComments(JSONArray storyCommentArray) {
		List<StoryComment> storyComments = new ArrayList<>();
		for (int i = 0; i < storyCommentArray.length(); i++) {
			JSONObject storyCommentObject = storyCommentArray.getJSONObject(i);
			StoryComment storyComment = new StoryComment();
			//封装数据
			storyComment.setId(storyCommentObject.get("id").toString());
			storyComment.setRepliedAt(storyCommentObject.get("repliedAt").toString());
			storyComment.setWhoId(storyCommentObject.get("whoId").toString());
			storyComment.setUniversallyUniqueIdentifier(storyCommentObject.get("universallyUniqueIdentifier").toString());
			storyComment.setContent(storyCommentObject.get("content").toString());
			storyComment.setWho(storyCommentObject.get("who").toString());

			storyComments.add(storyComment);
		}
		return storyComments;
	}

	//封装文章图片
	public List<StoryImage> findOneByEmailStoryImages(JSONArray storyImageArray) {
		List<StoryImage> storyImages = new ArrayList<>();
		for (int i = 0; i < storyImageArray.length(); i++) {
			JSONObject storyImageObject = storyImageArray.getJSONObject(i);
			StoryImage storyImage = new StoryImage();
			//封装数据
			storyImage.setImgUrl(storyImageObject.get("imgUrl").toString());
			storyImage.setContent(storyImageObject.get("content").toString());
			storyImages.add(storyImage);
		}
		return storyImages;
	}

	//封装文章作者
	public Personnel findOneByEmailPersonnel(JSONObject authorObject) {
		Personnel personnel = new Personnel();
		//封装数据
		personnel.setId(authorObject.get("id").toString());
		personnel.setNickname(authorObject.get("nickname").toString());
		personnel.setProfileImgUrl(authorObject.get("profileImgUrl").toString());
		return personnel;
	}

	/**
	 *
	 * @param nickname
	 * @param facebookId
	 * @param googleId
	 * @param lineId
	 * @param email
	 * @param lastname
	 * @param firstname
	 * @param birth
	 * @param gender
	 * @param storeName
	 * @return
	 * @throws ClientProtocolException
	 * @throws IOException
	 */
	public String registerUser(User user) throws ClientProtocolException, IOException {
		CloseableHttpClient closeableHttpClient = HttpClients.createDefault();
		HttpPost httpPost = new AuthHttpClient().bulidHttpPost("personnels");

		JSONObject jSONObjectOfParams = new JSONObject();
		jSONObjectOfParams.put("nickname", user.getNickname());
		jSONObjectOfParams.put("facebookId", user.getFacebookId());
		jSONObjectOfParams.put("googleId", user.getGoogleId());
		jSONObjectOfParams.put("lineId", user.getLineId());
		jSONObjectOfParams.put("email", user.getEmail());
		jSONObjectOfParams.put("lastname", user.getLastname());
		jSONObjectOfParams.put("firstname", user.getFirstname());
		jSONObjectOfParams.put("birth", user.getBirth());
		jSONObjectOfParams.put("gender", user.getGender());
		jSONObjectOfParams.put("storeName", user.getStoreName());
		System.out.println("jSONObjectOfParams: " + jSONObjectOfParams.toString());
		System.out.println("1");

		StringEntity stringEntityOfPersonnel = new StringEntity(jSONObjectOfParams.toString(), "UTF-8");
		System.out.println("stringEntityOfPersonnel: " + stringEntityOfPersonnel);
		System.out.println("2");
		httpPost.setEntity(stringEntityOfPersonnel);
		httpPost.setHeader("Content-type", "application/json");
		CloseableHttpResponse closeableHttpResponse = closeableHttpClient.execute(httpPost);
		System.out.println("3");

		closeableHttpResponse.close();
		closeableHttpClient.close();
		return "註冊成功";

	}

	/*
		  {
		 	"content": "this is a story content.",		 
		 	"imgUrls":{"URL","URL","URL"}
		    "Author": "http://localhost:8080/personnels/1" 
		  }
	 */
	public String postStory(String content, String personnelsUri, String[] imgUrls) throws Exception {
		CloseableHttpClient closeableHttpClient = HttpClients.createDefault();
		HttpPost httpPost = new AuthHttpClient().bulidHttpPost("stories");

		JSONObject jSONObjectOfParams = new JSONObject();
		jSONObjectOfParams.put("content", content);
//		jSONObjectOfParams.put("imgUrls", new JSONArray(Arrays.asList(imgUrls)));
		jSONObjectOfParams.put("author", "https://redan-api.herokuapp.com/personnels/" + personnelsUri);

		System.out.println("jSONObjectOfParams: " + jSONObjectOfParams.toString());

		StringEntity stringEntityOfPersonnel = new StringEntity(jSONObjectOfParams.toString(), "UTF-8");

		httpPost.setEntity(stringEntityOfPersonnel);
		httpPost.setHeader("Content-type", "application/hal+json;charset=UTF-8");
		CloseableHttpResponse closeableHttpResponse = closeableHttpClient.execute(httpPost);

		System.out.println("----------------------------------------");
		System.out.println(closeableHttpResponse.getStatusLine());

		JSONObject storyInfo = new JSONObject(
			EntityUtils.toString(closeableHttpResponse.getEntity()));
		String storyUrl = storyInfo.getJSONObject("_links").getJSONObject("self").getString("href");

		Arrays.asList(imgUrls).forEach(imgUrl -> {
			try {
				System.out.println(postImgUrl(imgUrl, null, storyUrl));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		});

		closeableHttpResponse.close();
		closeableHttpClient.close();
		return "";

	}

	/*	
		{
			"story":"http://localhost:8080/stories/1",
			"who":"http://localhost:8080/personnels/1",
			"content":"oka"
		}
	 */
	public String postComments(String content, String personnelsUri, String storyUri) throws ClientProtocolException, IOException {
		CloseableHttpClient closeableHttpClient = HttpClients.createDefault();
		HttpPost httpPost = new AuthHttpClient().bulidHttpPost("storyComments");

		JSONObject jSONObjectOfParams = new JSONObject();
		jSONObjectOfParams.put("story", storyUri);
		jSONObjectOfParams.put("who", personnelsUri);
		jSONObjectOfParams.put("content", content);

		System.out.println("jSONObjectOfParams: " + jSONObjectOfParams.toString());

		StringEntity stringEntityOfPersonnel = new StringEntity(jSONObjectOfParams.toString(), "UTF-8");

		httpPost.setEntity(stringEntityOfPersonnel);
		httpPost.setHeader("Content-type", "application/json");
		CloseableHttpResponse closeableHttpResponse = closeableHttpClient.execute(httpPost);

		closeableHttpResponse.close();
		closeableHttpClient.close();
		return "留言成功";
	}

	/*
	{	
		"url":"http://localhost:8080/img1",
		"content":"oka",
		"story":"http://localhost:8080/stories/1"
	}
	 */
	public String postImgUrl(String imgUrl, String content, String storyUrl) throws ClientProtocolException, IOException {
		CloseableHttpClient closeableHttpClient = HttpClients.createDefault();
		HttpPost httpPost = new AuthHttpClient().bulidHttpPost("storyImages");

		JSONObject jSONObjectOfParams = new JSONObject();
		jSONObjectOfParams.put("url", imgUrl);
		jSONObjectOfParams.put("content", "redan");
		jSONObjectOfParams.put("story", storyUrl);

		System.out.println("postImgUrl: " + jSONObjectOfParams.toString());

		StringEntity stringEntityOfPersonnel = new StringEntity(jSONObjectOfParams.toString(), "UTF-8");

		httpPost.setEntity(stringEntityOfPersonnel);
		httpPost.setHeader("Content-type", "application/hal+json;charset=UTF-8");
		CloseableHttpResponse closeableHttpResponse = closeableHttpClient.execute(httpPost);
		System.out.println(EntityUtils.toString(closeableHttpResponse.getEntity()));
		closeableHttpResponse.close();
		closeableHttpClient.close();
		return "上傳成功";
	}

	/*
	发送好友请求
	
		{
			"relatedUser":"https://redan-api.herokuapp.com/personnels/3"
			"relatingUser":"https://redan-api.herokuapp.com/personnels/1"
			"status":0
		 }
	 */
	public void postFriend(String relatedId, String relatingId) throws IOException {
		JSONObject jSONFriend = new JSONObject();

		//获取请求发送者者
		jSONFriend.put("relatedUser", "https://redan-api.herokuapp.com/personnels/" + relatedId);

		//获取被请求者
		jSONFriend.put("relatingUser", "https://redan-api.herokuapp.com/personnels/" + relatingId);

		//状态
		jSONFriend.put("status", "0");

		//创建连接
		HttpPost httpPost = new AuthHttpClient().bulidHttpPost("userRelationships");

		//添加请求头
		httpPost.setHeader("Content-type", "application/hal+json;charset=UTF-8");

		//封装数据
		StringEntity stringEntityGoodFriend = new StringEntity(jSONFriend.toString(), "UTF-8");

		//添加请求体
		httpPost.setEntity(stringEntityGoodFriend);

		//发送请求
		CloseableHttpClient closeableHttpClient = HttpClients.createDefault();
		CloseableHttpResponse closeableHttpResponse = closeableHttpClient.execute(httpPost);

		//查看结果
		String storyUrl = new JSONObject(EntityUtils.toString(closeableHttpResponse.getEntity())).toString();
		System.out.println("添加成功---------------" + storyUrl);

		closeableHttpResponse.close();
		closeableHttpClient.close();

	}

	/**
	 * 会员数据
	 *
	 * @param id
	 * @throws ParserConfigurationException
	 * @throws IOException
	 */
	public Document memberCenterXml(String id) throws ParserConfigurationException, IOException {
		//创建文档对象
		DocumentBuilderFactory newInstance = DocumentBuilderFactory.newInstance();
		DocumentBuilder newDocumentBuilder = newInstance.newDocumentBuilder();
		Document doc = newDocumentBuilder.newDocument();

		//生成根元素
		Element documentElement = doc.createElement("document");
		doc.appendChild(documentElement);

		//创建连接
		HttpGet httpGet = new AuthHttpClient().bulidHttpGet("personnels/" + id);

		//获取请求体
		CloseableHttpResponse execute = HttpClients.createDefault().execute(httpGet);
		HttpEntity entity = execute.getEntity();

		if (entity != null) {
			String string = EntityUtils.toString(entity, "UTF-8");

			JSONObject jsonObject = new JSONObject(string);

			//universallyUniqueIdentifier-唯一标识符
			Element universallyUniqueIdentifierElement = doc.createElement("universallyUniqueIdentifier");
			universallyUniqueIdentifierElement.appendChild(doc.createTextNode(jsonObject.get("universallyUniqueIdentifier").toString()));
			documentElement.appendChild(universallyUniqueIdentifierElement);

			//nickname-名字
			Element nicknameElement = doc.createElement("nickname");
			nicknameElement.appendChild(doc.createTextNode(jsonObject.get("nickname").toString()));
			documentElement.appendChild(nicknameElement);

			//facebookId-脸谱网id
			Element facebookIdElement = doc.createElement("facebookId");
			facebookIdElement.appendChild(doc.createTextNode(jsonObject.get("facebookId").toString()));
			documentElement.appendChild(facebookIdElement);

			//googleId-谷歌id
			Element googleIdElement = doc.createElement("googleId");
			googleIdElement.appendChild(doc.createTextNode(jsonObject.get("googleId").toString()));
			documentElement.appendChild(googleIdElement);

			//lineId-
			Element lineIdElement = doc.createElement("lineId");
			lineIdElement.appendChild(doc.createTextNode(jsonObject.get("lineId").toString()));
			documentElement.appendChild(lineIdElement);

			//email-邮箱/账号
			Element emailElement = doc.createElement("email");
			emailElement.appendChild(doc.createTextNode(jsonObject.get("email").toString()));
			documentElement.appendChild(emailElement);

			//lastname-姓
			Element lastnameElement = doc.createElement("lastname");
			lastnameElement.appendChild(doc.createTextNode(jsonObject.get("lastname").toString()));
			documentElement.appendChild(lastnameElement);

			//firstname-初始名称
			Element firstnameElement = doc.createElement("firstname");
			firstnameElement.appendChild(doc.createTextNode(jsonObject.get("firstname").toString()));
			documentElement.appendChild(firstnameElement);

			//birth-生日
			Element birthElement = doc.createElement("birth");
			birthElement.appendChild(doc.createTextNode(jsonObject.get("birth").toString()));
			documentElement.appendChild(birthElement);

			//gender-性别
			Element genderElement = doc.createElement("gender");
			genderElement.appendChild(doc.createTextNode(jsonObject.get("gender").toString()));
			documentElement.appendChild(genderElement);

			//storeName-商铺名称
			Element storeNameElement = doc.createElement("storeName");
			storeNameElement.appendChild(doc.createTextNode(jsonObject.get("storeName").toString()));
			documentElement.appendChild(storeNameElement);

			//openedAt-账号成立时间
			Element openedAtElement = doc.createElement("openedAt");
			openedAtElement.appendChild(doc.createTextNode(jsonObject.get("openedAt").toString()));
			documentElement.appendChild(openedAtElement);

			//coverImgUrl-主页背景图
			Element coverImgUrlElement = doc.createElement("coverImgUrl");
			coverImgUrlElement.appendChild(doc.createTextNode(jsonObject.get("coverImgUrl").toString()));
			documentElement.appendChild(coverImgUrlElement);

			//profileImgUrl-头像
			Element profileImgUrlElement = doc.createElement("profileImgUrl");
			profileImgUrlElement.appendChild(doc.createTextNode(jsonObject.get("profileImgUrl").toString()));
			documentElement.appendChild(profileImgUrlElement);

			//profileText-个人简介
			Element profileTextElement = doc.createElement("profileText");
			profileTextElement.appendChild(doc.createTextNode(jsonObject.get("profileText").toString()));
			documentElement.appendChild(profileTextElement);

			return doc;
		}
		return null;
	}
	
	
}
