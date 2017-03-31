package com.duotin.util.shard.table.factory;

import com.duotin.util.ConstUtil.DataSource.ShardType;
import com.duotin.util.shard.table.ShardStrategy;

public class ShardFactory {

	public static ShardStrategy createShardFactory(ShardType type) {
		ShardStrategy factory = null;

		switch (type) {
		case DATA:
			factory = new DataShardFactory();
			break;
		case MOD:
			factory = new ModShardFactory();
			break;
		default:
			break;
		}
		return factory;
	}
}
