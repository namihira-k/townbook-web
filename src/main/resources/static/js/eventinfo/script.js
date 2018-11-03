new Vue({
  el: '#eventinfo',
  data () {
		return {
			uuid: uuid,
			event: {},
			prefectures: [],

			isProcess: true,
		};
  },
  
  head: {
  	meta () {
  		return [
    		{ name: 'twitter:title', content: this.event.name },
    		{ name: 'twitter:description', content: this.event.content },
    	];
  	},
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
  	this.$emit('updateHead');
  },
  
  methods: {
    format () {
    	$('.url-content').linkify({
		    target: '_blank',
		  });
    },
  	
  }
  
})