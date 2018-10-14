new Vue({
  el: '#app',
  data () {
		return {
			user: {
		    userId: null,
			  password: null,
			  email: null, 
			  prefectureCode: null,
			},
			prefectures: [],
			isProcess: false,
			isError: false,
		};
  },
  mounted () {
  	axios.get('/townbook/api/prefectures')
         .then(res => { this.prefectures = res.data.results; });    
  },
  methods: {
    post () {
    	this.isProcess = true;
    	axios.post('/townbook/api/users', this.user)
	         .then(res => {
	        	 M.Modal.getInstance($('#modal')).open();
	          })
	         .catch(error => {
	        	 this.isError = true;
	         })
    	     .then(() => {
	        	 this.isProcess = false;    	    	 
    	     });
    },
  }
})