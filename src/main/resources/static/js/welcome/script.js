new Vue({
  el: '#app',
  data () {
		return {
			stationStats: [],
			eventStats: null,
		};
  },

  mounted () {
    axios.get('/yorimichi/api/stats/station')
         .then(res => { this.stationStats = res.data.results; });

    axios.get('/yorimichi/api/stats/event')
         .then(res => { this.eventStats = res.data; });
  },
  
  methods: {
    hasEvents(prefectureCode) {
    	console.log("called hasEvents");
    	for(let s of this.stationStats) {
    		if (s.prefectureCode == prefectureCode) {
    			return true;
    		}
    	};
    	return false;
    },
  },

})