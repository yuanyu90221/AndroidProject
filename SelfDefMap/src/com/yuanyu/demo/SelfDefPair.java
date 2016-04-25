package com.yuanyu.demo;

public class SelfDefPair<K, V> {
	K key;
	V value;

	public SelfDefPair(K key, V value) {
		this.key = key;
		this.value = value;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof SelfDefPair<?, ?>) {
			SelfDefPair co = (SelfDefPair) obj;
			return key.equals(co.key);
		}
		return false;
	}

	@Override
	public String toString() {
		return "SelfDefPair [key=" + key + ", value=" + value + "]";
	}

	
}
