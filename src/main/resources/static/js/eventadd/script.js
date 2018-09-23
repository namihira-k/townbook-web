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
    post: function () {
  	  axios.post('/townbook/api/events', this.event)
	         .then(response => {
	           console.log(response);
	          });
    }   
  }
})