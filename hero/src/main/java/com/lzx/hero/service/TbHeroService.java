package com.lzx.hero.service;


import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lzx.hero.bean.TbHero;
import com.lzx.hero.bean.TbHero.AddressEnum;
import com.lzx.hero.bean.TbHero.TypeEnum;
import com.lzx.hero.dao.TbHeroDao;
import com.lzx.hero.dao.base.Page;
import com.lzx.hero.service.base.BaseService;
import com.lzx.hero.util.HttpClientUtil;

@Service

public class TbHeroService extends BaseService<TbHero,Integer> implements ITbHeroService{
	@Autowired
	private TbHeroDao tbHeroDao;	
	@Autowired
	public void setBaseDao(TbHeroDao tbHeroDao) {
		super.setBaseDao(tbHeroDao);
	}
	@Override
	public TbHero getHero(String name) {
		return tbHeroDao.getHero(name);
	}
	@Override
	public void inserOrtUpdateAllHero(Collection<TbHero> collection) {
		Iterator<TbHero> it=collection.iterator();
		while(it.hasNext()){
			TbHero hero=it.next();
			TbHero h=getHero(hero.getName());
			if(h!=null){
				hero.setId(h.getId());
				hero.setSec(h.getSec());
				tbHeroDao.update(hero);
			}else{
				tbHeroDao.save(hero);
			}
		}
//		//召唤师的站位是错误的，不是中排，应该为后排
//		TbHero hero=getHero("召唤师");
//		if(hero!=null){
//			hero.setAddress(3);
//			tbHeroDao.update(hero);
//		}
		
	}
	@Override
	public List<TbHero> findAllOrderAddress() {
		return tbHeroDao.findAllOrderAddress();
	}
	@Override
	public void updateAddressSec(List<Map> allHeroList) {
		for(Map map:allHeroList){
			List<Map> children=(List<Map>) map.get("children");
			int index=1;
			for(Map heroMap:children){
				int id=Integer.parseInt(heroMap.get("id").toString());
				tbHeroDao.updateAddressSec(id, index);
				index++;
			}
		}
		
	}
	private static void initFromOfficial(Map<Integer, TbHero> cacheMap,AddressEnum addressEnum,TypeEnum typeEnum) throws Exception{
		HttpClientUtil util=new HttpClientUtil();
		Map<String,Object> param=new HashMap();
		param.put("y", "all");
		if(addressEnum!=null){
			param.put("location", addressEnum.getText());
		}
		if(typeEnum!=null){
			param.put("type", typeEnum.getText());
		}
		String heroJson=util.doPostString("http://d.longtugame.com/daotadata/contrast",param);
		ObjectMapper mapper=new ObjectMapper();
		Map map=mapper.readValue(heroJson, Map.class);
		String allHeroHtml=(String) map.get("str");
		Document document=Jsoup.parse(allHeroHtml);
		Elements elements=document.getElementsByTag("li");
		for(int i=0;i<elements.size();i++){
			Element e=elements.get(i);
			if("li".equals(e.nodeName())){
				String aHref=e.getElementsByTag("a").attr("href");
				String idStr=aHref.substring(aHref.lastIndexOf("=")+1);
				int id=Integer.parseInt(idStr);
				TbHero hero=cacheMap.get(id);
				if(hero==null){
					hero=new TbHero();
					hero.setOfficialId(id);
					hero.setId(id);
					hero.setThirdId(id);
					cacheMap.put(id, hero);
				}
				hero.setName(e.text().replace("\"", ""));
				hero.setImg(e.getElementsByTag("img").get(0).attr("src"));
				if(addressEnum!=null){
					hero.setAddress(addressEnum.getId());
				}
				if(typeEnum!=null){
					hero.setType(typeEnum.getId());
				}
			}
		}
	}
	public  void saveAllHero() throws Exception{
			Map<Integer,TbHero> cacheMap=new HashMap<Integer, TbHero>();
			for(AddressEnum addressEnum:AddressEnum.values()){
				initFromOfficial(cacheMap, addressEnum, null);
			}
			for(TypeEnum typeEnum:TypeEnum.values()){
				initFromOfficial(cacheMap, null, typeEnum);
			}
			inserOrtUpdateAllHero(cacheMap.values());
	}
	
	/**
     * 单个unicode转换为中文，不匹配则返回原字符串
     * @param unicode
     * @return
     */
    private  String unicodeToString (String unicode){
        int length=unicode.length();
        StringBuilder sb=new StringBuilder();
        if(unicode.startsWith("\\u")&&length==6){
            return sb.append((char)Integer.parseInt(unicode.substring(2), 16)).toString();
        }
        return unicode;
        
    }
    /**
     * 将字会串里面的unicode转换为中文
     * @param str
     * @return
     */
    private  String coverUnicode(String str){
        StringBuilder sb=new StringBuilder();
        int length=str.length();
        int i = -1;  
        int pos = 0; 
        int last=-1;
        while((i=str.indexOf("\\u", pos)) != -1){
            sb.append(str.substring(pos, i));  
            if(i+6 <= length){  
                pos = i+6;  
                sb.append(unicodeToString(str.substring(i, i+6)));
                last=pos;
            }else{
                sb.append(str.substring(i));
                last=length;
            }
        } 
        if(last==-1){
            return str;
        }else if(last<length){
            sb.append(str.substring(last));
        }
        return sb.toString();  
        
    }
	@Override
	public List<TbHero> findHero(Page page) {
		// TODO Auto-generated method stub
		return tbHeroDao.findHero(page);
	}
	@Override
	public List<TbHero> findHeroByName(Page page, String name) {
		// TODO Auto-generated method stub
		return tbHeroDao.findHeroByName(page, name);
	}
	@Override
	public long countHeroByName(String name) {
		// TODO Auto-generated method stub
		return tbHeroDao.countHeroByName(name);
	}
    

} 
