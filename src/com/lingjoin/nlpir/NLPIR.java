package com.lingjoin.nlpir;
/**
 * NLPIR方法二次封装
 * @author Pan
 */
public class NLPIR {
	//初始化状态
	public static boolean InitState=false;
	//初始化方法
	public static boolean init(String argu){
	    int	charsetType=1;
	    InitState=CNLPIRLibrary.Instance .NLPIR_Init(argu,charsetType,"0");
		if(InitState){
			return InitState;
		}else{
			System.out.println(CNLPIRLibrary.Instance.NLPIR_GetLastErrorMsg());
			return false;
		}
	}
	public static boolean Init(){		
		int charsetType=1;
		String  argu="";
	    InitState=CNLPIRLibrary.Instance .NLPIR_Init(argu,charsetType,"0");
		if(InitState){
			return InitState;
		}else{
			System.out.println(CNLPIRLibrary.Instance.NLPIR_GetLastErrorMsg());
			return false;
		}
	}
	/**
	 * NLPIR分词方法
	 * @param sSrc 待分词字符串
	 * @param bPOSTagged 分词标注集序号
	 * 0----为无标注分词结果集，	 
	 * 1---- ICT_POS_MAP_FIRST  计算所一级标注集
	 * 2-----ICT_POS_MAP_SECOND  计算所二级标注集
	 * 3-----PKU_POS_MAP_SECOND   北大二级标注集	
	 *	4-----PKU_POS_MAP_FIRST 	  北大一级标注集
	 * @return
	 */
	public static String paragraphProcess(String sSrc, int bPOSTagged){
		String result=null;
		try{
			result=CNLPIRLibrary.Instance.NLPIR_ParagraphProcess(sSrc, bPOSTagged);
		}catch(Exception e){
			System.out.println(CNLPIRLibrary.Instance.NLPIR_GetLastErrorMsg());
		}
		return result;
	}
	
	public static int getParagraphProcessAWordCount(String para){
		if(!InitState){
			return 0;
		}else
			return CNLPIRLibrary.Instance.NLPIR_GetParagraphProcessAWordCount(para);
	}
	
	public static String finerSegment(String lenWords){
		if(!InitState){
			return null;
		}else
			return CNLPIRLibrary.Instance.NLPIR_FinerSegment(lenWords);
	}
	
	public static long fingerPrint(String sLine){
		if(!InitState){
			return 0;
		}else
			return CNLPIRLibrary.Instance.NLPIR_FingerPrint(sLine);
	}
	
	public static boolean isWord(String word){
		if(!InitState){
			return false;
		}else
			return CNLPIRLibrary.Instance.NLPIR_IsWord(word);
	}
	
	public static String wordFreqStat(String sText){
		if(!InitState){
			return null;
		}else
			return CNLPIRLibrary.Instance.NLPIR_WordFreqStat(sText);
	}
	
	public static String getEngWordOrign(String sWord){
		if(!InitState){
			return null;
		}else
			return CNLPIRLibrary.Instance.NLPIR_GetEngWordOrign(sWord);
	}
	
	public static double getUniProb(String word){
		if(!InitState){
			return 0.0;
		}else
			return CNLPIRLibrary.Instance.NLPIR_GetUniProb(word);
	}
	
	public static String getWordPos(String str){
		String result="";
		String pos="";
		if(!InitState){
			System.out.println("请先初始化分词！");
			return null;
		}
		try{
		    pos=CNLPIRLibrary.Instance.NLPIR_GetWordPOS(str);
		    //System.out.println(pos);
		    if(!pos.contains("#")) return "un";
		    String[] p=pos.split("#");
		    if(p.length>1){
		    	for(int i=0;i<p.length;i++){
		    		if(i==0) result+=p[i].split("/")[1]+"#";
		    		else if(i==p.length-1) result+=p[i].split("/")[1];
		    		else result+=p[i].split("/")[1]+"#";
		    	}
		    	return result;
		    }else{
		    	result=pos.split("/")[1];
		    }
		}catch(Exception e){
			System.out.println("参数解析失败！");
		}
		return result;	
	}
	/**
	 * 添加用户词典
	 * @param fileDict
	 */
	public static int importUserDict(String fileDict){
		if(!InitState){
			return 0;
		}
		return CNLPIRLibrary.Instance.NLPIR_ImportUserDict(fileDict,true);
	}
	
	public static int NLPIR_ImportKeyBlackList(String sFilename){
		if(!InitState){
			return 0;
		}else
			return CNLPIRLibrary.Instance.NLPIR_ImportKeyBlackList(sFilename);
	}
	/**
	 * 添加用户词
	 * @param word
	 * @return
	 */
	public static boolean addUserWord(String word){
		if(!InitState){
			System.out.println("请先初始化分词！");
			return false ;
		}
		int addState= CNLPIRLibrary.Instance.NLPIR_AddUserWord(word);
		if(addState==0)
			return false;
		else 
			return true;
	}
	/**
	 * 保存用户词典
	 */
	public static boolean saveUserWord(){
		return CNLPIRLibrary.Instance.NLPIR_SaveTheUsrDic();
	}
	
	public static boolean deleteUserWord(String word){
		if(!InitState){
			System.out.println("请先初始化分词！");
			return false ;
		}
		int delState=CNLPIRLibrary.Instance.NLPIR_DelUsrWord(word);
		if(delState==-1){
			System.out.print("The word :"+word+"not exsit!");
			return false;
		}else
			return true;
	}
	
	public static String getLastErrorMsg(){
		return CNLPIRLibrary.Instance.NLPIR_GetLastErrorMsg();
	}
	
	/**
	 * 退出
	 */
	public static void Exit(){
		if(!InitState){
			System.out.println("未初始化分词！无需执行退出操作!");
		}
		CNLPIRLibrary.Instance.NLPIR_Exit();
	}
	
	public static void main(String[] args) throws Exception {
		NLPIR.init("lib");
		System.out.println(NLPIR.isWord("中国"));
	}
}

