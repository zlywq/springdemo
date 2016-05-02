package web1.controller;

import java.util.*;

import org.bson.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import cmn2.util.*;
import spr2.domain.*;
import spr2.service.*;




@Controller
@RequestMapping("/post")
public class PostController {
	
	protected final Logger logger = LoggerFactory.getLogger(getClass());


	@Autowired
	BbsPostService postService;

	
	@Autowired
	UtilService utilService;
	

	
	
	
	/*
	 * http://localhost:8080/smmpWeb/post/getById.json?postId=123
	 */
	@RequestMapping(value = "/getById")
	public String getPost(ModelMap model, long postId) {
		try{
			BbsPost post = postService.getById(postId);
			model.put("post", post);
			model.put(Const.Key_success, true);
		}catch(Exception e){
			UtilMsg.retriveErrMsgAndCodeToMap_withLog(e, model);
		}
		return "post/post";
	}
	
	/*
	 * http://localhost:8080/smmpWeb/post/getPosts.json
	 */
	@RequestMapping(value = "/getPosts")
	public String getPosts(ModelMap model) {
		try{
			List<BbsPost> postList = postService.getPosts();
			model.put("postList", postList);
			model.put(Const.Key_success, true);
		}catch(Exception e){
			UtilMsg.retriveErrMsgAndCodeToMap_withLog(e, model);
		}
		return "post/postList";
	}
	
	

	
	
	/*
	 * http://localhost:8080/smmpWeb/post/insert.json?title=aa&content=aaa
	 */
	@RequestMapping(value = "/insert")
	public String insert(ModelMap model, BbsPost post) {
		try{
			long userIdInSession = Util1.getUserIdInSession();
			post.setUserId(userIdInSession);
			
			postService.insert(post);
			model.put("postId", post.getPostId());
			model.put(Const.Key_success, true);
		}catch(Exception e){
			UtilMsg.retriveErrMsgAndCodeToMap_withLog(e, model);
		}
		return "empty";
	}
	
	
	/*
	 * http://localhost:8080/smmpWeb/post/update.json?postId=12&title=aa&content=aaa
	 */
	@RequestMapping(value = "/update")
	public String update(ModelMap model, BbsPost post) {
		try{
			long userIdInSession = Util1.getUserIdInSession();
			post.setUserId(userIdInSession);
			
			postService.update(post);
			model.put(Const.Key_success, true);
		}catch(Exception e){
			UtilMsg.retriveErrMsgAndCodeToMap_withLog(e, model);
		}
		return "empty";
	}
	
	
	/*
	 * http://localhost:8080/smmpWeb/post/delete.json?postId=12
	 */
	@RequestMapping(value = "/delete")
	public String delete(ModelMap model, long postId) {
		try{
			long userIdInSession = Util1.getUserIdInSession();
			postService.delete(postId, userIdInSession);
			model.put(Const.Key_success, true);
		}catch(Exception e){
			UtilMsg.retriveErrMsgAndCodeToMap_withLog(e, model);
		}
		return "empty";
	}
	
	
}
