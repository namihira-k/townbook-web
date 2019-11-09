new Vue({
  el: '#app',
  data () {
		return {
			user: {
		    userId: null,
			  password: null,
			  email: null, 
			  prefectureCode: '',
			},
			prefectures: [],
			isProcess: false,
			isError: false,
		};
  },
  mounted () {
  	axios.get('/api/prefectures')
         .then(res => { this.prefectures = res.data.results; });    
  },
  methods: {
    post () {
      $("html,body").animate({scrollTop:0}, "slow");
    	this.isProcess = true;
    	axios.post('/yorimichi/api/users', this.user)
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