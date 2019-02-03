package jp.co.namihira.townbookweb.client.honto;

import java.util.Optional;
import java.util.stream.Stream;

import jp.co.namihira.townbookweb.util.CommonUtil;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum MaruzenShopEnum {

    MARUNOUCHI("13", "22828", "丸善 丸の内本店", new String[]{"丸の内"}),
    SHINJUKU("13", "22741", "丸善 新宿京王店", new String[]{"新宿"}),
    HAKATA("40", "28283", "丸善 博多店", new String[]{"博多"}),
    OTHER("13", "22828", "丸善", new String[]{"その他"});
    
    private final String prefectureCode;
    private final String stationCode;
    private final String name;
    private final String[] identifier;
    
    public static final String SHOP_NAME = "丸善";

    public static MaruzenShopEnum getShopbyName(final String name) {
        Optional<MaruzenShopEnum> result = Stream.of(MaruzenShopEnum.values())
                                                 .filter(shop -> CommonUtil.contains(name, shop.getIdentifier()))
                                                 .findFirst();
        return result.orElse(MaruzenShopEnum.OTHER);
    }
    
}
