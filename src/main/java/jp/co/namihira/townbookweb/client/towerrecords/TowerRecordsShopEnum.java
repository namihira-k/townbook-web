package jp.co.namihira.townbookweb.client.towerrecords;

import java.util.Optional;
import java.util.stream.Stream;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum TowerRecordsShopEnum {
	
	SAPPORO_PIVOT("1", "20073", "タワーレコード 札幌ピヴォ店"),
	SENDAI("4", "21044", "タワーレコード 仙台店"),
	TSUDAMUMA("12", "22370", "タワーレコード 津田沼店"),
	SHINJUKU("13", "22741", "タワーレコード 新宿店"),
	IKEBUKURO("13", "22513", "タワーレコード 池袋店"),
	SHIBUYA("13", "22715", "タワーレコード 渋谷店"),
	KICHIJOJI("13", "22637", "タワーレコード 吉祥寺店"),
	DIVERCITY_TOKYO("13", "29055", "TOWERmini ダイバーシティ東京プラザ店"),
	YOKOHAMA("14", "23368", "タワーレコード 横浜ビブレ店"),
	KAWASAKI("14", "23126", "タワーレコード 川崎店"),
	HIGASHI_TOTSUKA("14", "23291", "タワーレコード 西武東戸塚店"),
	MORERA_GIFU("21", "29572", "タワーレコード モレラ岐阜店"),
	NAGOYA_PARCO("23", "25230", "タワーレコード 名古屋パルコ店"),
	ODAKA("23", "29659", "タワーレコード 大高店"),
	KYOTO("26", "25635", "タワーレコード 京都店"),
	UMEDA_NU("27", "25853", "タワーレコード 梅田NU茶屋町店"),
	MORINOMIYA("27", "26224", "TOWERmini もりのみやキューズモール店"),
	NAMBA("27", "29090", "タワーレコード 難波店"),
	ABENO_HOOP("27", "26062", "タワーレコード あべのHoop店"),
	HIROSHIMA("34", "27351", "タワーレコード 広島店");
	
	private final String prefectureCode;
	private final String stationCode;
    private final String name;

    public static final String ORG_CODE = "towerRecords";
    
    public static TowerRecordsShopEnum getShopbyName(final String name) {
    	Optional<TowerRecordsShopEnum> result = Stream.of(TowerRecordsShopEnum.values())
    			                                      .filter(shop -> shop.getName().contains(name))
    			                                      .findFirst();
    	return result.orElse(null);
    }
	
}
