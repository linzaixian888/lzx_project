package com.lzx.hero.bean;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
/**
 * 对战结果表
 * @author lzx
 *
 */
@Entity
@Table(name="tb_result")
public class TbResult{
	/**
	 * 结果id
	 */
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer id;
	/**
	 * 胜率
	 */
	@Column(name = "percentage")
	private Integer percentage;
	/**
	 * 胜利队伍
	 */
	@Column(name = "success")
	private String success;
	/**
	 * 失败队伍
	 */
	@Column(name = "error")
	private String error;
	/**
	 * 保存时间
	 */
	@Column(name = "stime")
	private Date stime;
	public void setId(Integer id){
		this.id = id;
	}
	public Integer getId(){
		return this.id;
	}
	public void setPercentage(Integer percentage){
		this.percentage = percentage;
	}
	public Integer getPercentage(){
		return this.percentage;
	}
	public void setSuccess(String success){
		this.success = success;
	}
	public String getSuccess(){
		return this.success;
	}
	public void setError(String error){
		this.error = error;
	}
	public String getError(){
		return this.error;
	}
	public Date getStime() {
		return stime;
	}
	public void setStime(Date stime) {
		this.stime = stime;
	}

}
