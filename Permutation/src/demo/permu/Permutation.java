package demo.permu;

import java.util.ArrayList;
import java.util.List;

public class Permutation {
	
	public static List<List<Integer>> perms(List<Integer> permsList){
		List<List<Integer>> resultList = new ArrayList<List<Integer>>();
		if(permsList == null || permsList.size()==0){
			return null;
		}
		else{
			if(permsList.size() == 2){				
				resultList.add(permsList);
				resultList.add(swap(0,1,permsList));
			}
			else{
				for(List<Integer> lt : allRotated(permsList)){
				   
				   List<Integer> temp = new ArrayList<Integer>(lt.subList(1,lt.size()));
				   List<List<Integer>> restPerms = perms(temp);
				   for(List<Integer> rest: restPerms){
					   List<Integer> sol = new ArrayList<Integer>();
					   sol.add(lt.get(0));
					   sol.addAll(rest);
					   resultList.add(sol);
				   }
				}
			}
		}
		return resultList;
	}
	
	public static List<List<Integer>> allRotated(List<Integer> perms){
		List<List<Integer>> allRotateList = new ArrayList<List<Integer>>();
		for(Integer head: perms){
			int index = perms.indexOf(head);
			allRotateList.add(swap(0, index, perms));
		}
		return allRotateList;
	}
	
	public static List<Integer> swap(int index1, int index2 , List<Integer> perms){
		List<Integer> resultList = new ArrayList<Integer>(perms);
		if(perms != null && index1 < perms.size() && index2 < perms.size()){
			Integer temp = resultList.get(index1);
			resultList.set(index1, resultList.get(index2));
			resultList.set(index2, temp);
		}
		return resultList;
	}
	
	public static void printList(List<Integer> resultList){
		for(Integer i : resultList){
			System.out.print(i + ",");
		}
		System.out.println();
	}
	
	public static void main(String[] args){
		List<Integer> permsList = new ArrayList<Integer>();
		for(int i = 1 ; i <= 4; i++){
			permsList.add(i);
		}
		List<List<Integer>> result = perms(permsList);
		
		
		for(List<Integer> lt: result){
			printList(lt);
		}
		
	}
}
