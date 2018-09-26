new Vue({
  el: '#app',
  data () {
		return {
			events: [],
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
    	axios.get('/townbook/api/events', {
        params: {
          stationCode: this.stationCode
        }
      }).then(res => { this.events = res.data.results; });
    }  	
  
  }
  
});