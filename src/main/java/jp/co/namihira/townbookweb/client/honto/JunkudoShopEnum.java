package jp.co.namihira.townbookweb.client.honto;

import java.util.Optional;
import java.util.stream.Stream;

import jp.co.namihira.townbookweb.util.CommonUtil;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum JunkudoShopEnum {

    MINAMIFUNABASHI("12", "22457", "ジュンク堂書店 南船橋店", new String[]{"南船橋"}),
    IKEBUKURO("13", "22513", "ジュンク堂書店 池袋本店", new String[]{"池袋"}),
    KICHIJOJI("13", "22637", "ジュンク堂書店 吉祥寺店", new String[]{"吉祥寺"}),
    KOFU("19", "23408", "ジュンク堂書店 岡島甲府店", new String[] {"甲府"}),
    OSAKA("27", "29160", "ジュンク堂書店 大阪本店", new String[]{"大阪"}),
    AKASHI("28", "26377", "ジュンク堂書店 明石店", new String[]{"明石"}),
    FUKUOKA("40", "28236", "ジュンク堂書店 福岡店", new String[]{"福岡"}),
    NAHA("47", "29463", "ジュンク堂書店 那覇店", new String[]{"那覇"}),
    OTHER("13", "22828", "ジュンク堂書店", new String[]{"その他"});
    
    private final String prefectureCode;
    private final String stationCode;
    private final String name;
    private final String[] identifier;

    public static final String ORG_CODE = "junkudo";
    public static final String SHOP_NAME = "ジュンク堂書店";
    
    public static JunkudoShopEnum getShopbyName(final String name) {
        Optional<JunkudoShopEnum> result = Stream.of(JunkudoShopEnum.values())
                                                 .filter(shop -> CommonUtil.contains(name, shop.getIdentifier()))
                                                 .findFirst();
        return result.orElse(JunkudoShopEnum.OTHER);
    }
    
}
