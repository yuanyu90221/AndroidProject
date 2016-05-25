package demo.permu;

import java.util.ArrayList;
import java.util.List;

public class NonRecursiveComb {

	
	/**
	 * set before m = 1
	 * 
	 * @param arrayList
	 * @param m
	 */
	public static void setBeforeOne(ArrayList<Integer> arrayList, int m){
		for(int i =0 ; i < m; i++){
			arrayList.set(i, 1);
		}
	}
	
	public static List<Integer> getValueList(List<Integer> positionList, int m){
		List<Integer> valueList = new ArrayList<Integer>();
		int size = positionList.size();
		//int count = 0;
		for(int i = 0 ; i < size;i++){
			if(positionList.get(i) == 1){
				valueList.add(i+1);
			}
			if(valueList.size() == m){
				break;
			}
		}
		return valueList;	
	}
	
	public static boolean checkEnd(List<Integer> positionList,int m){
		boolean isEnd = true;
		int lastIndex = positionList.size() - 1;
		int reachIndex = lastIndex - m ;
		for(int i = lastIndex; i >= reachIndex +1  ;i--){
			if(positionList.get(i)==0){
				isEnd = false;
				break;
			}
		}
		return isEnd;
	}
	
	public static void move(List<Integer> positionList){
		int fromIndex = 0;
		int moveToIndex = fromIndex + 1;
		int resetCount = 0;
		int size = positionList.size();
		for(int i = 0; i < size; i++){
			
			if( moveToIndex < size && positionList.get(fromIndex)==1 && positionList.get(moveToIndex)==0){
				
				positionList.set(fromIndex, 0);
				positionList.set(moveToIndex, 1);
				break;
			}
			if(positionList.get(fromIndex) == 1){
				resetCount++;
			}
			fromIndex++;
			moveToIndex++;
		}
		for(int i =0; i < fromIndex;i++){
			if( i < resetCount){
				positionList.set(i, 1);
			}
			else{
				positionList.set(i, 0);
			}
		}
	}
	public static List<List<Integer>> combine01(ArrayList<Integer> orignalPositionList, int m){
		List<List<Integer>> result = new ArrayList<List<Integer>>();
		// ­º¥ý ¶ñ¤J1
		setBeforeOne(orignalPositionList, m);
		List<Integer> first = new ArrayList<Integer>(orignalPositionList);
		result.add(getValueList(first,m));
		
		do{
			move(first);
			result.add(getValueList(first,m));
			
		}while(!checkEnd(first,m));
		return result;
	}
	public static void main(String[] args) { 
		
		ArrayList<Integer> numberList = new ArrayList<Integer>();
		for(int i =1; i <= 49; i++){
			numberList.add(0);
		}
		List<List<Integer>> result = combine01(numberList, 4);
		for(List<Integer> list: result){
			printList(list);
		}
	}

	public static void printList(List<Integer> result){
		for(Integer num: result){
			System.out.print(num+",");
		}
		System.out.println();
	}
}
