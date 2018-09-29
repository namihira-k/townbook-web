package jp.co.namihira.townbookweb.service.station;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jp.co.namihira.townbookweb.client.ekispert.EkispertClient;
import jp.co.namihira.townbookweb.client.ekispert.Point;
import jp.co.namihira.townbookweb.client.ekispert.ResultListSet;
import jp.co.namihira.townbookweb.client.ekispert.Station;
import jp.co.namihira.townbookweb.dto.StationDto;

@Service
public class StationService {

	@Autowired
	private EkispertClient ekispertClient;
		
	public List<StationDto> getStations(final int prefectureCode) {
		final ResultListSet result = ekispertClient.getStations(prefectureCode);
		return toStationDtos(result.getPoints());
	}	
	
	private List<StationDto> toStationDtos(final List<Point> points) {
		final List<StationDto> stationDtos = points.stream()
				                                   .map(p -> new StationDto(p.getStation().getCode(), p.getStation().getName()))
				                                   .collect(Collectors.toList());
		return stationDtos;
	}
	
	public static StationDto toStationDto(final Point point) {
		final Station station = point.getStation();
		return new StationDto(station.getCode(), station.getName());
	}
	
}
