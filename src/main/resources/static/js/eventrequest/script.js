new Vue({
  el: '#app',
  data () {
		return {
			event: {
		    name: '',
			  url: '',
			},
			isProcess: false,
			isDone: false,
		};
  },

  methods: {
    post () {
    	this.isProcess = true;
    	axios.post('/yorimichi/api/eventrequests', this.event)
    	     .then(() => {
    	    	 this.isProcess = false;
    	    	 this.isDone = true;
    	     });
    },
  }
})