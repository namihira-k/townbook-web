package jp.co.namihira.townbookweb.client.kinokuniya;

import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import jp.co.namihira.townbookweb.util.CommonUtil;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum KinokuniyaEnum {

	SAPPORO("1", "20220", "紀伊國屋書店 札幌本店", new String[]{"札幌"}),
	SENDAI("4", "21065", "紀伊國屋書店 仙台店", new String[]{"仙台"}),
	MAEBASHI("10", "21946", "紀伊國屋書店 前橋店", new String[]{"前橋"}),
	SAITAMA_SHINTOSHIN("11", "29315", "紀伊國屋書店 さいたま新都心店", new String[]{"さいたま新都心"}),
	SHINJUKU("13", "22741", "紀伊國屋書店 新宿本店", new String[]{"新宿"}),
	OTEMACHI("13", "22564", "紀伊國屋書店 大手町ビル店", new String[]{"大手町"}),
	UMEDA("27", "25848", "紀伊國屋書店 梅田本店", new String[]{"梅田"}),
	AKAIKE("23", "24785", "紀伊國屋書店 プライムツリー赤池店", new String[]{"赤池"}),
	NAGOYA_AIRPORT("23", "25079", "紀伊國屋書店 名古屋空港店", new String[]{"名古屋空港"}),
	TSUDAKA("33", "27051", "紀伊國屋書店 エブリイ津高店", new String[]{"津高"}),
	HATSUKAICHI("34", "27402", "紀伊國屋書店 ゆめタウン廿日市店", new String[]{"廿日市"}),
	SAGA("41", "28389", "紀伊國屋書店 佐賀店", new String[]{"佐賀"});
	
	private final String prefectureCode;
	private final String stationCode;
    private final String name;
    private final String[] identifier;
    
	private static final List<String> KEYWORDS_FREE = CommonUtil.list(
			"観覧はフリー",
			"観覧フリー");

	public static List<String> getKeywordsOfFree(){
		return KEYWORDS_FREE;
	}	
    
    public static KinokuniyaEnum getShopbyName(final String name) {
    	Optional<KinokuniyaEnum> result = Stream.of(KinokuniyaEnum.values())
    			                            .filter(shop -> CommonUtil.contains(name, shop.getIdentifier()))
    			                            .findFirst();
    	return result.get();
    }

}
