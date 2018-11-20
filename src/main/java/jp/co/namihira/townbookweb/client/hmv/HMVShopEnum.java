package jp.co.namihira.townbookweb.client.hmv;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum HMVShopEnum {
	
	SHIBUYA("13", "22715", "HMV&BOOKS SHIBUYA", "https://www.hmv.co.jp/store/BTK/");
	
	private final String prefectureCode;
	private final String stationCode;
    private final String name;
	private final String url;
	
}
