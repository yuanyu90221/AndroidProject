package demo.permu;

import java.io.DataOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
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
	
	public static void writeList(List<Integer> resultList, DataOutputStream fw ) throws IOException{
		
		for(Integer i: resultList){
			fw.writeBytes(i.toString());
			fw.writeBytes(",");
		}
		fw.writeBytes("\n");
		fw.flush();
		
	}
	
	// C(m,n) = C(m-1, n-1) + C(m-1,n)
	public static List<List<Integer>> comb(List<Integer> combList,int m, int n){
		List<List<Integer>> resultList = new ArrayList<List<Integer>>();
		if(n==0){ // 代表所有都取完了
			List<Integer> temp1 = new ArrayList<Integer>();
			resultList.add(temp1);
		}
		else{
			if(m > n){
				// 取第一個
				List<Integer> temp = new ArrayList<Integer>(combList.subList(1, combList.size()));
				List<List<Integer>> rest = comb(temp,m-1,n-1); 
				for(List<Integer> lt: rest){
					
					List<Integer> sol = new ArrayList<Integer>();
					sol.add(combList.get(0));

					sol.addAll(lt);
					resultList.add(sol);
					
				}
				// 不取第一個
				List<Integer> temp1 = new ArrayList<Integer>(combList.subList(1, combList.size()));
				List<List<Integer>> rest1 = comb(temp1, m-1,n);
				for(List<Integer> lt: rest1){
					if(lt.size()!=0){
						List<Integer> sol1 = new ArrayList<Integer>();
						sol1.addAll(lt);
						resultList.add(sol1);
					}
				}
			}
			else if( m == n){
				List<Integer> temp1 = new ArrayList<Integer>(combList);
				resultList.add(temp1);
			}
		}
		
		return resultList;
	}
	
	public static List<List<Integer>> combFixN(List<Integer> combList, int fixedIndex, int n){
		List<List<Integer>> resultList = new ArrayList<List<Integer>>();
		List<Integer> tempList = new ArrayList<Integer>(combList);
		Integer firstElement = tempList.get(fixedIndex);
		tempList.remove(fixedIndex);
		List<List<Integer>> restComb = comb(tempList,combList.size(),n-1);
		for(List<Integer> restC:restComb){
			if(restC.size()==n-1){
				List<Integer> possibleList = new ArrayList<Integer>();
				possibleList.add(firstElement);
				possibleList.addAll(restC);
				resultList.add(possibleList);
			}
		}
		return resultList;
	}
	
	public static void main(String[] args){
//		FileOutputStream output = null;
//		DataOutputStream dw = null;
//		try {
//			output = new FileOutputStream("D://result.txt");
//			dw = new DataOutputStream(output);
//			List<Integer> permsList = new ArrayList<Integer>();
//			for (int i = 1; i <= 10; i++) {
//				permsList.add(i);
//			}
//			// //List<List<Integer>> result = perms(permsList);
//			// for(int k=0;k <=3;k++){
//			// List<List<Integer>> result = comb(permsList,3,k);
//			//
//			// for(List<Integer> lt: result){
//			// printList(lt);
//			// }
//			// }
//			List<List<Integer>> result = comb(permsList, 10, 3);
//			System.out.println("size = " + result.size());
//			dw.writeBytes("size = " + result.size()+"\n");
//			for (List<Integer> lt : result) {
//				// printList(lt);
//				List<Integer> restList = new ArrayList<Integer>(permsList);
//				restList.removeAll(lt);
//				List<List<Integer>> specialList = comb(restList,7, 3);
//				for (List<Integer> sp : specialList) {
//					System.out.print("result :");
//					restList.clear();
//					restList.addAll(lt);
//					restList.addAll(sp);
//					printList(restList);
//					writeList(restList,dw);
//				}
//				//writeList(lt,dw);
//			}
//		} catch (IOException e) {
//			
//			e.printStackTrace();
//			return ;
//		}finally{
//			if(output != null){
//				try {
//					if(dw!=null){
//						dw.close();
//					}
//					output.close();
//				} catch (IOException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
//			}
//		}
//		
		
		List<Integer> permsList = new ArrayList<Integer>();
		for (int i = 1; i <= 49; i++) {
			permsList.add(i);
		}
		for(int i= 0; i < permsList.size(); i ++){
			List<List<Integer>> result = combFixN(permsList, i, 4);
			System.out.println("size = " + result.size());
			for (List<Integer> lt : result) {
			   printList(lt);
			}
		}
		
	}
}
