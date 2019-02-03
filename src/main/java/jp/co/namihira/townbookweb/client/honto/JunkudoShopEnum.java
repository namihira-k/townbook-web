package jp.co.namihira.townbookweb.client.honto;

import java.util.Optional;
import java.util.stream.Stream;

import jp.co.namihira.townbookweb.util.CommonUtil;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum JunkudoShopEnum {

    IKEBUKURO("13", "22513", "ジュンク堂書店 池袋本店", new String[]{"池袋"}),
    OTHER("13", "22828", "ジュンク堂書店", new String[]{"その他"});
    
    private final String prefectureCode;
    private final String stationCode;
    private final String name;
    private final String[] identifier;

    public static final String SHOP_NAME = "ジュンク堂書店";
    
    public static JunkudoShopEnum getShopbyName(final String name) {
        Optional<JunkudoShopEnum> result = Stream.of(JunkudoShopEnum.values())
                                                 .filter(shop -> CommonUtil.contains(name, shop.getIdentifier()))
                                                 .findFirst();
        return result.orElse(JunkudoShopEnum.OTHER);
    }
    
}
