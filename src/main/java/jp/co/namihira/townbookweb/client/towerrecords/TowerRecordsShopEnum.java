package jp.co.namihira.townbookweb.client.towerrecords;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum TowerRecordsShopEnum {
	
	SHINJUKU("13", "22741", "新宿店", "https://tower.jp/store/kanto/Shinjuku/event"),
	IKEBUKURO("13", "22513", "池袋店", "https://tower.jp/store/kanto/Ikebukuro/event"),
	SHIBUYA("13", "22715", "渋谷店", "https://tower.jp/store/kanto/Shibuya/event"),
	YOKOHAMA("14", "23368", "横浜ビブレ店", "https://tower.jp/store/kanto/YokohamaVIVRE/event");
	
	private final String prefectureCode;
	private final String stationCode;
    private final String name;
	private final String url;

	private static final String COMPANY_NAME = "タワーレコード";
	
	public String getFullName() {
		return COMPANY_NAME + name;
	}
	
}
