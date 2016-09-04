package  com.lzx.hero.dao;
import com.lzx.hero.bean.TbHero;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.lzx.hero.dao.base.BaseDao;
import com.lzx.hero.dao.base.Page;
@Repository
public class TbHeroDao extends BaseDao<TbHero, Integer>{
	public TbHero getHero(String name){
		List<TbHero> list=queryHql("from TbHero where name like ?", name);
		if(list.size()>0){
			return list.get(0);
		}else{
			return null;
		}
	}
	public List<TbHero> findAllOrderAddress(){
		return queryHql("from TbHero order by address,sec");
	}
	public List<TbHero> findHero(Page page){
		return queryHql(page,"from TbHero order by address,sec");
	}
	public List<TbHero> findHeroByName(Page page,String name){
		return queryHql(page,"from TbHero where name like ? order by address,sec","%"+name+"%");
	}
	
	public long countHeroByName(String name){
		return countHql("select count(*) from TbHero where name like ?","%"+name+"%");
	}
	public void updateAddressSec(Integer id,Integer sec){
		executeHql("update TbHero set sec = ? where id = ?", sec,id);
	}
}
