package jp.co.namihira.townbookweb.client.sanseido;

import java.util.Optional;
import java.util.stream.Stream;

import jp.co.namihira.townbookweb.util.CommonUtil;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum SanseidoShopEnum {
	
	SAPPORO("1", "20220", "三省堂書店 札幌店", new String[]{"札幌"}),
	IKEBUKURO("13", "22513", "三省堂書店 池袋本店", new String[]{"池袋"}),
	JINBOCHO("13", "22756", "三省堂書店 神保町本店", new String[]{"神保町"}),
	YURAKUCHO("13", "23036", "三省堂書店 有楽町店", new String[]{"有楽町"}),
	NAGOYA("23", "25077", "三省堂書店 名古屋本店", new String[]{"名古屋"}),
	OTHER("13", "22828", "三省堂書店", new String[]{"その他"});
	
	private final String prefectureCode;
	private final String stationCode;
    private final String name;
    private final String[] identifier;
	
    public static SanseidoShopEnum getShopbyName(final String name) {
    	Optional<SanseidoShopEnum> result = Stream.of(SanseidoShopEnum.values())
    			                                  .filter(shop -> CommonUtil.contains(name, shop.getIdentifier()))
    			                                  .findFirst();
    	return result.orElse(SanseidoShopEnum.OTHER);
    }
    
}
