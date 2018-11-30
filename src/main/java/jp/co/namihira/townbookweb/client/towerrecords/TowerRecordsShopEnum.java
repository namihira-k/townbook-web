package jp.co.namihira.townbookweb.client.towerrecords;

import java.util.List;

import jp.co.namihira.townbookweb.util.CommonUtil;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum TowerRecordsShopEnum {
	
	SHINJUKU("13", "22741", "タワーレコード新宿店", "https://tower.jp/store/kanto/Shinjuku/event"),
	IKEBUKURO("13", "22513", "タワーレコード池袋店", "https://tower.jp/store/kanto/Ikebukuro/event"),
	SHIBUYA("13", "22715", "タワーレコード渋谷店", "https://tower.jp/store/kanto/Shibuya/event"),
	YOKOHAMA("14", "23368", "タワーレコード横浜ビブレ店", "https://tower.jp/store/kanto/YokohamaVIVRE/event"),
	KAWASAKI("14", "23126", "タワーレコード川崎店", "https://tower.jp/store/kanto/Kawasaki/event"),	
	NAGOYA_PARCO("23", "25230", "タワーレコード名古屋パルコ店", "https://tower.jp/store/chubu_hokuriku_shinetsu/NagoyaParco/event"),
	UMEDA_NU("27", "25853", "タワーレコード梅田NU茶屋町店", "https://tower.jp/store/kinki/UmedaNUChayamachi/event");
	
	private final String prefectureCode;
	private final String stationCode;
    private final String name;
	private final String url;

	private static final List<String> KEYWORDS_FREE = CommonUtil.list("観覧フリー", "観覧自由", "無料");

	public static List<String> getKeywordsOfFree(){
		return KEYWORDS_FREE;
	}
	
}
