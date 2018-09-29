new Vue({
  el: '#app',
  data () {
		return {
			user: {
		    userId: '',
			  password: '',
			  email: '', 
			  prefectureCode: '',
			},
			prefectures: [],
		};
  },
  mounted () {
    axios.get('/townbook/api/prefectures')
         .then(res => { this.prefectures = res.data.results; });    
  },
  
  methods: {
    post () {
    	axios.post('/townbook/api/users', this.user)
	         .then(res => {
	           console.log(res);
	          });
    },

  }
})