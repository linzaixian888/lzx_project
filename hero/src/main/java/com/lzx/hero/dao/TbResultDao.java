package  com.lzx.hero.dao;
import com.lzx.hero.bean.TbResult;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.lzx.hero.dao.base.BaseDao;
import com.lzx.hero.dao.base.Page;
@Repository
public class TbResultDao extends BaseDao<TbResult, Integer>{
	public long countByError(String ids){
		if(ids==null){
			return countHql("select count(*) from TbResult");
		}else{
			return countHql("select count(*) from TbResult where error like ?", ids);
		}
	}
	public List<TbResult> getByError(String ids,Page page){
		if(ids==null){
			return queryHql(page,"from TbResult");
		}else{
			return queryHql(page,"from TbResult where error like ?", ids);
		}
	}
	public long countBySuccessAndError(String success,String error){
		return countHql("select count(*) from TbResult where success = ? and error = ?", success,error);
	}
	public long countBySuccessAndError(String success,String error,Integer id){
		return countHql("select count(*) from TbResult where success = ? and error = ? and id != ?", success,error,id);
	}
}
