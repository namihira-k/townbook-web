new Vue({
  el: '#eventadd',
  data () {
		return {
			event: {
		    name: '',
		    conditions: '',
			  stationCode: '',
			  startDateTime: '', 
			  endDateTime: '',
			  content: '',
			},
			prefectureCode: 13, // Tokyo
			lineCode: '',
			prefectures: [],
			lines: [],
			stations: [],
			isError: false,
		};
  },
  mounted () {
    axios.get('/api/prefectures')
         .then(res => { this.prefectures = res.data.results; });
    
  	axios.get('/api/lines', {
      params: {
        prefectureCode: this.prefectureCode
      }
    }).then(res => { this.lines = res.data.results; });    
    
  },
  
  methods: {
    post () {
    	this._buildDateTime();    	
    	axios.post('/api/events', this.event)
	         .then(res => {
	           console.log(res);
	          })
			     .catch(error => {
			     	 this.isError = true;
			       $("html,body").animate({scrollTop:0},"slow");
			     })
    },
    
    
    getLines () {
    	axios.get('/api/lines', {
        params: {
          prefectureCode: this.prefectureCode
        }
      }).then(res => { this.lines = res.data.results; });
    },

    
    getStations () {
    	axios.get('/api/stations', {
        params: {
        	lineCode: this.lineCode
        }
      }).then(res => { this.stations = res.data.results; });
    },
    
    _buildDateTime () {
    	var startDate = M.Datepicker.getInstance($('#startDate')).el.value;
    	var startTime = M.Timepicker.getInstance($('#startTime')).time;
    	this.event.startDateTime = startDate + ' ' + startTime;

    	var endDate = M.Datepicker.getInstance($('#endDate')).el.value;
    	var endTime = M.Timepicker.getInstance($('#endTime')).time;
    	this.event.endDateTime = endDate + ' ' + endTime;    	
    }
  }
})