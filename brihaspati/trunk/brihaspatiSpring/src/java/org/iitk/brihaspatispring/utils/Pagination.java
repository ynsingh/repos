package org.iitk.brihaspatispring.utils;


import java.util.Vector;

public class Pagination {

	public static Vector listDivide(Vector list, int startIndex, int list_size)
        {
                Vector divideList=new Vector();
                try
                {
                        int endIndex=startIndex+list_size;
                        int check=(list.size()-startIndex);
                        int i=0;

                        /**
			 * Divide List of any vector
			 * Vector has whole details of topic
			 * */

                        if(check>=list_size)
                        {
                                for(i=startIndex;i<endIndex;i++)
                                {
                                        divideList.add(list.get(i));
                                }
                        }
                        else
                        {
                                for(i=startIndex;i<startIndex+check;i++)
                                {
                                divideList.add(list.get(i));
                                }
                        }
                }
                catch(Exception e)
                {
                        String message="The error in list Dividing"+e;
                        divideList.add(message);
                }
                return divideList;
        }

	public static int[] linkVisibility(int startIndex, int size, int list_size)
        {
                int value[]=new int[7];
                int endIndex=startIndex+list_size;
                int check_first=0;

                int check_pre=startIndex-list_size;
                int check_last1=size%list_size;
                int check_last=0;

                if(check_last1 == 0){
                        check_last=size-list_size;
                }
                else{
                        check_last=size-check_last1;
                }
                value[0]=startIndex;
                value[1]=endIndex;
                value[2]=check_first;
                value[3]=check_pre;
                value[4]=check_last1;
                value[5]=check_last;
                value[6]=startIndex+1;
                return value;
        }


}

