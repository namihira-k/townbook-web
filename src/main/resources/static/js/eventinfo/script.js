new Vue({
  el: '#eventinfo',
  data () {
		return {
			uuid: uuid,
			event: null,
			prefectures: [],

			isProcess: true,
		};
  },
    
  mounted () {
    axios.get('/yorimichi/api/prefectures')
         .then(res => { this.prefectures = res.data.results; });    
  	
  	axios.get('/yorimichi/api/events/' + this.uuid)
			   .then(res => {
			  	 this.event = res.data;
			   })
			   .then(() => {
  		  	 this.isProcess = false;
			   });
  },
    
  updated () {
  	this.format();
  },
  
  methods: {
    format () {
    	$('.url-content').linkify({
		    target: '_blank',
		  });
    },
    
    isToday(str) {
    	var startDay = new Date(str);
    	var day = new Date();
    	day.setHours(23);
    	day.setMinutes(59);
    	return startDay < day;
    },
  	
  }
  
})