package com.yuanyu.demo;

import java.util.ArrayList;
public class SelfDefMap<K, V> extends ArrayList<SelfDefPair<K,V>>{
	

	public void put(K key, V value) {
		if (!this.contains(new SelfDefPair<K, V>(key, value))) {
			this.add(new SelfDefPair<K, V>(key, value));
		}
	}

	public V get(K key) {
		for (SelfDefPair<K, V> sp : this) {
			if (sp.key.equals(key)) {
				return sp.value;
			}
		}
		return null;
	}

	public int size() {
		return this.size();
	}

	public static void main(String[] argv) {
		
		SelfDefMap<String, User> userMap = new SelfDefMap<String, User>();
		for(int i = 0 ; i <3; i++){
			User user = new User(String.format("json%d", i),i,String.format("engineer%d", i));
			userMap.put(String.format("json%d", i),user);
			
		}
		
		System.out.println(userMap.get("json1"));
	}
}
