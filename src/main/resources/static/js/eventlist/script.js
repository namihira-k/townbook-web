new Vue({
  el: '#app',
  data () {
		return {
			events: [],
		};
  },

  mounted () {
    axios.get('/townbook/api/events')
         .then(res => { this.events = res.data.results; });    
  },
});