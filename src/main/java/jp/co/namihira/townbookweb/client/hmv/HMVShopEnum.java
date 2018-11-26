package jp.co.namihira.townbookweb.client.hmv;

import java.util.List;

import jp.co.namihira.townbookweb.util.CommonUtil;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum HMVShopEnum {
	
	IKEBUKURO("13", "22513", "HMVエソラ池袋", "https://www.hmv.co.jp/store/IKE/"),
	SHIBUYA("13", "22715", "HMV&BOOKS SHIBUYA", "https://www.hmv.co.jp/store/BTK/"),
	YOKOHAMA_WP("14", "23368", "HMV横浜ワールドポーターズ", "https://www.hmv.co.jp/store/YWP/"),
	YOKOHAMA_LP("14", "23123", "HMVららぽーと横浜", "https://www.hmv.co.jp/store/LYK/");
	
	private final String prefectureCode;
	private final String stationCode;
    private final String name;
	private final String url;

	private static final List<String> KEYWORDS_FREE = CommonUtil.list("観覧フリー");

	public static List<String> getKeywordsOfFree(){
		return KEYWORDS_FREE;
	}	
	
}
