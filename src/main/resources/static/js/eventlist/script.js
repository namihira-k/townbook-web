new Vue({
  el: '#app',
  data () {
		return {
			events: [],
			fromDate: '',
			prefectureCode: '',
			stationCode: '',
			prefectures: [],
			stations: [],
		};
  },

  mounted () {
    axios.get('/townbook/api/prefectures')
         .then(res => { this.prefectures = res.data.results; });    
  	
  	axios.get('/townbook/api/events')
         .then(res => { this.events = res.data.results; });    
  },
  
  methods: {
    getStations () {
    	axios.get('/townbook/api/stations', {
        params: {
          prefectureCode: this.prefectureCode
        }
      }).then(res => { this.stations = res.data.results; });
    },
    
    getEvents () {
    	this.fromDate = M.Datepicker.getInstance($('#startDate')).el.value;
    	
    	axios.get('/townbook/api/events', {
        params: {
          stationCode: this.stationCode,
          fromDate: this.fromDate
        }
      }).then(res => { this.events = res.data.results; });
    }  	
  
  }
  
});