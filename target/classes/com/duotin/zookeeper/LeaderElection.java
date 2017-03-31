package com.duotin.zookeeper;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.List;
import java.util.concurrent.CountDownLatch;

import com.duotin.util.StringUtils;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooDefs;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.data.Stat;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * 
 * @author jared
 * 
 * @Description:zookeeper主机选择对象
 * 
 * @date Dec 8, 2014 11:39:59 AM
 * 
 */
public class LeaderElection implements Watcher, Runnable {

	private static final Logger log = LoggerFactory.getLogger(LeaderElection.class);

	private String zookeeperConnectionString;

	private String rootPath;

	private ZooKeeper zk;

	private byte[] hostAddress;

	public boolean isMaster = Boolean.FALSE;

	public boolean isMaster() {
		return isMaster;
	}

	public void setMaster(boolean isMaster) {
		this.isMaster = isMaster;
	}

	public LeaderElection(String zookeeperConnectionString, String rootPath) {
		this.zookeeperConnectionString = zookeeperConnectionString;
		this.rootPath = rootPath;
		try {
			hostAddress = InetAddress.getLocalHost().getHostAddress().getBytes();
		} catch (UnknownHostException e) {
			log.error("Get hostaddress has an error: " , e);
		}
		buildZK();
	}

	private void buildZK() {
		log.info("Build zookeeper client");
		try {
			zk = new ZooKeeper(zookeeperConnectionString, 10000, this);
			Stat s = zk.exists(rootPath, false);
			if (s == null) {
				zk.create(rootPath, new byte[0], ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
				zk.create(rootPath + "/ELECTION", new byte[0], ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
			}
			log.info("Zookeeper leader election lock address:"
					+ zk.create(rootPath + "/ELECTION/n_", hostAddress, ZooDefs.Ids.OPEN_ACL_UNSAFE,
							CreateMode.EPHEMERAL_SEQUENTIAL));
		} catch (Exception e) {
			log.error("Error connect to zoo keeper then build zookeeper" +e.getMessage());
		}
	}

	public void process(WatchedEvent event) {
		log.error("election leader again the reason is event: " + event);
		if (event.getState() == Event.KeeperState.Disconnected || event.getState() == Event.KeeperState.Expired) {
			log.error("Keep alive then zookeeper connection timeout.");
			buildZK();
		}

	}

	public void run() {
		while (true) {
			try {
				List<String> children = zk.getChildren(rootPath + "/ELECTION", false);
				String leaderPath = "Not found";
				int minValue = -1;
				for (int i = 0; i < children.size(); i++) {
					String child = children.get(i);
					int index = Integer.parseInt(child.substring(2));
					if (i == 0) {
						minValue = index;
						leaderPath = child;
					} else if (index < minValue) {
						minValue = index;
						leaderPath = child;
					}
				}
				LatchChildWatcher latchChildWatcher = new LatchChildWatcher();
				byte[] data = zk.getData(rootPath + "/ELECTION/" + leaderPath, latchChildWatcher, null);
				String masterAddress = new String(data);
				if (StringUtils.isNotEmpty(masterAddress)) {
					log.info("find the leader on the path:" + leaderPath + " whose host address is " + masterAddress);
					if (masterAddress.equals(new String(hostAddress))) {
						isMaster = Boolean.TRUE;
					}
				}
				latchChildWatcher.await();
			} catch (Exception e) {
				log.error("Error get the leader." + e.getMessage());
			}
		}
	}

	private class LatchChildWatcher implements Watcher {

		CountDownLatch latch;

		public LatchChildWatcher() {
			latch = new CountDownLatch(1);
		}

		public void process(WatchedEvent event) {
			log.info("Watcher fired on path: " + event.getPath() + " state: " + event.getState() + " type "
					+ event.getType());
			latch.countDown();
		}

		public void await() throws InterruptedException {
			latch.await();
		}
	}
}
