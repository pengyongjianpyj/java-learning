package com.java.test.po.first;

import com.java.base.po.BaseDO;
import java.io.Serializable;
import java.util.Date;

/**
 * @function 功能 :first 实体
 * @author   创建人:
 * @date     创建日期:2019-12-31 10:39:10
 */
public class UserBaseInfoDO extends BaseDO implements Serializable {

	
	private Long id;    //主键ID
	
	private String nickName;    //微信用户昵称
	
	private String headImgUrl;    //用户头像，最后一个数值代表正方形头像大小（有0、46、64、96、132数值可选，0代表640*640正方形头像），用户没有头像时该项为空。若用户更换头像，原有头像URL将失效
	
	private Byte sex;    //用户的性别，值为1时是男性，值为2时是女性，值为0时是未知
	
	private String province;    //用户个人资料填写的省份
	
	private String city;    //普通用户个人资料填写的城市
	
	private String country;    //国家，如中国为CN
	
	private String openId;    //用户的唯一标识(微信端)
	
	private String unionId;    //只有在用户将公众号绑定到微信开放平台帐号后，才会出现该字段。
	
	private Byte status;    //状态状态：1正常   0删除
	
	private java.util.Date gmtCreate;    //记录创建时间
	
	private java.util.Date gmtModified;    //记录更新时间
	
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getNickName() {
		return nickName;
	}
	public void setNickName(String nickName) {
		this.nickName = nickName;
	}
	
	public String getHeadImgUrl() {
		return headImgUrl;
	}
	public void setHeadImgUrl(String headImgUrl) {
		this.headImgUrl = headImgUrl;
	}
	
	public Byte getSex() {
		return sex;
	}
	public void setSex(Byte sex) {
		this.sex = sex;
	}
	
	public String getProvince() {
		return province;
	}
	public void setProvince(String province) {
		this.province = province;
	}
	
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	
	public String getOpenId() {
		return openId;
	}
	public void setOpenId(String openId) {
		this.openId = openId;
	}
	
	public String getUnionId() {
		return unionId;
	}
	public void setUnionId(String unionId) {
		this.unionId = unionId;
	}
	
	public Byte getStatus() {
		return status;
	}
	public void setStatus(Byte status) {
		this.status = status;
	}
	
	public java.util.Date getGmtCreate() {
		return gmtCreate;
	}
	public void setGmtCreate(java.util.Date gmtCreate) {
		this.gmtCreate = gmtCreate;
	}
	
	public java.util.Date getGmtModified() {
		return gmtModified;
	}
	public void setGmtModified(java.util.Date gmtModified) {
		this.gmtModified = gmtModified;
	}
	
}

