package jp.co.namihira.townbookweb.consts;

import java.util.Locale;

import org.springframework.context.MessageSource;

import jp.co.namihira.townbookweb.dto.PrefectureDto;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum PrefectureEnum {
	
    HOKKAIDO(1),
    AOMORI(2),
    IWATE(3),
    MIYAGI(4),
    AKITA(5),
    YAMAGATA(6),
    FUKUSHIMA(7),
    IBARAKI(8),
    TOCHIGI(9),
    GUNMA(10),
    SAITAMA(11),
    CHIBA(12),
    TOKYO(13),
    KANAGAWA(14),
    NIIGATA(15),
    TOYAMA(16),
    ISHIKAWA(17),
    FUKUI(18),
    YAMANASHI(19),
    NAGANO(20),
    GIFU(21),
    SHIZUOKA(22),
    AICHI(23),
    MIE(24),
    SHIGA(25),
    KYOTO(26),
    OHSAKA(27),
    HYOGO(28),
    NARA(29),
    WAKAYAMA(30),
    TOTTORI(31),
    SHIMANE(32),
    OKAYAMA(33),
    HIROSHIMA(34),
    YAMAGUCHI(35),
    TOKUSHIMA(36),
    KAGAWA(37),
    EHIME(38),
    KOHTI(39),
    FUKUOKA(40),
    SAGA(41),
    NAGASAKI(42),
    KUMAMOTO(43),
    OHITA(44),
    MIYAZAKI(45),
    KAGOSHIMA(46),
    OKINAWA(47);
    
    private final int code;
        
    public String getId(){
        return name().toLowerCase();
    }

    public String getDisplayname(final MessageSource messageSource, final Locale locale){
        return messageSource.getMessage("prefecture." + getId(), null, locale);
    }
    
    public PrefectureDto toPrefectureDto(final MessageSource messageSource, final Locale locale) {
    	return new PrefectureDto(code, getDisplayname(messageSource, locale));
    }
	

}
