package redistest;

import com.mdj.cache.CacheCluster;

public class RedisTest {
	
    public static void main(String[] args) {
    	//System.out.println(CacheClient.getValue("sww"));
	   System.out.println(CacheCluster.getObject("common_com.ai.seccenter.cache.SecServiceOpneInfoCacheImpl",Object.class));
    }
}
