package 疑似コード;
/**
 * net.sougethu.first.CodeBreakerFirstクラスの各要素を
 * メソッドにしたもの。
 * @author 三ヶ島　雅彦
 *
 */
public class GameEngine {
  /** タイトル */
	private String title = "*** CodeBreaker ***";//タイトル
  /** ルール説明 */
	private String rule = "隠された3つの数字をあてます。\n"
            + "1つの数字は1から6の間です。\n"
            + "3つの答えの中に同じ数字はありません。\n"
            + "入力した数字の、"
            + "位置と数字が当たってたらヒット、\n"
            + "数字だけあってたらブローとカウントします。\n"
            + "全部当てたら(3つともヒットになったら)"
            + "終了です\n\n";
    /** 回答を格納する配列 */
	private int[] answer;
	/** 入力した内容を保持する配列 */
	private int[] input;
	/** 応えの数字の数。将来的に拡張することを考慮して作成 */
    private int numberOfAnswers = 3;
    /** 乱数の幅。この場合は、１～６までとなる */
    private int widthOfRandom = 6;
    /** ヒット数の数 */
    private int hit = 0;
    /** ブローの数 */
    private int blow = 0;
    /** 
     * デフォルトコントラスタ。 
     *  numberOfAnswersがデフォルト値の３のまま運用する場合
     */
    public GameEngine(){
    	answer = new int[numberOfAnswers];
    	input = new int[numberOfAnswers];
    	makeAnswers();
    }
    /**
     * 答えを作成する。
     * 答えの数が変更されることも考え、numberOfAnswersと照合する
     */
  public  void makeAnswers(){
	  if(answer.length != numberOfAnswers){
		  answer = new int[numberOfAnswers];
		  input = new int[numberOfAnswers];
	  }
	  for(int i = 0; i < answer.length; i++){
		  //自分より前のようそに被るやつがないか確かめる
		  //あったらもう一回ランダム
		  boolean flag = false;
		  answer[i] = (int) (Math.random() * widthOfRandom + 1);
		  do{
			  flag = false;
			  for(int j = i - 1; j >= 0; j--){
				  if(answer[i] == answer[j]){
					  flag = true;
					  answer[i]
							  = (int) (Math.random() + widthOfRandom + 1);
				  }
			  }
		  } while (flag == true);
	  }
  }
  /**
   * 要素を指定して答えを入力する。indexは０から
   * 
   * @param index
   * @param answer
   * @throws Exception
   */
  public void inputAnswer(int index, int answer)throws InputException{
	  if(index > -1 && index < numberOfAnswers){
		  input[index] = answer;		  
	  } else {
		  throw new InputException("入力する答えの範囲外です");
	  }
  } 

  public void inputAnswer(int index, String string)throws Exception{
	  int answer;
	  try{
		  answer = Integer.parseInt(string);		  
	  } catch(NumberFormatException e){
		  throw new InputException("入力が答えの範囲外です");
	  }
	  inputAnswer(index, answer);
  }
  /**
   * inputとanswerを突き合わせる。
   * 全てhitならtrue、それ以外はfalse。
   * 
   * @return　すべてhitならtrue、それ以外はfalse。
   */
    public boolean judge(){
    	//答え判断
    	hit = 0;
    	blow = 0;
    	for (int i = 0; i < answer.length; i++){
    		for(int j = 0; j <answer.length; j++){
    			if(i == j && input[i] == answer[j]){
    				hit++; 				
    			} else if(input[i] == answer[j]){
    				blow++;
    			}
    		}
    	}
    	return (hit == numberOfAnswers);
    } 
    public int getNumberOfAnswers(){
    	return numberOfAnswers;
    }
    public int getWidthOfRandom(){
    	return widthOfRandom;
    }
    public int[] getAnswer(){
    	return answer;
    }
    public int getBlow(){
    	return blow;
    }
    public int getHit(){
    	return hit;
    }
    public int[] getInput(){
    	return input;
    }
    public void setInput(int[] input) throws InputException{
    	for(int i = 0; i < input.length; i++){
    		inputAnswer(i, input[i]);
    	}
    }
    public String getRule(){
    	return rule;
    }
    public void setRule(String rule){
    	this.rule = rule;
    }
    public String getTitle(){
    	return title;
    }
    public void setTitle(String title){
    	this.title = title;
    }
    }