package jp.co.namihira.townbookweb.client.shosen;

import java.util.Optional;
import java.util.stream.Stream;

import jp.co.namihira.townbookweb.util.CommonUtil;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ShosenEnum {

	JIMBOCHO("13", "22756", "書泉グランデ（神保町）", new String[]{"神保町"}),
	AKIHABARA("13", "22492", "書泉ブックタワー（秋葉原）", new String[]{"秋葉原"}),
	OTHER("13", "22828", "HMV", new String[]{"その他"});	
	
	private final String prefectureCode;
	private final String stationCode;
    private final String name;
    private final String[] identifier;
    
    public static ShosenEnum getShopbyName(final String name) {
    	Optional<ShosenEnum> result = Stream.of(ShosenEnum.values())
    			                            .filter(shop -> CommonUtil.contains(name, shop.getIdentifier()))
    			                            .findFirst();
    	return result.orElse(OTHER);
    }

}
