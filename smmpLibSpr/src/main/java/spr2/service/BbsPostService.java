package spr2.service;

import java.util.*;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.SqlSession;
import org.bson.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import com.itextpdf.text.pdf.PdfStructTreeController.returnType;

import cmn2.util.*;
import spr2.domain.*;
import spr2.ibatisMapper.*;




@Service
public class BbsPostService {
	
	@Autowired
	IdGeneratorLikeSnowflake idGeneratorLikeSnowflake;
	
	@Autowired
	private BbsPostMapper postMapper;
	
		
	@Autowired
	private SqlSession sqlSession;
	
	@Autowired
	DataSourceTransactionManager transactionManager;
	
	
	@Autowired
	UtilService utilService;
	
	private final Logger logger = LoggerFactory.getLogger(getClass());
	
	
	
	public BbsPost getById(long postId){
		return postMapper.getById(postId);
	}
	@Transactional(rollbackFor=Exception.class)
	public BbsPost getByIdInTran(long postId){
		return postMapper.getById(postId);
	}
	
	
	public List<BbsPost> getByUserId(long userId){
		return postMapper.getByUserId(userId);
	}
	
	public List<BbsPost> getPosts(){
		return postMapper.getPosts();
	}

	
	public void checkFields(BbsPost post,boolean isInsert){
		String errMsg = null;
		if (isInsert){
			if ( post.getUserId()==0 ){
				utilService.throwEx(ErrCode.Bus_ParamErr,"userId needed", post);
			}
		}
		
		if ( post.getUserId()==0 || Util1.isStringEmpty(post.getTitle()) 
				|| Util1.isStringEmpty(post.getContent()) 
				){
			errMsg = "请输入标题，内容等必要字段";
			utilService.throwEx(ErrCode.Bus_ParamErr,errMsg, post);
		}else {	
		}
		return ;
	}
	
	@Transactional(rollbackFor=Exception.class)
	public void insert(BbsPost post){
		checkFields(post,true);
		
		long postId = idGeneratorLikeSnowflake.generateId(BbsPost.class);
		post.setPostId(postId);
		postMapper.insert(post);
	}
	
	
	
	@Transactional(rollbackFor=Exception.class)
	public void update(BbsPost post){
		checkFields(post,false);
		
		if (post.getPostId()==0){
			utilService.throwEx(ErrCode.Bus_ParamErr,"postId needed", post);
		}
		BbsPost oldPost = postMapper.getByIdForUpdate(post.getPostId());
		if (oldPost == null){
			utilService.throwEx(ErrCode.Bus_ParamErr,"no this post", post);
		}
		if (oldPost.getUserId() != post.getUserId()){
//			utilService.throwEx(ErrCode.Bus_Common,"only can edit self post", post);
		}
		
		postMapper.update(post);
	}
	
	
	@Transactional(rollbackFor=Exception.class)
	public void delete(long postId, long userIdDoing){
		if (postId==0){
			utilService.throwEx(ErrCode.Bus_ParamErr,"postId needed");
		}
		BbsPost post = postMapper.getByIdForUpdate(postId);
		if (post == null){
			return;
		}
		if (post.getUserId() != userIdDoing){
//			utilService.throwEx(ErrCode.Bus_Common,"only self can delete post", post);
		}
		postMapper.delete(postId);
	}
	
	
	
	public List<BbsPost> getPostsPaged(long upperPostIdExclude, int pageSize){
		return postMapper.getPostsPaged(upperPostIdExclude, pageSize);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
}
