package com.lzx.hero.bean;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
/**
 * 英雄表
 * @author lzx
 *
 */
@Entity
@Table(name="tb_hero")
public class TbHero{
	/**
	 * 英雄id
	 */
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer id;
	/**
	 * 英雄名称
	 */
	@Column(name = "name")
	private String name;
	/**
	 * 站位(1为前排,2为中排,3为后排)
	 */
	@Column(name = "address")
	private Integer address;
	/**
	 * 星数
	 */
	@Column(name = "star")
	private Integer star;
	/**
	 * 排序
	 */
	@Column(name = "sec")
	private Integer sec;
	/**
	 * 类型（1为力量，2为敏捷，3为魔法）
	 */
	@Column(name = "type")
	private Integer type;
	/**
	 * 官网id
	 */
	@Column(name = "official_id")
	private Integer officialId;
	/**
	 * 第三方id
	 */
	@Column(name = "third_id")
	private Integer thirdId;
	/**
	 * 图片地址
	 */
	@Column(name = "img")
	private String img;
	public void setId(Integer id){
		this.id = id;
	}
	public Integer getId(){
		return this.id;
	}
	public void setName(String name){
		this.name = name;
	}
	public String getName(){
		return this.name;
	}
	public void setAddress(Integer address){
		this.address = address;
	}
	public Integer getAddress(){
		return this.address;
	}
	public void setStar(Integer star){
		this.star = star;
	}
	public Integer getStar(){
		return this.star;
	}
	public void setSec(Integer sec){
		this.sec = sec;
	}
	public Integer getSec(){
		return this.sec;
	}
	public void setType(Integer type){
		this.type = type;
	}
	public Integer getType(){
		return this.type;
	}
	public void setOfficialId(Integer officialId){
		this.officialId = officialId;
	}
	public Integer getOfficialId(){
		return this.officialId;
	}
	public void setThirdId(Integer thirdId){
		this.thirdId = thirdId;
	}
	public Integer getThirdId(){
		return this.thirdId;
	}
	public void setImg(String img){
		this.img = img;
	}
	public String getImg(){
		return this.img;
	}
	
	public enum AddressEnum{
		front(1,"前排"),center(2,"中排"),back(3,"后排");
		private int id;
		private String text;
		private AddressEnum(int id,String text){
			this.id=id;
			this.text=text;
		}
		public int getId() {
			return id;
		}
		public void setId(int id) {
			this.id = id;
		}
		public String getText() {
			return text;
		}
		public void setText(String text) {
			this.text = text;
		}
	}
	public enum TypeEnum{
		 Power(1,"力量型"),intelligence(2,"智力型"),agility(3,"敏捷型");
		private int id;
		private String text;
		private TypeEnum(int id,String text){
			this.id=id;
			this.text=text;
		}
		public int getId() {
			return id;
		}
		public void setId(int id) {
			this.id = id;
		}
		public String getText() {
			return text;
		}
		public void setText(String text) {
			this.text = text;
		}
	}
	@Override
	public String toString() {
		return "TbHero [id=" + id + ", name=" + name + ", address=" + address + ", star=" + star + ", sec=" + sec
				+ ", type=" + type + ", officialId=" + officialId + ", thirdId=" + thirdId + ", img=" + img + "]";
	}
	

}
