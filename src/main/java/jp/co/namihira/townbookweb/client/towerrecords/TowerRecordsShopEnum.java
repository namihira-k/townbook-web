package jp.co.namihira.townbookweb.client.towerrecords;

import java.util.Optional;
import java.util.stream.Stream;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum TowerRecordsShopEnum {
	
	SHINJUKU("13", "22741", "タワーレコード 新宿店"),
	IKEBUKURO("13", "22513", "タワーレコード 池袋店"),
	SHIBUYA("13", "22715", "タワーレコード 渋谷店"),
	KICHIJOJI("13", "22637", "タワーレコード 吉祥寺店"),
	YOKOHAMA("14", "23368", "タワーレコード 横浜ビブレ店"),
	KAWASAKI("14", "23126", "タワーレコード 川崎店"),	
	NAGOYA_PARCO("23", "25230", "タワーレコード 名古屋パルコ店"),
	UMEDA_NU("27", "25853", "タワーレコード 梅田NU茶屋町店");
	
	private final String prefectureCode;
	private final String stationCode;
    private final String name;
	
    public static TowerRecordsShopEnum getShopbyName(final String name) {
    	Optional<TowerRecordsShopEnum> result = Stream.of(TowerRecordsShopEnum.values())
    			                                      .filter(shop -> shop.getName().contains(name))
    			                                      .findFirst();
    	return result.orElse(null);
    }
	
}
