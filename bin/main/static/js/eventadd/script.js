new Vue({
  el: '#eventadd',
  data () {
		return {
			event: {
		    name: '',
			  date: '',
			  stationCode: '',
			  startTime: '',
			  endTime: '',
			},
			prefecture: {
				code: '',
			},
			prefectures: [],
			stations: [],
		};
  },
  
  mounted () {
    axios.get('/townbook/api/prefectures')
         .then(res => { this.prefectures = res.data.results; });
  },
  
  methods: {
    post () {
  	  axios.post('/townbook/api/events', this.event)
	         .then(response => {
	           console.log(response);
	          });
    },
    
    getStations () {
    	axios.get('/townbook/api/stations', {
        params: {
          prefectureCode: this.prefecture.code
        }
      }).then(res => { this.stations = res.data.results; });
    }
  
  }
})