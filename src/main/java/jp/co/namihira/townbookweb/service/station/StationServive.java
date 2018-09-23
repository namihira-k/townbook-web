package jp.co.namihira.townbookweb.service.station;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jp.co.namihira.townbookweb.client.ekispert.EkispertClient;
import jp.co.namihira.townbookweb.client.ekispert.Point;
import jp.co.namihira.townbookweb.client.ekispert.ResultSet;
import jp.co.namihira.townbookweb.dto.StationDto;

@Service
public class StationServive {


	@Autowired
	private EkispertClient ekispertClient;
	
	public List<StationDto> getStations() {
		final ResultSet resultSet = ekispertClient.getStations();
		return toStationDtos(resultSet.getPoints());
	}
	
	private List<StationDto> toStationDtos(final List<Point> points) {
		final List<StationDto> stationDtos = points.stream()
				                                   .map(p -> new StationDto(p.getStation().getCode(), p.getStation().getName()))
				                                   .collect(Collectors.toList());
		return stationDtos;
	}
	
	
}
