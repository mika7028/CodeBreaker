package 疑似コード;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class CodeBreakerSecond {

	public static void main(String[] args) {
		// TODO 自動生成されたメソッド・スタブ
        GameEngine ge = new GameEngine();
        /**
         * countは何回目のチャレンジかを数えている
         */
        int count = 0;
        
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in) );
        System.out.println(ge.getTitle());
        System.out.println(ge.getRule());
        
        //ランダムな答えを作成する
       ge.makeAnswers();
       while(true){
    	   count++;
    	   System.out.println("***" + count +  "回目 ***");
    	   //インプット
    	   for(int i = 0; i < ge.getNumberOfAnswers(); i++){
    		   System.out.println((i + 1) + "個目 : ");
    		   try{
    			   ge.inputAnswer(i, br.readLine());
    		   } catch(InputException e){
    			   System.out.println(e.getMessage());
    			   i--;
    		   } catch(IOException e){
    			   System.out.print("もう一度入力してください");
    			   i--;
    		   } catch(Exception e){
    			   System.out.println();
    		   }
    	   }
    	   //答え判断
    	   boolean end = ge.judge();
    	   //終了判断
    	   System.out.println("ヒット" + ge.getHit()
    	                     + "ブロー" + ge.getBlow());
    	   if(end){
    		   System.out.println("おめでとう！！");
    	   } else{
    		   System.out.println();
    	   }
       }
	}

}
