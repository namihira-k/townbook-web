package jp.co.namihira.townbookweb.service.station;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import jp.co.namihira.townbookweb.client.ekispert.EkispertClient;
import jp.co.namihira.townbookweb.client.ekispert.Line;
import jp.co.namihira.townbookweb.client.ekispert.Point;
import jp.co.namihira.townbookweb.client.ekispert.ResultListSet;
import jp.co.namihira.townbookweb.client.ekispert.ResultSet;
import jp.co.namihira.townbookweb.client.ekispert.Station;
import jp.co.namihira.townbookweb.dao.StationDao;
import jp.co.namihira.townbookweb.dto.LineDto;
import jp.co.namihira.townbookweb.dto.StationDto;
import jp.co.namihira.townbookweb.util.CommonUtil;

@Service
public class StationService {

	@Autowired
	private EkispertClient ekispertClient;
	
	@Value("${app.station.line.ignore}")
	private String ignoreLineWords = "";
	
	@Autowired
	private StationDao stationDao;
	
	public List<LineDto> getLines(final int prefectureCode) {
		final ResultListSet result = ekispertClient.getLines(prefectureCode);		
		List<Line> lines = result.getLines().stream()
				                            .filter(line -> line.getName().contains(ignoreLineWords) == false)
				                            .collect(Collectors.toList());
		
		return toLineDtos(lines);
	}
	
	public List<StationDto> getStations(final String prefectureCode) {
		return stationDao.findByPrefectureCode(prefectureCode);
	}
	
	public List<StationDto> getStations(final String prefectureCode, final String lineCode) {
		final ResultListSet result = ekispertClient.getStations(lineCode);

		List<Point> points = result.getPoints();
		if (CommonUtil.isNotEmpty(prefectureCode)) {
			points = points.stream()
                           .filter(p -> p.getPrefecture().getCode().equals(prefectureCode))
                           .collect(Collectors.toList());			
		}
		return toStationDtos(points);
	}		
	
	public StationDto getStationByCode(final String code) {
		final StationDto existed = stationDao.findByCode(code);
		if (existed != null) {
			return existed;
		}
		
		final ResultSet apiResult = ekispertClient.getStation(code);
		final StationDto stationDto = toStationDto(apiResult.getPoint());
		stationDao.save(stationDto);
		
		return stationDto;
	}
	
	public List<StationDto> getStationsbyCode(final List<String> codes) {
		return stationDao.findByCodeIn(codes);
	}
	
	
	public static StationDto toStationDto(final Point point) {
		final Station station = point.getStation();
		return new StationDto(station.getCode(), station.getName());
	}
	
	public static Optional<StationDto> getByCode(final String code, final List<StationDto> stationDtos) {
		return stationDtos.stream()
				          .filter(s -> s.getCode().equals(code))
				          .findFirst();
	}

	private List<LineDto> toLineDtos(final List<Line> lines) {
		final List<LineDto> dtos = lines.stream()
                                        .map(line -> new LineDto(line.getCode(), line.getName()))
                                        .collect(Collectors.toList());
        return dtos;
	}
	
	private List<StationDto> toStationDtos(final List<Point> points) {
		final List<StationDto> stationDtos = points.stream()
				                                   .map(p -> new StationDto(p.getStation().getCode(), p.getStation().getName()))
				                                   .collect(Collectors.toList());
		return stationDtos;
	}
	
}
