new Vue({
  el: '#eventadd',
  data () {
		return {
			event: {
		    name: '',
			  stationCode: '',
			  startDateTime: '', 
			  endDateTime: '',
			  content: '',
			},
			prefecture: {
				code: '',
			},
			prefectures: [],
			stations: [],
			isError: false,
		};
  },
  mounted () {
    axios.get('/townbook/api/prefectures')
         .then(res => { this.prefectures = res.data.results; });    
  },
  
  methods: {
    post () {
    	this._buildDateTime();    	
    	axios.post('/townbook/api/events', this.event)
	         .then(res => {
	           console.log(res);
	          })
			     .catch(error => {
			     	 this.isError = true;
			       $("html,body").animate({scrollTop:0},"slow");
			     })
    },
    
    getStations () {
    	axios.get('/townbook/api/stations', {
        params: {
          prefectureCode: this.prefecture.code
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