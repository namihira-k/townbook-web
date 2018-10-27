new Vue({
  el: '#app',
  data () {
		return {
			events: [],
			fromDate: '',
			prefectureCode: 13, //Tokyo
			lineCode: '',
			stationCode: '',
			prefectures: [],
			lines: [],		
			stations: [],
			
			totalCount: 0,
			page: 0,
			
			isProcess: false,
		};
  },

  mounted () {
    axios.get('/yorimichi/api/prefectures')
         .then(res => { this.prefectures = res.data.results; });    
  	
  	axios.get('/yorimichi/api/lines', {
			      params: {
			        prefectureCode: this.prefectureCode
			      }})
			   .then(res => { this.lines = res.data.results; });
   	
  	axios.get('/yorimichi/api/events', {
				    params: {
				    	prefectureCode: this.prefectureCode
				    }})
         .then(res => {
        	 this.totalCount = res.data.totalCount;
        	 this.events = res.data.results;
        	});
  },
  
  methods: {
    getLines () {
    	axios.get('/yorimichi/api/lines', {
        params: {
          prefectureCode: this.prefectureCode
        }
      }).then(res => { this.lines = res.data.results; });
    },
  	
  	getStations () {
    	axios.get('/yorimichi/api/stations', {
        params: {
          prefectureCode: this.prefectureCode,        	
        	lineCode: this.lineCode
        }
      }).then(res => { this.stations = res.data.results; });
    },
    
    getEvents () {
    	var el = $('#eventlist').offset().top;
      $("html,body").animate({scrollTop:el}, "slow");
    	this.isProcess = true;
    	
    	this.page = 0;
    	this.fromDate = M.Datepicker.getInstance($('#startDate')).el.value;    	
    	axios.get('/yorimichi/api/events', {
			        params: {
			          page: this.page,
			        	prefectureCode: this.prefectureCode,        	
			          stationCode: this.stationCode,
			          fromDate: this.fromDate,
			        }})
			      .then(res => {
		        	this.totalCount = res.data.totalCount;
			      	this.events = res.data.results;
			      })
			      .then(() => { this.isProcess = false; });
    },
    
    addEvents ($state) {
    	this.page += 1;    	
    	axios.get('/yorimichi/api/events', {
        params: {
					page: this.page,
					prefectureCode: this.prefectureCode,        	
					stationCode: this.stationCode,
					fromDate: this.fromDate
        }})
      .then(res => {
      	if (res.data.results.length > 0) {
      		this.events.push(...res.data.results);
        	$state.loaded();
      	} else {
      		$state.complete();      		
      	}
      });
    },
    
    moveSearch () {
    	var el = $('#id_search').offset().top;
      $("html,body").animate({scrollTop:el}, "fast");    	
    }
    
  }
});