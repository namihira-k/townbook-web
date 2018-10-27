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
         .then(res => { this.events = res.data.results; });
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
    	
    	this.fromDate = M.Datepicker.getInstance($('#startDate')).el.value;    	
    	axios.get('/yorimichi/api/events', {
			        params: {
			          prefectureCode: this.prefectureCode,        	
			          stationCode: this.stationCode,
			          fromDate: this.fromDate
			        }})
			      .then(res => { this.events = res.data.results; })
			      .then(() => { this.isProcess = false; });
    },
    
    addEvents ($state) {
    	axios.get('/yorimichi/api/events', {
        params: {
          prefectureCode: this.prefectureCode,        	
          stationCode: this.stationCode,
          fromDate: this.fromDate
        }})
      .then(res => {
      	this.events = res.data.results;
      	$state.loaded();
      	$state.complete();
      });
    },
    
    moveSearch () {
    	var el = $('#id_search').offset().top;
      $("html,body").animate({scrollTop:el}, "slow");    	
    }
    
  }
});