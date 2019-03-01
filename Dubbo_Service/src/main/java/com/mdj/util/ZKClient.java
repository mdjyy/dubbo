package com.mdj.util;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.KeeperException.NoNodeException;
import org.apache.zookeeper.ZooDefs.Ids;

public class ZKClient {
    
	private static ZKClient client;
	private static ZooKeeper zk;
    private static Lock lock = new ReentrantLock();
    private static ChildrenChangedWatcher childrenChangedWatcher;
    private static Map<String,List> CacheMap = new ConcurrentHashMap<String, List>();
    public static ZKClient getInstance(){
        if(client==null){
        	try {
        		lock.lock();
        		if(client==null){
        			client = new ZKClient();
        			client.init();
        		}
			} catch (Exception e) {
			    e.printStackTrace();
			}finally{
				lock.unlock();
			}
        }
        return client;
	}
	
	public void init() throws IOException{
		Properties p = new Properties();
		p.load(ZKClient.class.getResourceAsStream("/zk.properties"));
		String connectString = p.getProperty("connectString","localhost:2181");
		int sessionTimeout = Integer.valueOf(p.getProperty("sessionTimeout","1000"));
		zk = new ZooKeeper(connectString, sessionTimeout, new Watcher() {
			@Override
			public void process(WatchedEvent event) {
                System.out.println("路径:"+event.getPath()+"发生事件"+event.getType());
			}
		});
		childrenChangedWatcher = new ChildrenChangedWatcher();
	}
	
	public void createNode(String path,String data) throws KeeperException, InterruptedException{
		if(path!=null&&!path.startsWith("/")){
    		path = "/"+path;
    	}
    	try {
    		zk.create(path, data==null?null:data.getBytes(),Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
    	} catch (NoNodeException e) {
			String parentPath = path.substring(0,path.lastIndexOf("/"));
            createNode(parentPath, null);
            createNode(path, data);
		}	     
	}
	public  void deleteNode(String path) throws KeeperException, InterruptedException{
	    	List<String> list = zk.getChildren(path, true);
	        for(String s:list){
	        	deleteNode(path+"/"+s);
	        }
	        zk.delete(path, -1);
	        CacheMap.remove(path);
	}
	public  List getChildren(String path) throws KeeperException, InterruptedException{
		    	List list = zk.getChildren(path,childrenChangedWatcher);
	            return list;
	}
	public  void close() throws InterruptedException{
		zk.close();
	}
	
	private  class  ChildrenChangedWatcher implements Watcher{
		@Override
		public void process(WatchedEvent event) {
			try {
				String path = event.getPath();
				List list = zk.getChildren(event.getPath(),this);
				CacheMap.put(path, list);
				System.err.println("触发"+event.getType()+",节点"+event.getPath()+"刷新后"+list);
			} catch (KeeperException e) {
				e.printStackTrace();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	} 
	
	public static void main(String[] args) throws KeeperException, InterruptedException {
		ZKClient client = ZKClient.getInstance();
		//client.deleteNode("/cache");
		client.createNode("/cache/config/ip=127.0.0.1", null);
		client.createNode("/cache/config/host=2181", null);
		List list = client.getChildren("/cache/config");
	    System.out.println(list);
		client.createNode("/cache/config/maxId=5", null);
        System.out.println(client.getChildren("/cache/config"));
        while(true){
        	Thread.sleep(1000);
        	System.out.println(1);
        }
	}
}
