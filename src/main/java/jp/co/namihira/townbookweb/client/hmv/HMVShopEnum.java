package jp.co.namihira.townbookweb.client.hmv;

import java.util.Optional;
import java.util.stream.Stream;

import jp.co.namihira.townbookweb.util.CommonUtil;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum HMVShopEnum {
	
	SENDAI("4", "21044", "HMV 仙台 E BeanS", new String[]{"仙台"}),
	KAWAGUCHI("11", "22174", "HMV イオンモール川口前川", new String[]{"川口"}),
	IKEBUKURO("13", "22513", "HMV エソラ池袋", new String[]{"池袋"}),
	SHIBUYA("13", "22715", "HMV&BOOKS SHIBUYA", new String[]{"SHIBUYA"}),
	YOKOHAMA_WP("14", "23368", "HMV 横浜ワールドポーターズ", new String[]{"横浜ワールドポーターズ"}),
	YOKOHAMA_LP("14", "23123", "HMV ららぽーと横浜", new String[]{"ららぽーと横浜"}),
	KAWASAKI("14", "23126", "HMV ラゾーナ川崎", new String[]{"ラゾーナ川崎"}),	
	SAKEE("23", "24944", "HMV 栄", new String[]{"栄"}),
	KISOGAWA("23", "24902", "HMV イオンモール木曽川", new String[]{"木曽川"}),
	SHINSAIBASHI("27", "25981", "HMV&BOOKS SHINSAIBASHI", new String[]{"SHINSAIBASHI"}),
	OTHER("13", "22828", "HMV", new String[]{"その他"});
	
	private final String prefectureCode;
	private final String stationCode;
    private final String name;
    private final String[] identifier;
	
    public static HMVShopEnum getShopbyName(final String name) {
    	Optional<HMVShopEnum> result = Stream.of(HMVShopEnum.values())
    			                             .filter(shop -> CommonUtil.contains(name, shop.getIdentifier()))
    			                             .findFirst();
    	return result.orElse(HMVShopEnum.OTHER);
    }
    
}
