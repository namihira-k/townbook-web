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


})