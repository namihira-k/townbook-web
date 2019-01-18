new Vue({
  el: '#header',
  data () {
		return {
			datetime: '---------- --:--',
		};
  },
  
  mounted () {
  	this.updateTime();
  	let self = this;
    setInterval(function () {self.updateTime()}, 60000);  	
  },

  methods: {
  	updateTime () {
  		this.datetime = this.formatDate(new Date());
  	},
  	
  	formatDate (date) {
  		return date.getFullYear() + "-" + (date.getMonth()+1) + "-" + date.getDate() + " " 
  		     + date.getHours() + ":" + date.getMinutes();
  	},
  	
  },
  
})