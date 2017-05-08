package com.lingjoin.nlpir;

import com.sun.jna.Library;
import com.sun.jna.Native;

public interface CNLPIRLibrary extends Library {

	CNLPIRLibrary Instance = (CNLPIRLibrary) Native.loadLibrary("NLPIR", CNLPIRLibrary.class);
	
	/**
	 * 初始化
	 * @param sDataPath Data目录所在路径
	 * @param encoding 编码，0是GBK，1是UTF8
	 * @param sLicenceCode 为空即可
	 * @return
	 */
	public boolean NLPIR_Init(String sDataPath, int encoding, String sLicenceCode);

	/**
	 * 分词接口
	 * @param sParagraph 待分词串
	 * @param bPOSTagged 是否带词性
	 * @return
	 */
	public String NLPIR_ParagraphProcess(String sParagraph, int bPOSTagged);

	public int NLPIR_GetParagraphProcessAWordCount(String para);
	
	public double NLPIR_FileProcess(String sSourceFilename, String sResultFilename, int bPOStagged);

	public String NLPIR_FinerSegment(String lenWords);
	
	public int NLPIR_AddUserWord(String userWord);

	public boolean NLPIR_SaveTheUsrDic();

	public int NLPIR_DelUsrWord(String sWord);

	public int NLPIR_ImportUserDict(String dictFileName, boolean bOverwrite);

	public int NLPIR_ImportKeyBlackList(String sFilename);

	public long NLPIR_FingerPrint(String sLine);

	public String NLPIR_GetWordPOS(String sWords);

	public boolean NLPIR_IsWord(String word);

	public String NLPIR_WordFreqStat(String sText);

	public String NLPIR_FileWordFreqStat(String sFilename);

	public String NLPIR_GetEngWordOrign(String sWord);

	public double NLPIR_GetUniProb(String word);
	
	public String NLPIR_GetLastErrorMsg();

	public boolean NLPIR_Exit();
}
