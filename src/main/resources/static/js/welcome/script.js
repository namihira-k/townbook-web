new Vue({
  el: '#app',
  data () {
		return {
			stationStats: [],
		};
  },

  mounted () {
    axios.get('/yorimichi/api/stats/station')
      .then(res => { this.stationStats = res.data.results; });
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